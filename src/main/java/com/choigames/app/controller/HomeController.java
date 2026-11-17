package com.choigames.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.choigames.app.form.UserForm;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

  /**
   * ホーム（ゲームメニュー）画面への遷移
   * 
   * @param session
   * @return ModelAndView
   */
  @GetMapping("/init")
  public ModelAndView openGameMenu(HttpSession session) {
    ModelAndView mv = new ModelAndView();
    UserForm userForm = new UserForm();
    userForm.setUserName("太郎");

    // HTTPセッションは後ほどログイン処理に移植する
    session.setAttribute("userData", userForm);
    mv.setViewName("Menu");
    return mv;
  }
}
