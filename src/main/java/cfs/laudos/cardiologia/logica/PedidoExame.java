package cfs.laudos.cardiologia.logica;

import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.mail.EmailException;

import cfs.laudos.cardiologia.dao.ExameDAO;
import cfs.laudos.cardiologia.dao.MedicoDAO;
import cfs.laudos.cardiologia.dao.PacienteDAO;
import cfs.laudos.cardiologia.modelo.EmailInfo;
import cfs.laudos.cardiologia.modelo.EmailSender;
import cfs.laudos.cardiologia.modelo.Exame;
import cfs.laudos.cardiologia.modelo.Medico;
import cfs.laudos.cardiologia.modelo.Paciente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PedidoExame implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		String exame = req.getParameter("exame");
		String hipotese = req.getParameter("hipotese");
		String recomendacao = req.getParameter("recomendacao");
		String crm = req.getParameter("medico");
		
		Paciente paciente = new Paciente();
		paciente.setCpf(cpf);
		paciente = (new PacienteDAO()).recuperaPaciente(paciente);
		
		Medico medico = new Medico();
		medico.setCrm(crm);
		medico = (new MedicoDAO()).recuperaMedico(medico);
		
		Exame pedidoExame = new Exame();
		pedidoExame.setPaciente(paciente);
		pedidoExame.setMedico(medico);
		pedidoExame.setExame(exame);
		pedidoExame.setHipotese(hipotese);
		pedidoExame.setRecomendacao(recomendacao);
		pedidoExame.setSituacao(Exame.AGUARDANDO_EXAME);
		
		Calendar dataPedido = Calendar.getInstance();
		pedidoExame.setData(dataPedido);
		
		//data prevista pra 3 dias
		dataPedido.add(Calendar.DAY_OF_MONTH,3);
		Calendar dataPrevista = dataPedido;
		pedidoExame.setDataRealizacao(dataPrevista);
		
		
		String url;
		//Verifica se o usuário ja tem o mesmo exame com status: aguardando
		ExameDAO dao = new ExameDAO();
		if(dao.verificaPacienteExame(pedidoExame) == true) {
			String aviso = "O paciente já tem esse exame marcado!";
			req.setAttribute("aviso", aviso);
			url = "pedidoexame.jsp";
			return url;
		}
		
		//Envio do pedido de exame para paciente
		EmailInfo info = new EmailInfo();
		String message = (String.format("Pedido de exame\nMédico: %s\nExame: %s\nData prevista para realização: "
				+ "%s\nRecomendação: %s",pedidoExame.getMedico().getCrm(),pedidoExame.getExame(),pedidoExame.getDataRealizacao().getTime(),
				pedidoExame.getRecomendacao()));
		try {
			EmailSender.sendEmail(info.getSmtpHost(),info.getSmtpPorta(),info.getUser(),info.getSenha(),info.getFrom(),
					pedidoExame.getPaciente().getEmail(),"Pedido de Exame", message);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		dao.pedidoExame(pedidoExame);
		req.setAttribute("exame", pedidoExame);
		url = "pedidoexamefeito.jsp";
		
		return url;
	}
	
	

}
