package com.example.loginws;

import java.sql.Date;

public class Familia {
	private int id;
	private String nome_titular;
	private Date data_nascimento_titular;
	private String endereco;
	private String telefone_titular;
	private String CEP;
	private String bairro;
	private String rg_titular;
	private String cpf_titular;
	private Boolean lock;
	private String localizacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome_titular() {
		return nome_titular;
	}
	public void setNome_titular(String nome_titular) {
		this.nome_titular = nome_titular;
	}
	public Date getData_nascimento_titular() {
		return data_nascimento_titular;
	}
	public void setData_nascimento_titular(Date data_nascimento_titular) {
		this.data_nascimento_titular = data_nascimento_titular;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone_titular() {
		return telefone_titular;
	}
	public void setTelefone_titular(String telefone_titular) {
		this.telefone_titular = telefone_titular;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRg_titular() {
		return rg_titular;
	}
	public void setRg_titular(String rg_titular) {
		this.rg_titular = rg_titular;
	}
	public String getCpf_titular() {
		return cpf_titular;
	}
	public void setCpf_titular(String cpf_titular) {
		this.cpf_titular = cpf_titular;
	}
	public Boolean getLock() {
		return lock;
	}
	public void setLock(Boolean lock) {
		this.lock = lock;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

}
