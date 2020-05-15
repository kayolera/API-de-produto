package produto.produto.controller;

import java.util.ArrayList;
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
import produto.produto.model.User;
import produto.produto.repository.UserRepository;
import produto.produto.service.UserService;

@RestController
@RequestMapping("/users")
@Api(value = "API de Cadastro de user")
@CrossOrigin (origins = "*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService servico;
	
	@GetMapping
	@ApiOperation(value = "Lista todo os usuarios do sistema")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastro do usuario")
	@ResponseStatus(value = HttpStatus.CREATED)
	public User cadastro( @RequestBody @Valid User user) {
		return servico.salvar(user);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Monstra um usuario")
	public ResponseEntity<User> getUser(@PathVariable Integer id){
		Optional<User> lis = userRepository.findById(id);
		if(lis.isPresent()) {
			return ResponseEntity.ok(lis.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza  um usuario")
	public ResponseEntity<User> atualizar(@PathVariable Integer id, @Valid @RequestBody User user){
		if(userRepository.existsById(id)) {
			user.setId(id);
			return ResponseEntity.ok(servico.salvar(user));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/fitros/{nome}")
	@ApiOperation(value = "filtro pelo nome do usuario")
	@ResponseStatus(value = HttpStatus.OK)
	public ArrayList<User> busca(@PathVariable String nome){
		return userRepository.findByNomeContaining(nome);
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Apagam um usuario")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	

}
