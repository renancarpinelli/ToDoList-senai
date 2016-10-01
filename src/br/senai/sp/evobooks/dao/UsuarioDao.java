package br.senai.sp.evobooks.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.evobooks.model.Usuario;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Usuario usuario) {
		manager.persist(usuario);
	}

	@Transactional
	public void excluir(Long idUsuario) {
		Usuario usuario = manager.find(Usuario.class, idUsuario);
		manager.remove(usuario);
	}

	public List<Usuario> listar() {
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u order by u.login", Usuario.class);
		return query.getResultList();
	}

	public Usuario buscar(Long idUsuario) {
		return manager.find(Usuario.class, idUsuario);
	}
	
	public Usuario logar(Usuario usuario){
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u where u.login = :login and u.senha = :senha", Usuario.class);
		query.setParameter("login", usuario.getLogin());
		query.setParameter("senha", usuario.getSenha());
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}  
	}

}
