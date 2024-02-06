package cfs.laudos.cardiologia.modelo;

public class MedicoDocente {
	private String crm;
	private String nome;
	public static final String DOUTOR = "Doutor", ASSISTENTE = "Assistente", 
	LIVRE_DOCENTE = "Livre Docente", TITULAR = "Titular";
	
	private String titulacao;
	
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTitulacao() {
		return titulacao;
	}
	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}
	@Override
	public String toString() {
		return String.format("MedicoDocente [crm=%s, nome=%s, titulacao=%s]", crm, nome, titulacao);
	}
	
}
