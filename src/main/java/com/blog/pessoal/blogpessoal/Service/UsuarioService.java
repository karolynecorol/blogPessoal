package com.blog.pessoal.blogpessoal.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import com.blog.pessoal.blogpessoal.Model.Login;
import com.blog.pessoal.blogpessoal.Model.User;
import com.blog.pessoal.blogpessoal.Repository.UserRepository;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UserRepository repository;
	
	public User CadastrarUsuario(User usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
	
	return repository.save(usuario);
	}
	
	public Optional<Login> Logar(Optional<Login> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
		
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setId(user.get().getId());
				user.get().setNome(user.get().getNome());
				user.get().setFoto(user.get().getFoto());
				user.get().setToken(generatorBasicToken(user.get().getUsuario(),user.get().getSenha()));
				user.get().setSenha(user.get().getSenha());
				user.get().setTipo(user.get().getTipo());
				
				return user;
			}
		}
		return null;
	}

	private String generatorBasicToken(String usuario, String senha) {
		return null;
	}
	
}