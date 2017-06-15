package com.rebekahperkins.website.web.controller;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.User;
import com.rebekahperkins.website.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
  public String getLangingPage(Model model, Pageable pageable) {
    Page<Poem> page = poemService.findAll(pageable);
    model.addAttribute("page", page);
    model.addAttribute("referrer", "/cats");
    return "cats";
  }

  @RequestMapping(path = "/cats/{id}/favorite", method = RequestMethod.POST)
  public String toggle(@PathVariable Long id, Model model, Principal principal) {
    User loggedInUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    poemService.toggleFavorite(id, loggedInUser);

    return "redirect:/cats/" + id;
  }

  @RequestMapping("/my_cats")
  public String getMyCats(Model model, Pageable pageable, Principal principal) {
    User loggedInUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    Page<Poem> page = poemService.findBySubmittedBy(loggedInUser, pageable);
    model.addAttribute("page", page);
    model.addAttribute("referrer", "/my_cats");
    return "my_cats";
  }

  @RequestMapping("cats/{id}")
  public String detail(@PathVariable Long id, Model model, Principal principal) {
    User loggedInUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    Poem poem = poemService.get(id);
    poem.setFavorite(poemService.isFavorite(id, loggedInUser.getId()));
    model.addAttribute("poem", poem);
    return "detail";
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
    poem = poemService.addOrUpdate(poem);
    return "redirect:/cats/" + poem.getId();
  }

  @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
  public String delete (Poem poem, Model model) {
    poemService.delete(poem);
    return "redirect:/cats";
  }
}