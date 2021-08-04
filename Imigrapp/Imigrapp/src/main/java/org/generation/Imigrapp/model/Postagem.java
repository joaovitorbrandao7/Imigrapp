package org.generation.Imigrapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagem")
public class Postagem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_postagem;

	@NotBlank
	private String campoPostagem;

	private int compartilhamento;

	private String comentario;

	private int interacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public long getId_postagem()
	{
		return id_postagem;
	}

	public void setId_postagem(long id_postagem)
	{
		this.id_postagem = id_postagem;
	}

	public String getCampoPostagem()
	{
		return campoPostagem;
	}

	public void setCampoPostagem(String campoPostagem)
	{
		this.campoPostagem = campoPostagem;
	}

	public int getCompartilhamento()
	{
		return compartilhamento;
	}

	public void setCompartilhamento(int compartilhamento)
	{
		this.compartilhamento = compartilhamento;
	}

	public String getComentario()
	{
		return comentario;
	}

	public void setComentario(String comentario)
	{
		this.comentario = comentario;
	}

	public int getInteracao()
	{
		return interacao;
	}

	public void setInteracao(int interacao)
	{
		this.interacao = interacao;
	}

	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

}
