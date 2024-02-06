package cfs.laudos.cardiologia.modelo;

import java.util.Calendar;

public class Paciente {
	public static final String FEMININO = "Feminino";
	public static final String MASCULINO = "Masculino";
	private String cpf;
	private String nome;
	private String email;
	private String sexo;
	private Long idade;
	private Calendar dataNascimento;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Long getIdade() {
		return idade;
	}
	public void setIdade(Long idade) {
		this.idade = idade;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public static String getFeminino() {
		return FEMININO;
	}
	public static String getMasculino() {
		return MASCULINO;
	}
	@Override
	public String toString() {
		return String.format("Paciente [cpf=%s, nome=%s, email=%s, sexo=%s, idade=%s, dataNascimento=%s]", cpf, nome,
				email, sexo, idade, dataNascimento.getTime());
	}
	
	
	
}
