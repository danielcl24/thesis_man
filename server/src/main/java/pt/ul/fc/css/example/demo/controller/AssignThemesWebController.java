package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.handlers.AtribuicaoTema_Admin_Handler;
import pt.ul.fc.css.example.demo.services.AlunoService;
import pt.ul.fc.css.example.demo.services.DocenteService;
import pt.ul.fc.css.example.demo.services.TemaService;

@Controller
public class AssignThemesWebController {

  @Autowired private TemaService temaService;

  @Autowired private DocenteService docenteService;

  @Autowired private AlunoService alunoService;

  @Autowired private AtribuicaoTema_Admin_Handler atribuicaoTemaAdminHandler;

  public AssignThemesWebController() {}

  @GetMapping("/themes/assign")
  public String assignThemes(
      final Model model, @CookieValue(value = "utilizador", required = false) String id) {

    if (id == null) {
      return "redirect:/login";
    }
    if (!docenteService.isAdmin(Long.parseLong(id))) {
      return "redirect:/themes";
    }
    List<Tema> temas = temaService.getTemasSemAlunos2();
    List<Aluno> alunos = alunoService.getAlunosSemTema();
    model.addAttribute("temas", temas);
    model.addAttribute("alunos", alunos);
    return "assignThemes";
  }

  @PostMapping("/themes/assign")
  public String assignTheme(
      @RequestParam("aluno") String alunoId,
      @RequestParam("tema") String temaId,
      final Model model,
      @CookieValue(value = "utilizador", required = false) String id) {

    if (id == null) {
      return "redirect:/login";
    }
    if (!docenteService.isAdmin(Long.parseLong(id))) {
      return "redirect:/themes";
    }
    boolean result =
        atribuicaoTemaAdminHandler.atribuirTema(Long.parseLong(alunoId), Long.parseLong(temaId));
    if (!result) {
      return "redirect:/themes";
    }

    return "redirect:/themes/assign";
  }
}
