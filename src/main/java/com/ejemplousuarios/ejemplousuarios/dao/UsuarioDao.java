package com.ejemplousuarios.ejemplousuarios.dao;

import com.ejemplousuarios.ejemplousuarios.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();
    void eliminar(int id);
    void registrar(Usuario usuario);
    boolean verificarCredenciales(Usuario usuario);
    //String verificarCredenciales(Usuario usuario);

}
