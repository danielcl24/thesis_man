package pt.ul.fc.css.example.demo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.example.demo.handlers.Login_Handler;
import pt.ul.fc.css.example.demo.services.TemaService;

@Controller
public class LoginWebController {

  @Autowired private Login_Handler loginHandler;

  @Autowired private TemaService temaService;

  public LoginWebController() {}

  @GetMapping({"/", "/login"})
  public String login(
      final Model model, @CookieValue(value = "utilizador", required = false) String id) {

    if (id != null) {
      return "redirect:/themes";
    }
    return "login_page";
  }

  @PostMapping("/login")
  public String login(
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      final Model model,
      HttpServletResponse response) {
    UtilizadorEmpresarial utilizador = loginHandler.login_web(email);
    if (utilizador == null) {
      Docente docente = loginHandler.login(email);
      if (docente == null) {
        model.addAttribute(
            "errorMessage", "Login failed, your credentials are incorrect, please try again.");
        return "login_page";
      } else {
        Cookie cookie = new Cookie("utilizador", docente.getId().toString());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/themes";
      }
    } else {
      Cookie cookie = new Cookie("utilizador", utilizador.getId().toString());
      cookie.setPath("/");
      response.addCookie(cookie);
      return "redirect:/themes";
    }
  }
}
