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

    @RequestMapping(value = "usuario/{id}")
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

    @RequestMapping(path = "usuarios", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();

        /*List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();
        usuario.setId(123);
        usuario.setNombre("Pepe");
        usuario.setApellido("Perez");
        usuario.setTelefono("091123456");
        usuario.setEmail("pepeperez@usuario.com");
        usuario.setPassword("123456");

        Usuario usuario2 = new Usuario();
        usuario2.setId(456);
        usuario2.setNombre("José");
        usuario2.setApellido("González");
        usuario2.setTelefono("091111222");
        usuario2.setEmail("josegonzalez@usuario.com");
        usuario2.setPassword("456");

        Usuario usuario3 = new Usuario();
        usuario3.setId(789);
        usuario3.setNombre("Luis");
        usuario3.setApellido("Gómez");
        usuario3.setTelefono("091333444");
        usuario3.setEmail("luisgomez@usuario.com");
        usuario3.setPassword("789");

        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        return usuarios;*/

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
