package com.ejemplousuarios.ejemplousuarios.controllers;

import com.ejemplousuarios.ejemplousuarios.dao.UsuarioDao;
import com.ejemplousuarios.ejemplousuarios.models.Usuario;
import com.ejemplousuarios.ejemplousuarios.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(path = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if(usuarioLogueado != null) {

            // Se retorna el tocke + info que se requiera
            // Se agrega jwt
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuario.getEmail());

            return tokenJwt;
        }
            return "FAIL!!!";

    }
}
