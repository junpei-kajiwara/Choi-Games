package com.example.choigames.app.games;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GamesController {
	
	@GetMapping
	public ModelAndView openGameMenu() {
		ModelAndView mv = new ModelAndView();
		System.out.println("test");
		mv.setViewName("Menu");
		return mv;
	}
	
	@GetMapping("/flipCards")
	public ModelAndView doflipCards() {
		ModelAndView mv = new ModelAndView();
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
