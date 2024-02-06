package cfs.laudos.cardiologia.modelo;

public class Laudo {
	private Long idExame;
	private MedicoResidente medico;
	private String laudo;
	private String diagnostico;
	private String situacao;
	public static final String LAUDO_PROVISORIO = "Laudo prosivósrio", LAUDO_DEFINITIVO = "Laudo definitivo";
	public Long getIdExame() {
		return idExame;
	}
	public void setIdExame(Long idExame) {
		this.idExame = idExame;
	}
	public MedicoResidente getMedico() {
		return medico;
	}
	public void setMedico(MedicoResidente medico) {
		this.medico = medico;
	}
	public String getLaudo() {
		return laudo;
	}
	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	@Override
	public String toString() {
		return String.format("Laudo [idExame=%s, medico=%s, laudo=%s, diagnostico=%s, situacao=%s]", idExame, medico,
				laudo, diagnostico, situacao);
	}
	
}
