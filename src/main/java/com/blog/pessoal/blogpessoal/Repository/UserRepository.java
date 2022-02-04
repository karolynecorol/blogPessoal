package com.blog.pessoal.blogpessoal.Repository;

import java.util.List;
import java.util.Optional;

import com.blog.pessoal.blogpessoal.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByNomeAndUsuarioContainingIgnoreCase (String nome, String  usuario);

    public Optional<User> findByUsuario(String userName);
    
    public List <User> findAllByNomeContainingIgnoreCase(String nome);

}


