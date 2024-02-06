package cfs.laudos.cardiologia.modelo;

public class MedicoResidente {
	private String crm;
	private String nome;
	private String matricula;
	
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
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	@Override
	public String toString() {
		return String.format("MedicoResidente [crm=%s, nome=%s, matricula=%s]", crm, nome, matricula);
	}
	
	
}
