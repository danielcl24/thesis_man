package pt.ul.fc.css.example.demo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.example.demo.handlers.Registar_UE_Handler;

@Controller
public class RegisterWebController {

  @Autowired Registar_UE_Handler registar_UE_Handler;

  public RegisterWebController() {}

  @GetMapping("/register")
  public String register() {
    return "register_page";
  }

  @PostMapping("/register/new")
  public String register(
      @RequestParam("name") String name,
      @RequestParam("surname") String surname,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      final Model model,
      HttpServletResponse response) {
    UtilizadorEmpresarial ue = registar_UE_Handler.registarUE(email, name, surname);
    if (ue == null) {
      model.addAttribute("errorMessage", "Registration failed, that email is already in use.");
      return "register_page";
    }
    Cookie cookie = new Cookie("utilizador", ue.getId().toString());
    cookie.setPath("/");
    response.addCookie(cookie);
    return "redirect:/themes";
  }
}
