package com.coutomariel.osworks.api.model;

public class OrdemServicoInput {

	private String descricao;
	private String preco;
	private ClienteIdImput cliente;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public ClienteIdImput getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdImput cliente) {
		this.cliente = cliente;
	}

}
