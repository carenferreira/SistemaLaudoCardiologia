package cfs.laudos.cardiologia.logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;

import cfs.laudos.cardiologia.dao.ExameDAO;
import cfs.laudos.cardiologia.dao.UsuarioDAO;
import cfs.laudos.cardiologia.modelo.EmailInfo;
import cfs.laudos.cardiologia.modelo.EmailSender;
import cfs.laudos.cardiologia.modelo.Exame;
import cfs.laudos.cardiologia.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EfetuaLogin implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		String url = "login.jsp";
		UsuarioDAO userDao = new UsuarioDAO();
		Usuario user = userDao.validaCredencial(login, senha); 
		
		if(user != null) {
			//Verifica exames cancelados
			ExameDAO exame = new ExameDAO();
			exame.cancelaExame();
			
			List<Exame> exameCancelado = new ArrayList<>();
			exameCancelado = exame.listaExamesCancelados();
			
			HttpSession sessao = req.getSession();
			sessao.setMaxInactiveInterval(3*60);
			sessao.setAttribute("status", true);
			sessao.setAttribute("nome", login);
			
			//Medico
			if(user.getTipo() == 1) {
				if(exameCancelado!=null) {
					//Envia email cancelando os exames
					for(Exame e : exameCancelado) {
						EmailInfo info = new EmailInfo();
						String message = (String.format("Pedido de exame cancelado!\nMédico: %s\nExame: %s\n"
								+ "\nRecomendação: %s",e.getMedico().getCrm(),e.getExame(),
								e.getRecomendacao()));
						try {
							EmailSender.sendEmail(info.getSmtpHost(),info.getSmtpPorta(),info.getUser(),info.getSenha(),info.getFrom(),
									e.getPaciente().getEmail(),"Exame Cancelado!", message);
						} catch (EmailException i) {
							i.printStackTrace();
						}
					}
				}
				url = "menuprincipalmedico.jsp";
			}
				
			//Docente
			if(user.getTipo() == 2) 
				url = "menuprincipaldocente.jsp";
			//Residente
			if(user.getTipo() == 3) 
				url = "menuprincipalresidente.jsp";
		}
		return url;
	}

}
