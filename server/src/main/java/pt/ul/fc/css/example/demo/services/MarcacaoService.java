package pt.ul.fc.css.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.example.demo.entities.Marcacao;
import pt.ul.fc.css.example.demo.handlers.Listar_Marcacoes_Handler;

@Service
public class MarcacaoService {

  @Autowired private Listar_Marcacoes_Handler listar_Marcacoes_Handler;

  public MarcacaoService() {}

  public List<Marcacao> listar_Marcacoes() {
    return listar_Marcacoes_Handler.listar_Marcacoes();
  }
}
