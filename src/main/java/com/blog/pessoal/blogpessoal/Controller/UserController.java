package com.blog.pessoal.blogpessoal.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.blog.pessoal.blogpessoal.Model.Login;
import com.blog.pessoal.blogpessoal.Model.User;
import com.blog.pessoal.blogpessoal.Repository.UserRepository;
import com.blog.pessoal.blogpessoal.Service.UsuarioService;

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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")

public class UserController {
    
    
	private @Autowired UsuarioService usuarioService;
	
	private @Autowired UserRepository userRepository;
    
	@GetMapping("/all")
	public ResponseEntity <List<User>> getAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable long id) {
		return userRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<User>> getByUsuarios (@PathVariable String nome)  {
		return ResponseEntity.ok(userRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping("/logar")
	public ResponseEntity<Login> authentication(@RequestBody Optional<Login> user) {
		return usuarioService.logarUsuario(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<User> postUsuario(@Valid @RequestBody User user) {
		return usuarioService.cadastrarUsuario(user)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<User> putUsuario(@Valid @RequestBody User user){
		return usuarioService.atualizarUsuario(user)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUsuarios (@PathVariable long id) {
		userRepository.deleteById(id);
	}
}
