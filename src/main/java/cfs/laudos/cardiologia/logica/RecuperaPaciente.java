package cfs.laudos.cardiologia.logica;

import java.io.IOException;

import cfs.laudos.cardiologia.dao.PacienteDAO;
import cfs.laudos.cardiologia.modelo.Paciente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecuperaPaciente implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		Paciente paciente = new Paciente();
		paciente.setCpf(cpf);
		
		PacienteDAO dao = new PacienteDAO();
		paciente = dao.recuperaPaciente(paciente);
		
		String url;
		if(paciente!=null) {
			req.setAttribute("paciente", paciente);
			url = "pacienteexame.jsp";
		}else url = "buscapaciente.jsp";
		
		return url;
	}

	
}
