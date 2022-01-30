package com.blog.pessoal.blogpessoal.Repository;

import java.util.List;

import com.blog.pessoal.blogpessoal.Model.Tema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TemaRepository extends JpaRepository<Tema, Long> {
    public List<Tema> findAllByDescricaoContainingIgnoreCase (String descricao);

}
