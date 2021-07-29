package org.generation.Imigrapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name="tb_tema")
public class Tema
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min=5, max= 100)
	private String nome_tema;
	
	@NotNull
	@Size(min=10, max=200)
	
	
	private String descricao_tema;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getNome_tema()
	{
		return nome_tema;
	}

	public void setNome_tema(String nome_tema)
	{
		this.nome_tema = nome_tema;
	}

	public String getDescricao_tema()
	{
		return descricao_tema;
	}

	public void setDescricao_tema(String descricao_tema)
	{
		this.descricao_tema = descricao_tema;
	}
	
	
}
