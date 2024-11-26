package pt.ul.fc.css.example.demo.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.handlers.MarcaDefesaProposta_Orientador_Handler;
import pt.ul.fc.css.example.demo.handlers.MarcaDefesa_Orientador_Handler;
import pt.ul.fc.css.example.demo.services.DocenteService;
import pt.ul.fc.css.example.demo.services.MarcacaoService;
import pt.ul.fc.css.example.demo.services.PropostasTeseService;
import pt.ul.fc.css.example.demo.services.TesesService;

@Controller
public class ThesisDefenseAptWebController {

  @Autowired private DocenteService docenteService;

  @Autowired private TesesService teseService;

  @Autowired private PropostasTeseService propostasTeseService;

  @Autowired private MarcacaoService marcacaoService;

  @Autowired private MarcaDefesa_Orientador_Handler marcaDefesaOrientadorHandler;

  @Autowired private MarcaDefesaProposta_Orientador_Handler marcaDefesaPropostaOrientadorHandler;

  public ThesisDefenseAptWebController() {}

  @GetMapping("/defenses/thesis")
  public String getThesisDefenses(
      @CookieValue(value = "utilizador", required = false) String id, final Model model) {
    if (id == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(id));
    if (docente == null) {
      return "redirect:/themes";
    }

    List<Docente> docentes = docenteService.listar_Docentes();
    docentes =
        docentes.stream().filter(d -> d.getId() != docente.getId()).collect(Collectors.toList());
    model.addAttribute("isFinalDefense", true);
    model.addAttribute("listaDocentes", docentes);
    model.addAttribute("listaMarcacoes", marcacaoService.listar_Marcacoes());
    model.addAttribute("listaProjs", teseService.listar_Projs_Docente(Long.parseLong(id)));
    model.addAttribute("listaDiss", teseService.listar_Dissertacoes_Docente(Long.parseLong(id)));

    return "thesisDefenseApt";
  }

  @PostMapping("/defenses/thesis")
  public String postThesisDefenses(
      @CookieValue(value = "utilizador", required = false) String id,
      @RequestParam String idTese,
      @RequestParam LocalDate data,
      @RequestParam LocalTime horaComeco,
      @RequestParam int duracao,
      @RequestParam String secondDefendantId,
      @RequestParam String presidentId,
      @RequestParam String sala,
      final Model model) {
    if (id == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(id));
    if (docente == null) {
      return "redirect:/login";
    }
    if (secondDefendantId.equals(presidentId)) {

      return "redirect:/defenses/thesis";
    }

    Duration duracao2 = Duration.ofMinutes(duracao);
    boolean result =
        marcaDefesaOrientadorHandler.marcaDefesa_Orientador(
            data, horaComeco, duracao2, sala, secondDefendantId, presidentId, idTese);
    if (result) {
      return "redirect:/defenses/thesis";
    } else {
      return "redirect:/themes";
    }
  }

  @GetMapping("/defenses/thesisProposal")
  public String getPropThesisDefenses(
      @CookieValue(value = "utilizador", required = false) String id, final Model model) {

    if (id == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(id));
    if (docente == null) {
      return "redirect:/themes";
    }

    List<Docente> docentes = docenteService.listar_Docentes();
    docentes =
        docentes.stream().filter(d -> d.getId() != docente.getId()).collect(Collectors.toList());
    model.addAttribute("isFinalDefense", false);
    model.addAttribute("listaDocentes", docentes);
    model.addAttribute("listaMarcacoes", marcacaoService.listar_Marcacoes());
    model.addAttribute(
        "listaProjs", propostasTeseService.listar_PTProjs_Docente(Long.parseLong(id)));
    model.addAttribute(
        "listaDiss", propostasTeseService.listar_PTDissertacoes_Docente(Long.parseLong(id)));

    return "thesisDefenseApt";
  }

  @PostMapping("/defenses/thesisProposal")
  public String postPropThesisDefenses(
      @CookieValue(value = "utilizador", required = false) String id,
      @RequestParam String idTese,
      @RequestParam LocalDate data,
      @RequestParam LocalTime horaComeco,
      @RequestParam int duracao,
      @RequestParam String secondDefendantId,
      @RequestParam String sala,
      final Model model) {
    if (id == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(id));
    if (docente == null) {
      return "redirect:/login";
    }

    Duration duracao2 = Duration.ofMinutes(duracao);
    boolean result =
        marcaDefesaPropostaOrientadorHandler.marcaDefesaProposta_Orientador(
            data, horaComeco, duracao2, sala, secondDefendantId, idTese);
    if (result) {
      return "redirect:/defenses/thesisProposal";
    } else {
      return "redirect:/themes";
    }
  }
}
