package cfs.laudos.cardiologia.logica;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cfs.laudos.cardiologia.dao.ExameDAO;
import cfs.laudos.cardiologia.dao.MedicoResidenteDAO;
import cfs.laudos.cardiologia.modelo.Exame;
import cfs.laudos.cardiologia.modelo.MedicoResidente;
import cfs.laudos.cardiologia.modelo.PdfCreator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RealizaExame implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String exame = req.getParameter("exame");
		String data = req.getParameter("data");
		String hora = req.getParameter("hora");
		String medico = req.getParameter("residente");

		//Formatar a data
		Calendar dataFormatada = Calendar.getInstance();
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			dataFormatada.setTime(date);
		} catch (ParseException e) {
			System.out.println("Ocorreu um erro na conversão da data");
			e.printStackTrace();
		} 

		//Formata hora
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); 
		LocalTime horaFormatada = LocalTime.parse(hora, formatter);

		//Médico que realizou o exame
		MedicoResidente residente = new MedicoResidente();
		residente.setCrm(medico);
		residente = (new MedicoResidenteDAO()).recuperaMedicoResidente(residente);
		
		Exame exameRealizado = new Exame();
		ExameDAO dao = new ExameDAO();
		exameRealizado.setId(Long.parseLong(exame));
		exameRealizado = dao.recuperaExameId(exameRealizado);
		exameRealizado.setDataRealizacao(dataFormatada);
		exameRealizado.setHora(horaFormatada);
		exameRealizado.setMedicoResitente(residente);
		exameRealizado.setSituacao(Exame.AGUARDANDO_LAUDO);
		
		//Gera um pdf com as imagens e salvar em uma pasta
		String nomeArquivo = (String.format("%d-%s.pdf", exameRealizado.getId(),exameRealizado.getExame()));
		PdfCreator.geraPdf(exameRealizado.getExame(), nomeArquivo);
		
		dao.realizacaoExame(exameRealizado);
		req.setAttribute("exame", exameRealizado);
		String url = "pedidoexamefeito.jsp";

		return url;
	}

}
