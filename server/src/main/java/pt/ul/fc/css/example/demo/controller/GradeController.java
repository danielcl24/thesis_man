package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.Dissertacao;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PTDissertacao;
import pt.ul.fc.css.example.demo.entities.PTProjeto;
import pt.ul.fc.css.example.demo.entities.Projeto;
import pt.ul.fc.css.example.demo.handlers.RegistaNotaDefTese_PresiJuri_Handler;
import pt.ul.fc.css.example.demo.handlers.RegistoNota_Orientador_Handler;
import pt.ul.fc.css.example.demo.services.DocenteService;
import pt.ul.fc.css.example.demo.services.PropostasTeseService;
import pt.ul.fc.css.example.demo.services.TesesService;

@Controller
public class GradeController {

  @Autowired private DocenteService docenteService;

  @Autowired private TesesService teseService;

  @Autowired private RegistaNotaDefTese_PresiJuri_Handler registaNotaDefTesePresiJuriHandler;

  @Autowired private RegistoNota_Orientador_Handler registoNotaOrientadorHandler;

  @Autowired private PropostasTeseService propostasTeseService;

  public GradeController() {}

  @GetMapping("/grades/finalDefenses")
  public String getGrades(
      @CookieValue(value = "utilizador", required = false) String id, final Model model) {
    if (id == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(id));
    if (docente == null) {
      return "redirect:/themes";
    }

    List<Projeto> projs = teseService.listar_Projs_Docente_Presi(Long.parseLong(id));
    projs = projs.stream().filter(proj -> proj.getDefesa() != null).collect(Collectors.toList());
    List<Dissertacao> diss = teseService.listar_Dissertacoes_Docente_Presi(Long.parseLong(id));
    diss = diss.stream().filter(d -> d.getDefesa() != null).collect(Collectors.toList());

    model.addAttribute("isFinalDefense", true);
    model.addAttribute("projList", projs);
    model.addAttribute("dissList", diss);

    return "grades";
  }

  @PostMapping("/grades/finalDefenses")
  public String postGrades(
      @CookieValue(value = "utilizador", required = false) String idU,
      @RequestParam String avaliacao,
      @RequestParam String id,
      final Model model) {
    if (idU == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(idU));
    if (docente == null) {
      return "redirect:/themes";
    }

    registaNotaDefTesePresiJuriHandler.registoNota(
        Long.parseLong(id), Long.parseLong(idU), Double.parseDouble(avaliacao));

    return "redirect:/grades/finalDefenses";
  }

  @GetMapping("/grades/thesisProposals")
  public String getGradesP(
      @CookieValue(value = "utilizador", required = false) String id, final Model model) {
    if (id == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(id));
    if (docente == null) {
      return "redirect:/themes";
    }

    List<PTProjeto> projs = propostasTeseService.listar_PTProjs_Docente(Long.parseLong(id));
    projs = projs.stream().filter(proj -> proj.getDefesa() != null).collect(Collectors.toList());
    List<PTDissertacao> diss =
        propostasTeseService.listar_PTDissertacoes_Docente(Long.parseLong(id));
    diss = diss.stream().filter(d -> d.getDefesa() != null).collect(Collectors.toList());

    model.addAttribute("isFinalDefense", false);
    model.addAttribute("projList", projs);
    model.addAttribute("dissList", diss);

    return "grades";
  }

  @PostMapping("/grades/thesisProposals")
  public String postGradesP(
      @CookieValue(value = "utilizador", required = false) String idU,
      @RequestParam String avaliacao,
      @RequestParam String id,
      final Model model) {
    if (idU == null) {
      return "redirect:/login";
    }
    Docente docente = docenteService.getDocente(Long.parseLong(idU));
    if (docente == null) {
      return "redirect:/themes";
    }

    registoNotaOrientadorHandler.registoNota(
        Long.parseLong(id), Long.parseLong(idU), Double.parseDouble(avaliacao));

    return "redirect:/grades/thesisProposals";
  }
}
