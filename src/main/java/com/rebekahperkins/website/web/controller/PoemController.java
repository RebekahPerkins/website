package com.rebekahperkins.website.web.controller;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.User;
import com.rebekahperkins.website.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PoemController {

  @Autowired
  private PoemService poemService;

  @RequestMapping("/cats")
  public String getLangingPage(Model model) {
    return "cats";
  }

  @RequestMapping(path = "/vote", method = RequestMethod.POST)
  public String toggleVote(Model model) {
    return "redirect:/cats";
  }

  @RequestMapping("/my_cats")
  public String getMyCats(Model model) {
    return "my_cats";
  }

  @RequestMapping("cats/{id}")
  public String detail(@PathVariable Long id, Model model) {
    Poem poem = poemService.get(id);
    model.addAttribute("poem", poem);
    return "detail";
  }

  @RequestMapping("/search")
  public String search(@RequestParam String q) {
    return String.format("redirect:/search/%s", q);
  }

  @RequestMapping("/search/{q}")
  public String getForSearchTerm(@PathVariable String q, Model model) {
    return "cats";
  }

  @RequestMapping(path = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    model.addAttribute("poem", new Poem());
    return "edit";
  }

  @RequestMapping(path = "/cats/{id}/edit", method = RequestMethod.GET)
  public String edit(@PathVariable Long id, Model model) {
    Poem poem = poemService.get(id);
    model.addAttribute("poem", poem);
    return "edit";
  }

  @RequestMapping(path = "/addoredit", method = RequestMethod.POST)
  public String edit (Poem poem, Model model, Principal principal) {
    User loggedInUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    poem.setSubmittedBy(loggedInUser);
    //creates a new record for edit
    if (null != poem.getId()){
      poem = poemService.get(poem.getId());
    }
    poem = poemService.addOrUpdate(poem);
    return "redirect:/cats/" + poem.getId();
  }
}