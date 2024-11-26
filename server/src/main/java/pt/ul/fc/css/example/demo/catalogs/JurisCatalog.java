package pt.ul.fc.css.example.demo.catalogs;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.Defesa;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.Juri;
import pt.ul.fc.css.example.demo.entities.JuriFinal;
import pt.ul.fc.css.example.demo.repositories.JuriFinalRepository;
import pt.ul.fc.css.example.demo.repositories.JuriRepository;

@Component
public class JurisCatalog {
	
	@Autowired
	private JuriRepository juriRepository;

	@Autowired
	private JuriFinalRepository juriFinalRepository;
	
	public Juri addJuri(Docente orientador,  Docente arguente) {
		Juri juri = new Juri(orientador, arguente);
		juriRepository.save(juri);
		return juri;
	}

	public void save(Juri juri) {
		juriRepository.save(juri);
	}
	
	public JuriFinal addJuriFinal(Docente orientador,  Docente arguente,Docente presidente) {
		JuriFinal juriFinal = new JuriFinal(orientador, arguente, presidente);
		juriFinalRepository.save(juriFinal);
		return juriFinal;
	}
}
