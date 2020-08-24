package com.coutomariel.osworks.api.model;

public class OrdemServicoInput {

	private String descricao;
	private String preco;
	private ClienteIdInput cliente;

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

	public ClienteIdInput getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdInput cliente) {
		this.cliente = cliente;
	}

}
