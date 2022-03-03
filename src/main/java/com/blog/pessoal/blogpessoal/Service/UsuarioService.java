package com.blog.pessoal.blogpessoal.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import com.blog.pessoal.blogpessoal.Model.Login;
import com.blog.pessoal.blogpessoal.Model.User;
import com.blog.pessoal.blogpessoal.Repository.UserRepository;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementação do UsuarioService com os métodos relacionados com a model Usuario
 * 
 * @author Karolyne Corol
 * @date 03/03/2022
 * @version 1.0
 * 
 */

@Service
public class UsuarioService {

	private @Autowired UserRepository repository;

	//Cadastrar Usuário
	public Optional<User> cadastrarUsuario(User user) {

		if (repository.findByUsuario(user.getUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			user.setSenha(criptografarSenha(user.getSenha()));

		return Optional.of(repository.save(user));
	}
	
	//Atualizar Usuário
	public Optional<User> atualizarUsuario(User user) {
		if (repository.findById(user.getId()).isPresent()) {
			Optional<User> buscaUsuario = repository.findByUsuario(user.getUsuario());
			if (buscaUsuario.isPresent()) {
				if (buscaUsuario.get().getId() != user.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}
			user.setSenha(criptografarSenha(user.getSenha()));
			return Optional.of(repository.save(user));
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
	}
	
	//Logar Usuário
	public Optional<Login> logarUsuario(Optional<Login> login) {
		Optional<User> user = repository.findByUsuario(login.get().getUsuario());

		if (user.isPresent()) {
			if (compararSenhas(login.get().getSenha(), user.get().getSenha())) {

				login.get().setId(user.get().getId());
				login.get().setNome(user.get().getNome());
				login.get().setFoto(user.get().getFoto());
				login.get().setToken(generatorBasicToken(login.get().getUsuario(), login.get().getSenha()));
				login.get().setSenha(user.get().getSenha());
				login.get().setTipo(user.get().getTipo());

				return login;
			}
		}
		throw new ResponseStatusException(
			HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
	}
	
	//Criptografar Senha
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		return senhaEncoder;
	}
	
	//Comparar Senhas
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	//Gerador de token
	private String generatorBasicToken(String usuario, String password) {
		String structure = usuario + ":" + password;
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(structureBase64);
	}
}