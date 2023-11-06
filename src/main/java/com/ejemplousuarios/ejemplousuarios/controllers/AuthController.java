package com.ejemplousuarios.ejemplousuarios.controllers;

import com.ejemplousuarios.ejemplousuarios.dao.UsuarioDao;
import com.ejemplousuarios.ejemplousuarios.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(path = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        if(usuarioDao.verificarCredenciales(usuario)) {
            return "OK";
        } else {
            return "FAIL!!!";
        }
    }
}
