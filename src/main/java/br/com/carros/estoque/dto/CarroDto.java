package br.com.carros.estoque.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.carros.estoque.modelo.Carro;

public class CarroDto {
	
	private Long id;
	private String chassi;
	private String nome;
	private String marca;
	private String cor;
	private Double valor;
	private int anoFabricacao;
	
	public CarroDto(Carro carro) {
		this.id = carro.getId();
		this.chassi = carro.getChassi();
		this.nome = carro.getNome();
		this.marca = carro.getMarca();
		this.cor = carro.getCor();
		this.valor = carro.getValor();
		this.anoFabricacao = carro.getAnoFabricacao();
	}
	
	public Long getId() {
		return id;
	}
	public String getChassi() {
		return chassi;
	}
	public String getNome() {
		return nome;
	}
	public String getMarca() {
		return marca;
	}
	public String getCor() {
		return cor;
	}
	public Double getValor() {
		return valor;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}	
	
	public static List<CarroDto> converter(List<Carro> carro) {
		return carro.stream().map(CarroDto::new).collect(Collectors.toList());
	}
}