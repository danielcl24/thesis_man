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
public class Defesa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
    private Long id;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "marcacao_id", referencedColumnName="id", nullable = true)
	private Marcacao marcacao;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "juri_id", referencedColumnName="id", nullable = true)
	private Juri juri;
	
	private Double avaliacao;

	
	protected Defesa() {
		
	}
	
	public Defesa(@NonNull Marcacao marcacao,@NonNull Juri juri) {
		super();
		this.marcacao = marcacao;
		this.juri = juri;
		this.avaliacao = null;
	}

	public Defesa(@NonNull Marcacao marcacao,@NonNull JuriFinal juriFinal) {
		super();
		this.marcacao = marcacao;
		this.juri = juriFinal;
		this.avaliacao = null;
	}

	public Defesa(@NonNull Marcacao marcacao,@NonNull Juri juri, @NonNull Double avaliacao) {
		super();
		this.marcacao = marcacao;
		this.juri = juri;
		this.avaliacao = avaliacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Marcacao getMarcacao() {
		return marcacao;
	}

	public void setMarcacao(Marcacao marcacao) {
		this.marcacao = marcacao;
	}

	public Juri getJuri() {
		return juri;
	}

	public void setJuri(Juri juri) {
		this.juri = juri;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
	
	

}
