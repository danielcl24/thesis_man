package pt.ul.fc.css.example.demo.entities;

import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Juri {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
    private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "orientador_id", referencedColumnName="id", nullable = false)
	private Docente orientador;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "arguente_id", referencedColumnName="id", nullable = false)
	private Docente arguente;
	
	protected Juri() {
		
	}
	
	public Juri(@NonNull Docente orientador, @NonNull Docente arguente) {
		super();
		this.orientador = orientador;
		this.arguente = arguente;
	}

	
	public Juri(@NonNull Docente orientador, @NonNull Docente arguente, @NonNull Defesa defesa) {
		super();
		this.orientador = orientador;
		this.arguente = arguente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Docente getOrientador() {
		return orientador;
	}

	public void setOrientador(Docente orientador) {
		this.orientador = orientador;
	}

	public Docente getArguente() {
		return arguente;
	}

	public void setArguente(Docente arguente) {
		this.arguente = arguente;
	}

}
