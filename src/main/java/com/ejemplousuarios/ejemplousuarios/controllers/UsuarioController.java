package com.ejemplousuarios.ejemplousuarios.controllers;

import com.ejemplousuarios.ejemplousuarios.dao.UsuarioDao;
import com.ejemplousuarios.ejemplousuarios.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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

    @RequestMapping(path = "api/usuarios", method = RequestMethod.POST)
    @ResponseBody
    public void registrarUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
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
