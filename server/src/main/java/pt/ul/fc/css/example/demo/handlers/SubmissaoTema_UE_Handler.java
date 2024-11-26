package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.TemasCatalog;
import pt.ul.fc.css.example.demo.catalogs.UtilizadoresEmpresarialCatalog;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class SubmissaoTema_UE_Handler {

  @Autowired private TemasCatalog temasCatalog;

  @Autowired private UtilizadoresEmpresarialCatalog utilizadoresEmpresarialCatalog;

  public boolean submeterTema(
      String titulo,
      String descricao,
      Double remuneracao,
      List<String> mestradosCompativeis,
      Long ueId) {
    UtilizadorEmpresarial ue = utilizadoresEmpresarialCatalog.getUE(ueId);
    if (ue == null) {
      return false;
    }
    temasCatalog.addTema(ue, titulo, descricao, remuneracao, mestradosCompativeis);
    return true;
  }
}
