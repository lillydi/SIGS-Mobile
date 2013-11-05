package com.example.loginws;

/* Teste 4 */

import java.sql.Date;

public class Familia {
	private long id;
	private String nome_titular;
	private String data_nascimento_titular;
	private String endereco;
	private String telefone_titular;
	private String CEP;
	private String bairro;
	private String rg_titular;
	private String cpf_titular;
	private Boolean lock;
	private Double lon;
	private Double lat;
	
	public Familia(Long id, String nome, String rg, String cpf, String data, String telefone, 
			String cep, String endereco, String bairro, Double lat, Double longi){
		this.id = id;
		nome_titular = nome;
		rg_titular = rg;
		cpf_titular = cpf;
		data_nascimento_titular = data;
		telefone_titular = telefone;
		CEP = cep;
		this.endereco = endereco;
		this.bairro = bairro;	
		this.lat = lat;
		lon = longi;
	}
	public Familia(){
		
	}
	
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome_titular() {
		return nome_titular;
	}
	public void setNome_titular(String nome_titular) {
		this.nome_titular = nome_titular;
	}
	public String getData_nascimento_titular() {
		return data_nascimento_titular;
	}
	public void setData_nascimento_titular(String data_nascimento_titular) {
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

}
