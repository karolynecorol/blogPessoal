package com.blog.pessoal.blogpessoal.Controller;

import java.util.List;

import com.blog.pessoal.blogpessoal.Model.User;
import com.blog.pessoal.blogpessoal.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private UserRepository repository;

    @GetMapping("/all") //o /all é uma URI de um determinado endpoint
    public ResponseEntity<List<User>> getAll () {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}") //o {id} é referência ao parâmetro a ser pesquisado (no caso, ID)
    public ResponseEntity<User> getById(@PathVariable long id) {
        return repository.findById(id)
            .map(resp -> ResponseEntity.ok(resp))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{nome}/{usuario}")
    public ResponseEntity<List<User>> getByUser(
            @PathVariable(value="nome") String nome, 
            @PathVariable(value="usuario")String usuario)  {
        return ResponseEntity.ok(repository.findAllByNomeAndUsuarioContainingIgnoreCase(nome, usuario));
    }

    @PostMapping("/new")
    public ResponseEntity<User> newUser(@RequestBody User newUser){
        return ResponseEntity.status(201).body(repository.save(newUser));
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User newUser) {
        return ResponseEntity.status(200).body(repository.save(newUser));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        repository.deleteById(id);
    }

}
