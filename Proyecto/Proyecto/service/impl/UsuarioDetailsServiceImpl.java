package Proyecto.Proyecto.service.impl;

import Proyecto.Proyecto.service.UsuarioDetailsService; // Asegúrate de que esta interfaz exista
import Proyecto.Proyecto.dao.UsuarioDao; // Asegúrate de que esta sea la ruta correcta a tu DAO
import Proyecto.Proyecto.domain.Usuario; // Asegúrate de que esta sea la ruta correcta a tu entidad Usuario
import Proyecto.Proyecto.domain.Rol; // Asegúrate de que esta sea la ruta correcta a tu entidad Rol
import jakarta.servlet.http.HttpSession; // Este import es correcto si lo usas
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*; // Importa User y UserDetailsService
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService") // Asegúrate de que este nombre de bean sea 'userDetailsService'
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session; // Usar HttpSession en un UserDetailsService puede ser problemático para la seguridad
                                // si se usa para almacenar datos de usuario que afecten la autenticación.
                                // Si solo es para guardar la sesión del usuario logueado después de la autenticación
                                // (ej. para mostrar su nombre), puede estar bien.
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario en la tabla
        Usuario usuario = usuarioDao.findByUsername(username);

        // Si no existe el usuario lanza una excepción
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username); // Mensaje más descriptivo
        }
        
        // Si está acá es porque existe el usuario... sacamos los roles que tiene
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {   
            roles.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre())); // Los roles deben tener el prefijo "ROLE_"
        }
        
        // Se devuelve User (clase de UserDetails de Spring Security)
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isActivo(), true, true, true, roles);
        // Asegúrate de que tu entidad Usuario tenga un campo 'activo' (boolean isActivo() o getActivo())
        // Si no lo tienes, puedes poner 'true' fijo: new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, roles);
    }
}