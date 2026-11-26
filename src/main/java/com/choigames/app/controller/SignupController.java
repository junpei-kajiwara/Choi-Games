package com.choigames.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

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
  public String doSignup() {
    // TODO サインアップ処理
    return "redirect:/login?signup";
  }

}
