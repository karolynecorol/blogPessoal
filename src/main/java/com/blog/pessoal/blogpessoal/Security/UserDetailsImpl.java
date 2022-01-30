package com.blog.pessoal.blogpessoal.Security;

import java.util.Collection;

import com.blog.pessoal.blogpessoal.Model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

    public class UserDetailsImpl implements UserDetails {  
        private static final long serialVersionUID = 1L;   
        private String usuario;  
        private String senha;  

        public UserDetailsImpl(User usuario) {   
            this.usuario = usuario.getUsuario(); 
            this.senha = usuario.getSenha(); 
        }   
      
        public UserDetailsImpl () {}

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return senha;
        }

        @Override
        public String getUsername() {
            return usuario;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }