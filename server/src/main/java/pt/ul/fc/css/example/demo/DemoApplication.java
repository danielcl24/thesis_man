package pt.ul.fc.css.example.demo;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Author;
import pt.ul.fc.css.example.demo.entities.Candidatura;
import pt.ul.fc.css.example.demo.entities.Dissertacao;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PTDissertacao;
import pt.ul.fc.css.example.demo.entities.PTProjeto;
import pt.ul.fc.css.example.demo.entities.Projeto;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.example.demo.handlers.AtribuicaoTema_Admin_Handler;
import pt.ul.fc.css.example.demo.handlers.CancelarCandidatura_Handler;
import pt.ul.fc.css.example.demo.handlers.CandidatarTema_Aluno_Handler;
import pt.ul.fc.css.example.demo.handlers.ListarTemas_Handler;
import pt.ul.fc.css.example.demo.handlers.Login_Handler;
import pt.ul.fc.css.example.demo.handlers.MarcaDefesaProposta_Orientador_Handler;
import pt.ul.fc.css.example.demo.handlers.MarcaDefesa_Orientador_Handler;
import pt.ul.fc.css.example.demo.handlers.RecolhaEstatisticas_Handler;
import pt.ul.fc.css.example.demo.handlers.RegistaNotaDefTese_PresiJuri_Handler;
import pt.ul.fc.css.example.demo.handlers.Registar_UE_Handler;
import pt.ul.fc.css.example.demo.handlers.RegistoNota_Orientador_Handler;
import pt.ul.fc.css.example.demo.handlers.SubmissaoDocFinal_Aluno_Handler;
import pt.ul.fc.css.example.demo.handlers.SubmissaoProposta_Aluno_Handler;
import pt.ul.fc.css.example.demo.handlers.SubmissaoTema_Doc_Handler;
import pt.ul.fc.css.example.demo.handlers.SubmissaoTema_UE_Handler;
import pt.ul.fc.css.example.demo.repositories.AlunoRepository;
import pt.ul.fc.css.example.demo.repositories.AuthorRepository;
import pt.ul.fc.css.example.demo.repositories.CandidaturaRepository;
import pt.ul.fc.css.example.demo.repositories.DefesaFinalRepository;
import pt.ul.fc.css.example.demo.repositories.DefesaPropostaRepository;
import pt.ul.fc.css.example.demo.repositories.DissertacaoRepository;
import pt.ul.fc.css.example.demo.repositories.DocenteRepository;
import pt.ul.fc.css.example.demo.repositories.JuriFinalRepository;
import pt.ul.fc.css.example.demo.repositories.JuriRepository;
import pt.ul.fc.css.example.demo.repositories.MarcacaoRepository;
import pt.ul.fc.css.example.demo.repositories.PTDissertacaoRepository;
import pt.ul.fc.css.example.demo.repositories.PTProjetoRepository;
import pt.ul.fc.css.example.demo.repositories.ProjetoRepository;
import pt.ul.fc.css.example.demo.repositories.TemaRepository;
import pt.ul.fc.css.example.demo.repositories.UtilizadorEmpresarialRepository;

@SpringBootApplication
public class DemoApplication {

  @Autowired private Registar_UE_Handler registarUEhandler = new Registar_UE_Handler();

  @Autowired private Login_Handler loginUEhandler = new Login_Handler();

  @Autowired
  private SubmissaoTema_Doc_Handler submissaoTemaDocHandler = new SubmissaoTema_Doc_Handler();

  @Autowired
  private SubmissaoTema_UE_Handler submissaoTemaUEHandler = new SubmissaoTema_UE_Handler();

  @Autowired private ListarTemas_Handler listarTemasHandler = new ListarTemas_Handler();

  @Autowired
  private CandidatarTema_Aluno_Handler candidatarTemaAlunoHandler =
      new CandidatarTema_Aluno_Handler();

  @Autowired
  private CancelarCandidatura_Handler cancelarCandidaturaHandler =
      new CancelarCandidatura_Handler();

  @Autowired
  private AtribuicaoTema_Admin_Handler atribuicaoTemasAdminHandler =
      new AtribuicaoTema_Admin_Handler();

  @Autowired
  private SubmissaoProposta_Aluno_Handler submissaoPropostaAlunoHandler =
      new SubmissaoProposta_Aluno_Handler();

  @Autowired
  private MarcaDefesaProposta_Orientador_Handler marcaDefesaPropostaOrientadorHandler =
      new MarcaDefesaProposta_Orientador_Handler();

  @Autowired
  private SubmissaoDocFinal_Aluno_Handler submissaoDocFinalAlunoHandler =
      new SubmissaoDocFinal_Aluno_Handler();

  @Autowired
  private RegistoNota_Orientador_Handler registoNotaOrientadorHandler =
      new RegistoNota_Orientador_Handler();

  @Autowired
  private MarcaDefesa_Orientador_Handler marcaDefesaOrientadorHandler =
      new MarcaDefesa_Orientador_Handler();

  @Autowired
  private RegistaNotaDefTese_PresiJuri_Handler registaNotaDefTesePresiJuriHandler =
      new RegistaNotaDefTese_PresiJuri_Handler();

  @Autowired
  private RecolhaEstatisticas_Handler recolhaEstatisticasHandler =
      new RecolhaEstatisticas_Handler();

  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(
      AuthorRepository repository,
      AlunoRepository alunorepo,
      DocenteRepository docerepo,
      UtilizadorEmpresarialRepository uerepo,
      TemaRepository temarepo,
      ProjetoRepository prepo,
      DissertacaoRepository drepo,
      PTDissertacaoRepository ptdrepo,
      PTProjetoRepository ptprepo,
      MarcacaoRepository marcarepo,
      JuriRepository jurirepo,
      JuriFinalRepository jufirepo,
      DefesaPropostaRepository dprepo,
      DefesaFinalRepository dfrepo,
      CandidaturaRepository candirepo) {
    return (args) -> {
      // save a few customers
      Aluno a1 = new Aluno("rafaelcorreia@gmail.com", "Rafael", "Correia", 12.3, "Informática");
      Aluno a2 = new Aluno("miguelcosta@gmail.com", "Miguel", "Costa", 15.2, "Biologia");
      Aluno a3 = new Aluno("goncalopeixe@gmail.com", "Gonçalo", "Peixe", 17.2, "Geologia");
      alunorepo.save(a1);
      alunorepo.save(a2);
      alunorepo.save(a3);

      Docente doc1 = new Docente("antonioramires@gmail.com", "Antonio", "Ramires", false);
      Docente doc2 = new Docente("alcidespereira@gmail.com", "Alcides", "Pereira", true);
      Docente doc3 = new Docente("filipeesteve@gmail.com", "Filipe", "Esteve", false);
      docerepo.save(doc1);
      docerepo.save(doc2);
      docerepo.save(doc3);

      UtilizadorEmpresarial ue1 =
          new UtilizadorEmpresarial("oscargustavo@gmail.com", "Oscar", "Gustavo");
      UtilizadorEmpresarial ue2 =
          new UtilizadorEmpresarial("afonsobaleixo@gmail.com", "Afonso", "Baleixo");
      uerepo.save(ue1);
      uerepo.save(ue2);
      List<String> mestrados = new ArrayList<String>();
      mestrados.add("Informatica");
      mestrados.add("Biologia");
      List<String> mestrados2 = new ArrayList<String>();
      mestrados2.add("Geologia");
      mestrados2.add("Biologia");
      Tema t1 = new Tema(doc1, "Bananas Saltitantes", "1,2,3", 233.1, mestrados);
      Tema t2 = new Tema(ue1, "Potatos", "blablabla", 444.1, mestrados2);
      temarepo.save(t1);
      temarepo.save(t2);

      Candidatura c1 = new Candidatura("Aceite", a3, t2);
      Candidatura c2 = new Candidatura("Rejeite", a2, t1);

      candirepo.save(c1);
      candirepo.save(c2);

      File file = new File("banana.txt");
      File file1 = new File("potato.txt");
      LocalDate date = LocalDate.of(1234, 2, 23);
      LocalTime horaAtual = LocalTime.now();
      PTProjeto ptp = new PTProjeto(file, a1, doc2, t2, ue1);
      PTDissertacao ptd = new PTDissertacao(file1, a3, doc1, t1);
      ptdrepo.save(ptd);
      ptprepo.save(ptp);

      LocalDate date1 = LocalDate.of(1424, 2, 23);
      LocalTime horaAtual1 = LocalTime.now();

      Projeto tp = new Projeto(file, a2, doc2, ue2, ptp, true);
      Dissertacao td = new Dissertacao(file1, a3, doc2, ptd, false);

      tp.setTema(t2);
      td.setTema(t1);
      prepo.save(tp);
      drepo.save(td);

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (Author author : repository.findAll()) {
        log.info(author.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      repository
          .findById(1L)
          .ifPresent(
              (Author author) -> {
                log.info("Customer found with findById(1L):");
                log.info("--------------------------------");
                log.info(author.toString());
                log.info("");
              });

      // fetch customers by last name
      log.info("Author found with findByName('Bauer'):");
      log.info("--------------------------------------------");
      repository
          .findByName("Bauer")
          .forEach(
              bauer -> {
                log.info(bauer.toString());
              });
      // for (Customer bauer : repository.findByLastName("Bauer")) {
      // log.info(bauer.toString());
      // }
      log.info("");
    };
  }
}
