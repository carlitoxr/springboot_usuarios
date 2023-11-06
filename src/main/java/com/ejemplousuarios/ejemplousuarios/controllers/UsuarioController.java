package com.ejemplousuarios.ejemplousuarios.controllers;

import com.ejemplousuarios.ejemplousuarios.dao.UsuarioDao;
import com.ejemplousuarios.ejemplousuarios.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "prueba")
    public List<String> prueba() {
        return List.of("Manzana", "Naranja", "Pera");
    }

    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Pepe");
        usuario.setApellido("Perez");
        usuario.setTelefono("091123456");
        usuario.setEmail("pepeperez@usuario.com");
        usuario.setPassword("123456");
        return usuario;
    }

    @RequestMapping(path = "api/usuarios", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable int id) {
        usuarioDao.eliminar(id);
    }

/*
    @RequestMapping(value = "usuario/{id}")
    public Usuario editar(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Pepe");
        usuario.setApellido("Perez");
        usuario.setTelefono("091123456");
        usuario.setEmail("pepeperez@usuario.com");
        usuario.setPassword("123456");
        return usuario;
    }

    @RequestMapping(value = "usuario/{id}")
    public Usuario eliminar(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Pepe");
        usuario.setApellido("Perez");
        usuario.setTelefono("091123456");
        usuario.setEmail("pepeperez@usuario.com");
        usuario.setPassword("123456");
        return usuario;
    }

    @RequestMapping(value = "usuario/{id}")
    public Usuario buscar(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Pepe");
        usuario.setApellido("Perez");
        usuario.setTelefono("091123456");
        usuario.setEmail("pepeperez@usuario.com");
        usuario.setPassword("123456");
        return usuario;
    }
*/
}
