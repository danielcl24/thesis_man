package thesisman.client.models;

import java.io.File;
import java.util.List;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thesisman.client.views.ViewFactory;

public class Model {

  private final ViewFactory viewFactory;
  private final RestDriver restDriver;
  private static Model model;

  // data
  private Aluno aluno;
  private Candidatura currentCandidatura;
  private Tema currentTema;
  private boolean loginSuccessFlag;
  private Tese tese;
  private final ObservableList<Tema> temas =
      FXCollections.observableArrayList(
          tema ->
              new Observable[] {
                tema.tituloProperty(),
                tema.remuneracaoProperty(),
                tema.getMestrados(),
                tema.descricaoProperty()
              });

  private final ObservableList<Candidatura> candidaturas =
      FXCollections.observableArrayList(
          candidatura ->
              new Observable[] {
                candidatura.tituloProperty(),
                candidatura.remuneracaoProperty(),
                candidatura.estadoProperty()
              });

  private Model() {
    this.restDriver = new RestDriver();
    this.viewFactory = new ViewFactory();

    this.loginSuccessFlag = false;
    this.aluno = new Aluno(0, "", "", "", 0, "");
  }

  // Singleton pois o viewFactory tem de ser sempre o mesmo para mudar as stages e as scenes
  public static synchronized Model getInstance() {
    if (model == null) {
      model = new Model();
    }
    return model;
  }

  public ViewFactory getViewFactory() {
    return viewFactory;
  }

  public RestDriver getRestDriver() {
    return restDriver;
  }

  public boolean isLoginSuccessFlag() {
    return loginSuccessFlag;
  }

  public void setLoginSuccessFlag(boolean flag) {
    this.loginSuccessFlag = flag;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public ObservableList<Tema> getTemas() {
    return temas;
  }

  public ObservableList<Candidatura> getCandidaturas() {
    return candidaturas;
  }

  public Tema getCurrentTema() {
    return currentTema;
  }

  public void setCurrentTema(Tema tema) {
    this.currentTema = tema;
  }

  public Candidatura getCurrentCandidatura() {
    return currentCandidatura;
  }

  public void setCurrentCandidatura(Candidatura candidatura) {
    this.currentCandidatura = candidatura;
  }

  public Tese getTese() {
    return this.tese;
  }

  public void setTese(Tese t) {
    this.tese = t;
  }

  public void fetchCandidaturas() {
    List<Candidatura> candidaturas = restDriver.getCandidaturasData(this.aluno.getId());
    this.candidaturas.setAll(candidaturas);
  }

  public void fetchTemas() {
    List<Tema> temas = restDriver.getTemasData();
    this.temas.setAll(temas);
  }

  public void evaluateCreds(String email, String password) {
    Aluno aluno = restDriver.getAlunoData(email);
    if (aluno != null && !password.isEmpty()) {
      this.aluno = aluno;
      setLoginSuccessFlag(true);
    }
  }

  public void logout() {
    this.loginSuccessFlag = false;
    this.aluno = new Aluno(0, "", "", "", 0, "");
  }

  public boolean candidata() {
    return restDriver.candidata(this.aluno.getId(), this.currentTema.getId());
  }

  public boolean cancela() {
    return restDriver.cancela(this.aluno.getId(), this.currentCandidatura.getId());
  }

  public void initTese() {
    setTese(restDriver.getTese(this.aluno.getId()));
  }

  public boolean submeteProposta(File f) {
    return restDriver.submeteProposta(this.aluno.getId(), f);
  }

  public boolean submeteFinal(File f) {
    return restDriver.submeteFinal(this.aluno.getId(), f);
  }
}
