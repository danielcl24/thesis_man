package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DefesasCatalog;
import pt.ul.fc.css.example.demo.entities.DefesaFinal;

@Component
public class RecolhaEstatisticas_Handler {

  @Autowired DefesasCatalog defesasCatalog;

  public Double[] recolhaEstatisticas() {
    List<Long> defesasFinaisIds = defesasCatalog.getDefesasFinal();
    int notasPositivas = 0;
    double somaNotas = 0;
    double length = defesasFinaisIds.size();
    for (Long id : defesasFinaisIds) {
      DefesaFinal defesa = defesasCatalog.getDefesaFinal(id);
      Double nota = defesa.getAvaliacao();
      if (nota < 9.5) {
        somaNotas += nota;
      } else {
        somaNotas += nota;
        notasPositivas++;
      }
    }
    double taxaSucesso = notasPositivas / length * 100;
    double mediaNotas = somaNotas / length;
    Double[] result = {taxaSucesso, mediaNotas};
    return result;
  }
}
