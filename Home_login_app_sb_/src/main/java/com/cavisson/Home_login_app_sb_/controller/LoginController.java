package com.cavisson.Home_login_app_sb_.controller;

import com.cavisson.Home_login_app_sb_.Entity.User;
import com.cavisson.Home_login_app_sb_.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  @PostMapping("/login")
  public String processLogin(@RequestParam String email,
                             @RequestParam String password,
                             Model model) {
    Optional<User> user = userRepository.findByEmailAndPassword(email, password);

    if (user.isPresent()) {
      return "redirect:/Notes"; // ✅ success
    } else {
      model.addAttribute("error", "Invalid email or password");
      return "login"; // ❌ stay on login
    }
  }

  @GetMapping("/Notes")
  public String showNotes() {
    return "Notes";
  }
}


