package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Pessoa;
import com.victor.HelpDesk.domain.repository.PessoaRepository;
import com.victor.HelpDesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceipml implements UserDetailsService {
    @Autowired
    private PessoaRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = repository.findByEmail(email);
        if (user.isPresent()){
            return new UserSS(user.get().getId(),user.get().getEmail(),user.get().getSenha(),user.get().getPerfils());
        }
        throw new UsernameNotFoundException(email);
    }
}
