package pt.ul.fc.css.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.ul.fc.css.example.demo.entities.Juri;

@Repository
public interface JuriRepository extends JpaRepository<Juri, Long>{
	
	@Query("SELECT j FROM Juri j")
    List<Juri> findAll();
}
