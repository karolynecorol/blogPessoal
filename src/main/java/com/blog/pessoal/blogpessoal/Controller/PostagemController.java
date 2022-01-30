package com.blog.pessoal.blogpessoal.Controller;

import java.util.List;

import com.blog.pessoal.blogpessoal.Model.Postagem;
import com.blog.pessoal.blogpessoal.Repository.PostagemRepository;

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
@RequestMapping("/postagem")
@CrossOrigin("*")

public class PostagemController {
    
    @Autowired
    private PostagemRepository repository;

    @GetMapping("/all") //o /all é uma URI de um determinado endpoint
    public ResponseEntity<List<Postagem>> getAll () {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}") //o {id} é referência ao parâmetro a ser pesquisado (no caso, ID)
    public ResponseEntity<Postagem> getById(@PathVariable long id) {
        return repository.findById(id)
            .map(resp -> ResponseEntity.ok(resp))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<Postagem>> getByPostagem(@PathVariable String titulo){
            return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping("/new")
    public ResponseEntity<Postagem> newPostagem(@RequestBody Postagem newPostagem){
        return ResponseEntity.status(201).body(repository.save(newPostagem));
    }

    @PutMapping("/edit")
    public ResponseEntity<Postagem> editPostagem(@RequestBody Postagem newPostagem) {
        return ResponseEntity.status(200).body(repository.save(newPostagem));
    }

    @DeleteMapping("/delete/{id}")
    public void deletePostagem(@PathVariable long id) {
        repository.deleteById(id);
    }
}
