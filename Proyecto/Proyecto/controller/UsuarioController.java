/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Proyecto.Proyecto.controller;

import Proyecto.Proyecto.domain.Usuario;
import Proyecto.Proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuario", usuarios);
        model.addAttribute("totalUsuario", usuarios.size());
        return "/usuario/listado";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

@PostMapping("/guardar")
public String usuarioGuardar(Usuario usuario) {
    usuarioService.save(usuario, true); // Guarda con rol USER por defecto

    // Obtener el usuario que está autenticado actualmente
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // Buscar si tiene el rol ADMIN
    boolean esAdmin = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .anyMatch(rol -> rol.equals("ROLE_ADMIN"));

    // Redirigir según el rol actual del usuario autenticado
    return esAdmin ? "redirect:/usuario/listado" : "redirect:/inicio_Sesion";
}

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }
}

