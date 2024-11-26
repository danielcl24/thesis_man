package pt.ul.fc.css.example.demo.entities;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidatura {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;
	
	@NonNull
	private String estado;
	
	@ManyToOne
	@JoinColumn(name="candidato_id", nullable = false)
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="tema_id", nullable = false)
	private Tema tema;

	public Candidatura() {
		super();
	}

	public Candidatura(@NonNull String estado, @NonNull Aluno candidato, @NonNull Tema tema) {
		super();
		this.estado = estado;
		this.aluno = candidato;
		this.tema = tema;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Aluno getCandidato() {
		return aluno;
	}

	public void setCandidato(Aluno candidato) {
		this.aluno = candidato;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
}
