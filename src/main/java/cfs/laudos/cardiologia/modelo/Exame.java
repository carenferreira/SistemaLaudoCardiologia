package cfs.laudos.cardiologia.modelo;

import java.time.LocalTime;
import java.util.Calendar;

public class Exame {
	private Long id;
	private Paciente paciente;
	private Medico medico;
	private MedicoResidente medicoResitente;
	private String exame;
	private String hipotese;
	private String recomendacao;
	private String situacao;
	private Calendar data;
	private Calendar dataRealizacao;
	private LocalTime hora;
	
	public static final String AGUARDANDO_EXAME = "Aguardando Exame", 
			AGUARDANDO_LAUDO = "Aguardando Laudo",
			LAUDO_REALIZADO = "Laudo Realizado",
			EXAME_CANCELADO = "Exame Cancelado";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public MedicoResidente getMedicoResitente() {
		return medicoResitente;
	}
	public void setMedicoResitente(MedicoResidente medicoResitente) {
		this.medicoResitente = medicoResitente;
	}
	public String getExame() {
		return exame;
	}
	public void setExame(String exame) {
		this.exame = exame;
	}
	public String getHipotese() {
		return hipotese;
	}
	public void setHipotese(String hipotese) {
		this.hipotese = hipotese;
	}
	public String getRecomendacao() {
		return recomendacao;
	}
	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}
	public String getStatus() {
		return situacao;
	}
	public void setStatus(String status) {
		this.situacao = status;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Calendar getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Calendar dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	@Override
	public String toString() {
		return String.format("Exame [paciente=%s, medico=%s, exame=%s, hipotese=%s, recomendacao=%s, status=%s, data=%s, dataRealizacao=%s]",
				paciente, medico, exame, hipotese, recomendacao, situacao, data.getTime(), dataRealizacao.getTime());
	}
	
}
