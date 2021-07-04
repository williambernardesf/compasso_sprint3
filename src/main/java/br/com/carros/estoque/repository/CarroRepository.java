package br.com.carros.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.carros.estoque.modelo.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{
	
	List<Carro> findByNome(String nome);
	List<Carro> findByMarca(String marca);
	List<Carro> findByCor(String cor);
	
	@Query("SELECT c FROM Carro c ORDER BY c.nome DESC")
	List<Carro> orderByNome();
	
	@Query("SELECT c FROM Carro c ORDER BY c.valor DESC")
	List<Carro> orderByValor();
	
	@Query("SELECT c FROM Carro c ORDER BY c.anoFabricacao DESC")
	List<Carro> orderByAno();
	
	@Query("SELECT c FROM Carro c WHERE valor = (SELECT max(valor) FROM Carro)")
	List<Carro> findByValorCaro();
	
	@Query("SELECT c FROM Carro c WHERE valor = (SELECT min(valor) FROM Carro)")
	List<Carro> findByValorBarato();
}