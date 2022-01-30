package com.blog.pessoal.blogpessoal.Repository;

import java.util.List;

import com.blog.pessoal.blogpessoal.Model.Postagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
                //após list, inserir o nome da MODEL
    public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
}
