package com.blog.pessoal.blogpessoal.Controller;

import java.util.Optional;

import com.blog.pessoal.blogpessoal.Model.Login;
import com.blog.pessoal.blogpessoal.Model.User;
import com.blog.pessoal.blogpessoal.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")

public class UserController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/logar")
    public ResponseEntity<Login> Autentication(@RequestBody Optional<Login> usuario){
		return usuarioService.Logar(usuario).map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

    @PostMapping("/cadastrar")
    public ResponseEntity<User> Post(@RequestBody User usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(usuarioService.CadastrarUsuario(usuario));
    }
}