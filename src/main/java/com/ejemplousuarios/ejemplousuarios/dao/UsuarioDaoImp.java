package com.ejemplousuarios.ejemplousuarios.dao;

import com.ejemplousuarios.ejemplousuarios.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        return entityManager.createQuery("FROM Usuario").getResultList();
    }

    @Override
    public void eliminar(int id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }
}
