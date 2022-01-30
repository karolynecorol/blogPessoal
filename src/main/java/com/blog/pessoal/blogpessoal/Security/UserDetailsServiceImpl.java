package com.blog.pessoal.blogpessoal.Security;

import  java.util.Optional;

import com.blog.pessoal.blogpessoal.Model.User;
import com.blog.pessoal.blogpessoal.Repository.UserRepository;

import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.security.core.userdetails.UserDetails;
import  org.springframework.security.core.userdetails.UserDetailsService;
import  org.springframework.security.core.userdetails.UsernameNotFoundException;
import  org.springframework.stereotype.Service; 


@Service 
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired 
    private  UserRepository  userRepository; 

    @Override 
    public  UserDetails  loadUserByUsername(String  userName)  throws  UsernameNotFoundException  { 
        Optional<User>  user  =  userRepository.findByUsuario(userName); 
        user.orElseThrow(()  ->  new  UsernameNotFoundException(userName  +  "  not  found.")); 
        return  user.map(UserDetailsImpl::new).get(); 
    } 
} 