package produto.produto.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import produto.produto.model.Produto;
import produto.produto.repository.ProdutoRepository;
import produto.produto.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@Api(value = "API de cadastro de produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private ProdutoService servico;
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Retorna lista de produto")
	public List<Produto> index() {
		return repo.findAll();
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Registra um  produto")
	public Produto save (@Valid  @RequestBody Produto p){
		return servico.Salvar(p);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um  produto")
	public ResponseEntity<Produto> buscar(@PathVariable long id) {
		Optional<Produto> get = repo.findById(id);
		
		if(get.isPresent()) {
			
			return ResponseEntity.ok(get.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "atualizar um produto")
	public ResponseEntity<Produto> atualizar(@PathVariable long id,@Valid @RequestBody Produto p){
		
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		p.setId(id);
		servico.Salvar(p);
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Apaga um  produto")
	public ResponseEntity<Produto> apagar(@PathVariable long id){
		
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		servico.apagar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	

}
