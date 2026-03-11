package com.victor.HelpDesk.services;

import com.victor.HelpDesk.entity.Pessoa;
import com.victor.HelpDesk.repository.PessoaRepository;
import com.victor.HelpDesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceIpml implements UserDetailsService {
    private final PessoaRepository repository;


    public UserDetailsServiceIpml(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Pessoa user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserSS(
                user.getId(),
                user.getEmail(),
                user.getSenha(),
                user.getPerfils()
        );
    }
}
