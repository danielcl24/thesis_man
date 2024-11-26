package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

public interface UtilizadorEmpresarialRepository
    extends JpaRepository<UtilizadorEmpresarial, Long> {

  List<UtilizadorEmpresarial> findByEmail(String email);
}
