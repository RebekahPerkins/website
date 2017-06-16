package com.rebekahperkins.website.web.controller;

import com.rebekahperkins.website.domain.User;
import com.rebekahperkins.website.service.UserService;
import com.rebekahperkins.website.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @RequestMapping(path = "/register", method = RequestMethod.GET)
  public String getRegistration(Model model) {
    if (!model.containsAttribute("user")){
      model.addAttribute("user", new User());
    }
    return "register";
  }

  @RequestMapping(path = "/register", method = RequestMethod.POST)
  public String register(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
      redirectAttributes.addFlashAttribute("user", user);
      return "redirect:/register";
    }
    try {
      userService.register(user);
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("exception", e.getMessage());
      return "redirect:/register";
    }

    //TODO: check for username and password at login get
    redirectAttributes.addAttribute("user", user);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Successfully registered", FlashMessage.Status.SUCCESS));
    return "redirect:/login";
  }
}
