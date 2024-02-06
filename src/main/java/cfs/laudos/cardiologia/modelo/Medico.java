package cfs.laudos.cardiologia.modelo;

public class Medico {
	private String crm;
	private String nome;
	
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
	@Override
	public String toString() {
		return String.format("Medico [crm=%s, nome=%s]", crm, nome);
	}
	
	
}
