package br.desafio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.desafio.ejb.TelefoneEJB;
import br.desafio.ejb.UsuarioEJB;
import br.desafio.model.Telefone;
import br.desafio.model.Usuario;


@Named
@RequestScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioEJB usuarioEJB;

	@EJB
	private TelefoneEJB telefoneEJB;

	private Usuario usuario;
	private Usuario usuarioEdicao;

	private String idBusca;
	private String idBuscaTelefone;

	private List<Usuario> usuarios;

	private List<Telefone> telefones;
	private List<Telefone> telefonesEdicao;

	private Telefone telefone;
	private Telefone telefoneEdicao;

	@PostConstruct
	public void init() {

		usuarios = usuarioEJB.findAll();
		usuarioEdicao = new Usuario();

		telefones = new ArrayList<Telefone>();
		telefonesEdicao = new ArrayList<Telefone>();		
		telefoneEdicao = new Telefone();

		idBuscaTelefone = "";
		idBusca = "";	

	}
	/*
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            System.out.println("alteração");
            System.out.println("antigo: "+oldValue);
            System.out.println("novo: "+newValue);
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }*/

	public void atualizaListaUsuarios() {
		usuarios = new ArrayList<Usuario>();
		usuarios = usuarioEJB.findAll();
	}
	
	public void removeUsuarioEdicao() {

		if(Integer.parseInt(idBusca) > 0) {

			Usuario usua = new Usuario();
			usua.setId(Integer.parseInt(idBusca));

			List<Telefone> list = buscaTelefonesUsuario(usua);

			for(Telefone tel : list)
				removeTelefone(tel);

			remover(usua);
			idBusca = "";
			usuarioEdicao = new Usuario();
			usuarios.remove(usua);
			//atualizaListaUsuarios();
		}

	}

	public void removeTelefoneEdicao() {

		if(!idBuscaTelefone.equalsIgnoreCase("")) {
			
			Telefone tel = new Telefone();
			//tel.setId(Integer.parseInt(idBuscaTelefone));
			
			tel = buscaTelefoneId(Integer.parseInt(idBuscaTelefone));
			
			//System.out.println("idBuscaTelefone removeTelefoneEdicao: "+idBuscaTelefone);
			
			telefoneEJB.delete(tel);
			
			idBuscaTelefone = "";
					
			telefoneEdicao = new Telefone();
		}
		
	}
	
	
	public void atualizaUsuarioEdicao() {

		if(Integer.parseInt(idBusca) > 0) {

			usuarioEdicao.setId(Integer.parseInt(idBusca));			

			atualizar(usuarioEdicao);
		}
	}

	

	public void insereAletaraTelefone() {		

		if(!idBusca.equalsIgnoreCase("")) {
			
			Usuario usu = new Usuario();
			usu.setId(Integer.parseInt(idBusca));
			telefoneEdicao.setUsuario(usu);
			
			if(idBuscaTelefone.equalsIgnoreCase("")) {
				/*
				System.out.println("Id idBusca insereAletaraTelefone "+idBusca);

				System.out.println("Id usuario insereAletaraTelefone "+usuarioEdicao.getId());
				System.out.println("numero: "+telefoneEdicao.getNumero());
				System.out.println("DDD: "+telefoneEdicao.getDdd());
				System.out.println("Tipo: "+telefoneEdicao.getTipo());
				System.out.println("######################");			
				*/

				telefoneEJB.insert(telefoneEdicao);
				

			}
			else {
				/*
				System.out.println("Atualiza telefoneEdicao: ");			
				System.out.println("Id idBusca insereAletaraTelefone "+idBusca);			
				System.out.println("Id usuario insereAletaraTelefone "+idBuscaTelefone);
				*/
				telefoneEdicao.setId(Integer.parseInt(idBuscaTelefone));

				telefoneEJB.update(telefoneEdicao);
			}
			
			idBuscaTelefone = "";
			telefoneEdicao = new Telefone();
			
		}

	}

	public void carregaEdicao() {
		
		//System.out.println("Email foi: "+usuario.getEmail());
		
		idBuscaTelefone = "";
		telefoneEdicao = new Telefone();
		
		usuarioEdicao = buscaUsuarioId(Integer.parseInt(idBusca));
		telefonesEdicao = telefoneEJB.findAllPhoneByUser(usuarioEdicao);	

		//	
	}

	public void carregaTelefoneEdicao() {

		if(Integer.parseInt(idBuscaTelefone) > 0) {

			telefoneEdicao = buscaTelefoneId(Integer.parseInt(idBuscaTelefone));
		}


	}

	public Usuario buscaUsuarioId(int id) {

		return usuarioEJB.load(id);

	}

	public Telefone buscaTelefoneId(int id) {

		return telefoneEJB.load(id);


	}

	public void removeTelefone(Telefone tel) {

		telefoneEJB.delete(tel);
	}

	public void povoar() {

		Usuario usua = new Usuario();
		Usuario usua1 = new Usuario();

		usua.setEmail("joao@uol.com");
		usua.setNome("João");
		usua.setSenha("123");

		usua1.setEmail("maria@uol.com");
		usua1.setNome("Maria");
		usua1.setSenha("456");


		Telefone tel = new Telefone();
		Telefone tel1 = new Telefone();
		Telefone tel2 = new Telefone();
		Telefone tel3 = new Telefone();

		List<Telefone> list = new ArrayList<Telefone>();
		List<Telefone> list1 = new ArrayList<Telefone>();			


		tel.setDdd(82);
		tel.setNumero("9955-8774");
		tel.setTipo("Celular");
		tel.setUsuario(usua);

		tel1.setDdd(82);
		tel1.setNumero("3320-8855");
		tel1.setTipo("Fixo");
		tel1.setUsuario(usua);

		tel2.setDdd(82);
		tel2.setNumero("99914-0202");
		tel2.setTipo("Celular");
		tel2.setUsuario(usua1);

		tel3.setDdd(82);
		tel3.setNumero("3121-7456");
		tel3.setTipo("Fixo");
		tel3.setUsuario(usua1);

		list.add(tel);
		list.add(tel1);

		list1.add(tel2);
		list1.add(tel3);

		usua.setTelefones(list);
		usua1.setTelefones(list1);

		//System.out.println("Qtd telefones lista usuario: "+usua.getTelefones().size());

		usuarioEJB.insert(usua);
		usuarioEJB.insert(usua1);

		telefoneEJB.insert(tel);
		telefoneEJB.insert(tel1);
		telefoneEJB.insert(tel2);
		telefoneEJB.insert(tel3);

		/*
		//usua.setId(1);



		//telefoneEJB.insert(tel);
		//telefoneEJB.insert(tel2);

		list1 = telefoneEJB.findAllPhoneByUser(usua);

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		for(Telefone tele : list1) {
			System.out.println("numero: "+tele.getNumero());
			System.out.println("DDD: "+tele.getDdd());
			System.out.println("Tipo: "+tele.getTipo());
			System.out.println("######################");
		}
		*/


	}

	public void inserir() {

		usuario.setTelefones(telefones);
		//System.out.println("qtd telefone: "+usuario.getTelefones().size());

		usuarioEJB.insert(usuario);

		//System.out.println("6");
		usuarios.add(usuario);



		novo();

	}

	public void insereTelefone(Telefone tel) {

		/*
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("numero: "+telefone.getNumero());
		System.out.println("ddd: "+telefone.getDdd());
		System.out.println("tipo: "+telefone.getTipo());
		System.out.println("usuarioId: "+telefone.getUsuario().getId());
		 */
		telefoneEJB.insert(tel);


	}
	public List<Telefone> buscaTelefoneUsuarioIdEdicao(){

		//System.out.println("idUsuario: "+idBusca);

		Usuario usu = new Usuario();
		usu.setId(Integer.parseInt(idBusca));

		telefones = telefoneEJB.findAllPhoneByUser(usu);

		return telefones;

	}

	public List<Telefone> buscaTelefoneUsuarioId(){

		//System.out.println("idUsuario: "+usuario.getId());

		telefones = telefoneEJB.findAllPhoneByUser(usuario);

		return telefones;

	}

	public List<Telefone> buscaTelefoneUsuario(Usuario usuario){

		//System.out.println("idUsuario buscaTelefoneUsuario: "+usuario.getId());

		telefones = telefoneEJB.findAllPhoneByUser(usuario);

		return telefones;

	}

	public List<Telefone> buscaTelefonesUsuario(Usuario usuario){

		List<Telefone> list = new ArrayList<Telefone>();

		//System.out.println("idUsuario: "+usuario.getId());

		list = telefoneEJB.findAllPhoneByUser(usuario);

		return list;

	}

	public void buscar(Usuario usuario) {

		usuario = usuarioEJB.find(usuario);

	}

	public void atualizar(Usuario usu) {

		usuarioEJB.update(usu);

	}

	public void remover(Usuario usu) {

		usuarioEJB.delete(usu);

	}

	public void novo() {

		usuario = new Usuario();

	}

	public void novoTelefone() {

		telefone = new Telefone();

	}

	public String efetuaLogin() {

		//System.out.println("entrou login");

		if(usuarioEJB.login(usuario)) {

			return "edicao";

		}


		else {
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));
			return "index";
		}

	}

	public List<Usuario> listaTodosUsuarios(){

		return usuarioEJB.findAll();

	}


	public Usuario getUsuario() {

		if(usuario == null)
			usuario = new Usuario();

		return usuario;

	}

	public Telefone getTelefone() {

		if(telefone == null) 
			telefone = new Telefone();

		return telefone;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Telefone> getTelefones() {		

		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getIdBusca() {
		return idBusca;
	}

	public void setIdBusca(String idBusca) {
		this.idBusca = idBusca;
	}

	public List<Telefone> getTelefonesEdicao() {
		return telefonesEdicao;
	}

	public void setTelefonesEdicao(List<Telefone> telefonesEdicao) {
		this.telefonesEdicao = telefonesEdicao;
	}

	public Telefone getTelefoneEdicao() {
		return telefoneEdicao;
	}

	public void setTelefoneEdicao(Telefone telefoneEdicao) {
		this.telefoneEdicao = telefoneEdicao;
	}

	public Usuario getUsuarioEdicao() {
		return usuarioEdicao;
	}

	public void setUsuarioEdicao(Usuario usuarioEdicao) {
		this.usuarioEdicao = usuarioEdicao;
	}

	public String getIdBuscaTelefone() {
		return idBuscaTelefone;
	}

	public void setIdBuscaTelefone(String idBuscaTelefone) {
		this.idBuscaTelefone = idBuscaTelefone;
	}


}
