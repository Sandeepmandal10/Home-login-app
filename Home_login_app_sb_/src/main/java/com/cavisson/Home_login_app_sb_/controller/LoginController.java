package com.cavisson.Home_login_app_sb_.controller;

import com.cavisson.Home_login_app_sb_.Entity.User;
import com.cavisson.Home_login_app_sb_.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@Controller
public class LoginController {

  @Autowired
  private UserRepository userRepo;

  // Show login page
  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  // Generate OTP when email field is filled
  @GetMapping("/generate-otp")
  @ResponseBody
  public String generateOtp(@RequestParam String email, HttpSession session) {
    // Generate and store OTP in session
    int otp = new Random().nextInt(900000) + 100000;
    session.setAttribute("otp", otp);
    session.setAttribute("email", email); // Save email for reference
    return String.valueOf(otp); // For demo only – don't show OTP in production
  }

  // Process email, password, and OTP together
  @PostMapping("/login")
  public String processLogin(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam int otpInput,
                             HttpSession session,
                             Model model) {

    // Retrieve generated OTP and session email
    Integer generatedOtp = (Integer) session.getAttribute("otp");
    String sessionEmail = (String) session.getAttribute("email");

    // Check DB for valid user
    Optional<User> userOpt = userRepo.findByEmailAndPassword(email, password);

    // Validate all fields
    if (userOpt.isPresent() &&
      generatedOtp != null &&
      generatedOtp.equals(otpInput) &&
      email.equals(sessionEmail)) {
      return "redirect:/Notes";
    } else {
      model.addAttribute("error", "Invalid email, password or OTP");
      return "login";
    }
  }

  // Notes page
  @GetMapping("/Notes")
  public String notesPage() {
    return "Notes";
  }
}
