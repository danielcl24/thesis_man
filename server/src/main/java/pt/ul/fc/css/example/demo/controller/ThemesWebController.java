package pt.ul.fc.css.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.handlers.SubmissaoTema_Doc_Handler;
import pt.ul.fc.css.example.demo.handlers.SubmissaoTema_UE_Handler;
import pt.ul.fc.css.example.demo.services.DocenteService;
import pt.ul.fc.css.example.demo.services.TemaService;

@Controller
public class ThemesWebController {

  @Autowired private TemaService temaService;

  @Autowired private DocenteService docenteService;

  @Autowired private SubmissaoTema_Doc_Handler submissaoTemaDocHandler;

  @Autowired private SubmissaoTema_UE_Handler submissaoTemaUEHandler;

  public ThemesWebController() {}

  @GetMapping("/themes")
  public String themes(
      @CookieValue(value = "utilizador", required = false) String id, final Model model) {

    if (id == null) {
      return "redirect:/login";
    }
    List<Tema> temas = temaService.getTemasByProponenteId(Long.parseLong(id));
    model.addAttribute("isAdmin", docenteService.isAdmin(Long.parseLong(id)));
    model.addAttribute("temas", temas);
    return "temas";
  }

  @PostMapping("/themes/new")
  public String add_theme(
      @CookieValue(value = "utilizador", required = false) String id,
      final Model model,
      @RequestParam("titulo") String titulo,
      @RequestParam("descricao") String descricao,
      @RequestParam("remuneracao") Double remuneracao,
      @RequestParam("mestradosCompativeis") String mestradosCompativeis) {
    if (id == null) {
      return "redirect:/login";
    }
    List<String> mestrados = Arrays.asList(mestradosCompativeis.split(","));
    boolean result =
        submissaoTemaDocHandler.submeterTema(
            titulo, descricao, remuneracao, mestrados, Long.parseLong(id));
    if (!result) {
      result =
          submissaoTemaUEHandler.submeterTema(
              titulo, descricao, remuneracao, mestrados, Long.parseLong(id));
      if (!result) {
        model.addAttribute("isAdmin", docenteService.isAdmin(Long.parseLong(id)));
        model.addAttribute("temas", temaService.getTemasByProponenteId(Long.parseLong(id)));
        return "redirect:/themes";
      }
    }
    List<Tema> temas = temaService.getTemasByProponenteId(Long.parseLong(id));
    model.addAttribute("isAdmin", docenteService.isAdmin(Long.parseLong(id)));
    model.addAttribute("temas", temas);
    return "redirect:/themes";
  }
}
