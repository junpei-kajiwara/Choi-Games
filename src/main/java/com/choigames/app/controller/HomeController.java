package com.choigames.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.choigames.app.security.LoginPlayerDetails;

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
  public ModelAndView openGameMenu(@AuthenticationPrincipal LoginPlayerDetails loginPlayerDetails) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("playerName", loginPlayerDetails.getPlayerName());
    mv.setViewName("Menu");
    return mv;
  }
}
