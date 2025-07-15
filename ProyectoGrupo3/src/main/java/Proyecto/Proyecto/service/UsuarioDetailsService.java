package Proyecto.Proyecto.service;

import org.springframework.security.core.userdetails.*;

public interface UsuarioDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
