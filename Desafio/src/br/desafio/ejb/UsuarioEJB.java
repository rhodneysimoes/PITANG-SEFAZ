package br.desafio.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.desafio.model.Usuario;

@Stateful
public class UsuarioEJB {

	@PersistenceContext
	EntityManager em;

	//Insere novo usuario
	public void insert(Usuario usuario) {		

		em.persist(usuario);

	}

	//Altera o usuario
	public void update(Usuario usuario) {

		em.merge(usuario);

	}

	//Deleta o usuario
	public void delete(Usuario usuario) {

		//Recupera o id antes de deletar
		usuario = load(usuario.getId());
		em.remove(usuario);

	}

	//Recupera usuario pelo id
	public Usuario load(int id) {

		return em.find(Usuario.class, id);

	}

	//Efetua login
	public boolean login(Usuario usuario) {
		
		System.out.println("email: "+usuario.getEmail());
		System.out.println("senha: "+usuario.getSenha());
		
		TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class);
		
		q.setParameter("email", usuario.getEmail());
		q.setParameter("senha", usuario.getSenha());
		
		List<Usuario> list = q.getResultList();
		
		if(list.isEmpty())
			return false;
		
		return true;
					
	}

	public Usuario find(Usuario usuario){

		return em.find(Usuario.class, usuario.getId());

	}

	public List<Usuario> findAll(){

		return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();

	}

}
