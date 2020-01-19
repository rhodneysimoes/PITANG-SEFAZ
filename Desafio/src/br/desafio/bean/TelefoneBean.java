package br.desafio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.desafio.model.Telefone;

@Named
@RequestScoped
public class TelefoneBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Telefone telefone;
	private List<Telefone> telefones = new ArrayList<Telefone>();
	/*
	public void inserir() {
		
		telefones.add(telefone);
				
		novo();
		
	}
	*/
	public void novo() {
		
		telefone = new Telefone();
		
	}

	public Telefone getTelefone() {
		
		if(telefone == null)
			telefone = new Telefone();
		
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	

}
