package com.rebekahperkins.website.web.controller;

import com.rebekahperkins.website.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public String getLoginPage(Model model) {
    model.addAttribute("user", new User());
    return "login";
  }

  @RequestMapping("/access_denied")
  public String accessDenied() {
    return "access_denied";
  }
}
