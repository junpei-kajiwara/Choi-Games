package com.choigames.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choigames.app.exception.DuplicateEmailException;
import com.choigames.app.form.SignupForm;
import com.choigames.app.service.SignupService;

@Controller
@RequestMapping("/signup")
public class SignupController {

  @Autowired
  SignupService service;

  /**
   * サインアップ画面 初期表示
   * 
   * @return サインアップ画面遷移
   */
  @GetMapping
  public String signup() {
    return "Signup";
  }

  /**
   * サインアップ処理
   * 
   * @return リダイレクトでログイン画面へ遷移
   */
  @PostMapping
  public String doSignup(SignupForm form, RedirectAttributes redirectAttributes) {
    try {
      service.registerAccount(form);
      return "redirect:/login?signup";
    } catch (DuplicateEmailException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
      return "redirect:/signup";
    }
  }

}
