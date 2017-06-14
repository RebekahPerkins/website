package com.rebekahperkins.website.web.controller;

import com.rebekahperkins.website.domain.User;
import com.rebekahperkins.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @RequestMapping(path = "/register", method = RequestMethod.GET)
  public String getRegistration(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @RequestMapping(path = "/register", method = RequestMethod.POST)
  public String register(User user, Model model) {
    userService.register(user);
    //TODO: Send the users info with this request. Call the login get or post, either is okay
    return "redirect:/login";
  }
}
