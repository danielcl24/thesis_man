package pt.ul.fc.css.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ul.fc.css.example.demo.handlers.RecolhaEstatisticas_Handler;
import pt.ul.fc.css.example.demo.services.DocenteService;

@Controller
public class StatsController {

  @Autowired private DocenteService docenteService;

  @Autowired private RecolhaEstatisticas_Handler recolhaEstatisticasHandler;

  public StatsController() {}

  @GetMapping("/stats")
  public String stats(
      @CookieValue(value = "utilizador", required = false) String id, final Model model) {
    if (id == null) {
      return "redirect:/login";
    }
    if (docenteService.getDocente(Long.parseLong(id)) == null) {
      return "redirect:/themes";
    }
    Double[] stats = recolhaEstatisticasHandler.recolhaEstatisticas();

    model.addAttribute("sucessRate", stats[0]);
    model.addAttribute("averageGrade", stats[1]);
    return "stats";
  }
}
