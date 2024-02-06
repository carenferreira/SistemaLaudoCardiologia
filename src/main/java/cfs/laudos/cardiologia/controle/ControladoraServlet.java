package cfs.laudos.cardiologia.controle;

import java.io.IOException;

import cfs.laudos.cardiologia.logica.Logica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controladora")
public class ControladoraServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeClasse = "cfs.laudos.cardiologia.logica." 
				+ request.getParameter("logica");
		Class classe;
		String url="";
		try {
			classe = Class.forName(nomeClasse);
			try {
				Logica logica = (Logica) classe.newInstance();
				url = logica.executa(request, response);
					
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
