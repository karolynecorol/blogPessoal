package com.blog.pessoal.blogpessoal.Controller;

import java.util.List;

import com.blog.pessoal.blogpessoal.Model.Tema;
import com.blog.pessoal.blogpessoal.Repository.TemaRepository;

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
@RequestMapping("/tema")
@CrossOrigin("*")

public class TemaController {
    
    @Autowired
    private TemaRepository repository;

    @GetMapping("/all") //o /all é uma URI de um determinado endpoint
    public ResponseEntity<List<Tema>> getAll () {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}") //o {id} é referência ao parâmetro a ser pesquisado (no caso, ID)
    public ResponseEntity<Tema> getById(@PathVariable long id) {
        return repository.findById(id)
            .map(resp -> ResponseEntity.ok(resp))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/descricao")
    public ResponseEntity<List<Tema>> getByTema(@PathVariable String descricao){
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping("/new")
    public ResponseEntity<Tema> newTema(@RequestBody Tema newTema){
        return ResponseEntity.status(201).body(repository.save(newTema));
    }

    @PutMapping("/edit")
    public ResponseEntity<Tema> editTema(@RequestBody Tema newTema) {
        return ResponseEntity.status(200).body(repository.save(newTema));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTema(@PathVariable long id) {
        repository.deleteById(id);
    }
}
