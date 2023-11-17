package com.ejemplousuarios.ejemplousuarios.dao;

import com.ejemplousuarios.ejemplousuarios.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();
    void eliminar(int id);
    void registrar(Usuario usuario);
    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
    //String verificarCredenciales(Usuario usuario);

}
