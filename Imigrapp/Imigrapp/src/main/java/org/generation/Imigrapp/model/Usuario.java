package org.generation.Imigrapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_usuario;

	@Size(min = 5, max = 50)
	@NotBlank
	private String nomeUsuario;

	@Email
	@NotBlank
	private String email;

	@Size(min = 5, max = 100)
	@NotBlank
	private String senha;

	@NotBlank
	private String tipoUsuario;

	@Size(max = 20)
	@NotBlank
	private String idioma;

	@NotBlank
	private String paisOrigem;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	public long getId_usuario()
	{
		return id_usuario;
	}

	public void setId_usuario(long id_usuario)
	{
		this.id_usuario = id_usuario;
	}

	public String getNomeUsuario()
	{
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario)
	{
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public String getTipoUsuario()
	{
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}

	public String getIdioma()
	{
		return idioma;
	}

	public void setIdioma(String idioma)
	{
		this.idioma = idioma;
	}

	public String getPaisOrigem()
	{
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem)
	{
		this.paisOrigem = paisOrigem;
	}

}
