package com.ejemplousuarios.ejemplousuarios.dao;

import com.ejemplousuarios.ejemplousuarios.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public boolean verificarCredenciales(Usuario usuario) {
        /*String query = "FROM Usuario WHERE email = :email AND password = :password";
            Se agrega argon
         */
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                //.setParameter("password", usuario.getPassword())
                .getResultList();

        if(lista.isEmpty())
            return false;

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //argon2.verify(lista.get(0).getPassword(), usuario.getPassword());

        //return !lista.isEmpty();
        return argon2.verify(lista.get(0).getPassword(), usuario.getPassword());
    }
}
