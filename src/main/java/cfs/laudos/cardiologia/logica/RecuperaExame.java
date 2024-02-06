package cfs.laudos.cardiologia.logica;

import java.io.IOException;

import cfs.laudos.cardiologia.dao.ExameDAO;
import cfs.laudos.cardiologia.dao.LaudoDAO;
import cfs.laudos.cardiologia.dao.PacienteDAO;
import cfs.laudos.cardiologia.modelo.Exame;
import cfs.laudos.cardiologia.modelo.Laudo;
import cfs.laudos.cardiologia.modelo.Paciente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecuperaExame implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Exame pedidoExame = new Exame();
		pedidoExame.setId(Long.parseLong(id));
		pedidoExame = (new ExameDAO()).recuperaExameId(pedidoExame);
		
		String url = null;
		req.setAttribute("exame", pedidoExame);
		if(pedidoExame.getSituacao().equals(Exame.AGUARDANDO_EXAME) == true) {
			url = "realizaexame.jsp";
		}else if(pedidoExame.getSituacao().equals(Exame.AGUARDANDO_LAUDO)==true) {
			url = "gerarlaudo.jsp";
		}else if(pedidoExame.getSituacao().equals(Exame.LAUDO_REALIZADO)==true) {
			Laudo laudo = new Laudo();
			laudo.setIdExame(pedidoExame.getId());
			laudo = (new LaudoDAO()).recuperaLaudo(laudo);
			req.setAttribute("laudo", laudo);
			url = "avaliarlaudo.jsp";
		}
		
		return url;
	}

}
