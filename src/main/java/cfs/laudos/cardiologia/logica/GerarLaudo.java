package cfs.laudos.cardiologia.logica;

import java.io.IOException;

import cfs.laudos.cardiologia.dao.ExameDAO;
import cfs.laudos.cardiologia.dao.LaudoDAO;
import cfs.laudos.cardiologia.dao.MedicoResidenteDAO;
import cfs.laudos.cardiologia.modelo.Exame;
import cfs.laudos.cardiologia.modelo.Laudo;
import cfs.laudos.cardiologia.modelo.MedicoResidente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GerarLaudo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idExame = req.getParameter("exame");
		String medico = req.getParameter("medico");
		String descricao = req.getParameter("descricao");
		String diagnostico = req.getParameter("diagnostico");

		MedicoResidente medicoResidente = new MedicoResidente();
		medicoResidente.setCrm(medico);
		medicoResidente = (new MedicoResidenteDAO()).recuperaMedicoResidente(medicoResidente);
		
		Laudo laudo = new Laudo();
		laudo.setIdExame(Long.parseLong(idExame));
		laudo.setMedico(medicoResidente);
		laudo.setLaudo(descricao);
		laudo.setDiagnostico(diagnostico);
		laudo.setSituacao(Laudo.LAUDO_PROVISORIO);
		
		LaudoDAO dao = new LaudoDAO();
		dao.adiciona(laudo);
		
		Exame exame = new Exame();
		ExameDAO exameDao =new ExameDAO(); 
		exame.setId(Long.parseLong(idExame));
		exame = exameDao.recuperaExameId(exame);
		exame.setSituacao(Exame.LAUDO_REALIZADO);
		exameDao.geraLaudo(exame);
		
		req.setAttribute("exame", exame);
		req.setAttribute("laudo", laudo);
		String url = "pedidoexamefeito.jsp";

		return url;
	}

}
