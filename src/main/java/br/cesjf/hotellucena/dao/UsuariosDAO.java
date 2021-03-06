package br.cesjf.hotellucena.dao;

import br.cesjf.hotellucena.model.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.cesjf.hotellucena.util.PersistenceUtil;

public class UsuariosDAO {

    public static UsuariosDAO usuarioDAO;

    public static UsuariosDAO getInstance() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuariosDAO();
        }
        return usuarioDAO;
    }

    public Usuarios buscar(String nomeUsuario, String emailUsuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Usuarios a where a.nomeUsuario =:nomeUsuario and a.emailUsuario =:emailUsuario");
        query.setParameter("nomeUsuario", nomeUsuario);
        query.setParameter("emailUsuario", emailUsuario);

        List<Usuarios> usuarios = query.getResultList();
        if (usuarios != null && usuarios.size() > 0) {
            return usuarios.get(0);
        }

        return null;
    }

    public List<Usuarios> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Usuarios As a");
        return query.getResultList();
    }

    public void remover(Usuarios usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public Usuarios persistir(Usuarios usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            System.out.println("Registro Usuarios gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(" delete from Usuarios ");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public Usuarios login(Usuarios usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Usuarios a where a.emailUsuario =:emailUsuario");
        query.setParameter("emailUsuario", usuario.getEmailUsuario());

        List<Usuarios> usuarios = query.getResultList();
        if (usuarios != null && usuarios.size() > 0) {
            return usuarios.get(0);
        }

        return null;
    }
}
