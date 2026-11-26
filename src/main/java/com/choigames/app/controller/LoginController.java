package com.choigames.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

  /**
   * ログイン画面 初期表示
   * 
   * @return ログイン画面遷移
   */
  @GetMapping
  public String login() {
    return "Login";
  }

}
