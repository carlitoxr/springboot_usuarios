package com.ejemplousuarios.ejemplousuarios.controllers;

import com.ejemplousuarios.ejemplousuarios.dao.UsuarioDao;
import com.ejemplousuarios.ejemplousuarios.models.Usuario;
import com.ejemplousuarios.ejemplousuarios.utils.JWTUtil;
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

    @Autowired
    private JWTUtil jwtUtil;

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
    //public List<Usuario> getUsuarios() {
    // Agregando la verificación del tocken jwt
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        if(!validarToken(token)) {
            return null;
        }

        return usuarioDao.getUsuarios();
    }

    public boolean validarToken(String token) {
        // Se extrae la id (key) del usuario
        String usuarioId = jwtUtil.getKey(token);

        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,
                         @PathVariable int id) {

        if(!validarToken(token)) {
            return;
        }

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
