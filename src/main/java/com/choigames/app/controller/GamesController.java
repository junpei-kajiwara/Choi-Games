package com.choigames.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.choigames.app.form.UserForm;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/games")
public class GamesController {

  @GetMapping("/flipCards")
  public ModelAndView doflipCards(HttpSession session) {
    ModelAndView mv = new ModelAndView();

    // テスト用 TODO
    UserForm userForm = (UserForm) session.getAttribute("userData");
    System.out.println(userForm.getUserName());
    mv.setViewName("FlipCards");
    return mv;
  }

  @GetMapping("/swapPuzzle")
  public ModelAndView doSwapPuzzle() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("SwapPuzzle");
    return mv;
  }

  @GetMapping("/carryIt")
  public ModelAndView doCarryIt() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("CarryIt");
    return mv;
  }
}
