package br.com.carros.estoque.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.estoque.dto.CarroDto;
import br.com.carros.estoque.exception.ResourceNotFoundException;
import br.com.carros.estoque.modelo.Carro;
import br.com.carros.estoque.repository.CarroRepository;

@RestController
@RequestMapping("/api/carros")
public class CarrosController {
	
	@Autowired
	private CarroRepository carroRepository;
	
	@GetMapping
	public List<CarroDto> lista(){
		List<Carro> carros = carroRepository.findAll();
		return CarroDto.converter(carros);
	}
	
	@GetMapping("/{id}")
	public Carro listaPeloId(@PathVariable (value = "id") long carroId) {
		return this.carroRepository.findById(carroId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado pelo id  :" + carroId));
	}
	
	@GetMapping("/nome")
	public List<CarroDto> listaPeloNome(@RequestParam("nome") String carroNome){
		List<Carro> carros = carroRepository.findByNome(carroNome);
				return CarroDto.converter(carros);
	} 
	
	@GetMapping("/marca")
	public List<CarroDto> listaPelaMarca(@RequestParam("marca") String carroMarca){
		List<Carro> carros = carroRepository.findByMarca(carroMarca);
				return CarroDto.converter(carros);
	} 
	
	@GetMapping("/cor")
	public List<CarroDto> listaPelaCor(@RequestParam("cor") String carroCor){
		List<Carro> carros = carroRepository.findByCor(carroCor);
				return CarroDto.converter(carros);
	} 
	
	@GetMapping("/caro")
	public List<CarroDto> listaCarroCaro(){
		List<Carro> carros = carroRepository.findByValorCaro();
				return CarroDto.converter(carros);
	} 
	
	@GetMapping("/barato")
	public List<CarroDto> listaCarroBarato(){
		List<Carro> carros = carroRepository.findByValorBarato();
				return CarroDto.converter(carros);
	} 
	
	@GetMapping("/ordenanome")
	public List<CarroDto> OrdenaPorNome() {
		List<Carro> carros = carroRepository.orderByNome();
		return CarroDto.converter(carros);
	}

	@GetMapping("/ordenavalor")
	public List<CarroDto> OrdenaPorValor() {
		List<Carro> carros = carroRepository.orderByValor();
		return CarroDto.converter(carros);
	}

	@GetMapping("/ordenaano")
	public List<CarroDto> OrdenaPorAno() {
		List<Carro> carros = carroRepository.orderByAno();
		return CarroDto.converter(carros);
	}

	@PostMapping
	public Carro cria(@Valid @RequestBody Carro carro) {
		return this.carroRepository.save(carro);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Carro> atualizaCarro(@PathVariable(value = "id") Long carroId,
			@Valid @RequestBody Carro userDetails) throws ResourceNotFoundException {
		Carro carro = carroRepository.findById(carroId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado :: " + carroId));

		carro.setChassi(userDetails.getChassi());
		carro.setNome(userDetails.getNome());
		carro.setMarca(userDetails.getMarca());
		carro.setCor(userDetails.getCor());
		carro.setValor(userDetails.getValor());
		carro.setAnoFabricacao(userDetails.getAnoFabricacao());
		final Carro atualizaCarro = carroRepository.save(carro);
		return ResponseEntity.ok(atualizaCarro);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> removeCarro(@PathVariable(value = "id") Long carroId) throws ResourceNotFoundException {
		Carro carro = carroRepository.findById(carroId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado :: " + carroId));

		carroRepository.delete(carro);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}