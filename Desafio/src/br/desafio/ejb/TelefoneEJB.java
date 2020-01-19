package br.desafio.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.desafio.model.Telefone;
import br.desafio.model.Usuario;

@Stateful
public class TelefoneEJB {
	
	@PersistenceContext
	EntityManager em;
	
	//Recupera usuario pelo id
		public List<Telefone> findAllPhoneByUser(Usuario usuario) {
			
			//return em.createQuery("").setParameter("id_usuario", usuario.getId()).getResultList();
			System.out.println("id_usuario: "+usuario.getId());
			
			//TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class);
			
			//TypedQuery<Telefone> q = em.createQuery("SELECT t FROM Telefone t, Usuario u WHERE t.usuario = "+usuario.getId()+"", Telefone.class);			
			TypedQuery<Telefone> q = em.createQuery("SELECT t FROM Telefone t, Usuario u WHERE t.usuario = u.id AND t.usuario = "+usuario.getId()+"", Telefone.class);
			
			//q.setParameter("idUsuario", usuario.getId());
			
			List<Telefone> list = q.getResultList();
			
			return list;
		
		}
		
		public List<Telefone> findAll(){

			return em.createQuery("SELECT u FROM Telefone u", Telefone.class).getResultList();

		}
		
		public void insert(Telefone telefone) {		

			em.persist(telefone);

		}
		//Altera o telefone
		public void update(Telefone telefone) {

			em.merge(telefone);

		}

		//Deleta o telefone
		public void delete(Telefone telefone) {

			//Recupera o id antes de deletar
			telefone = load(telefone.getId());
			em.remove(telefone);

		}

		//Recupera telefone pelo id
		public Telefone load(int id) {

			return em.find(Telefone.class, id);

		}
		
}
