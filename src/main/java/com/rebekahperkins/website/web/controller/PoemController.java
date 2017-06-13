package com.rebekahperkins.website.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PoemController {

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

  @RequestMapping("/add")
  public String add(Model model) {
    return "edit";
  }

  @RequestMapping("/cats/{id}/edit")
  public String edit(@PathVariable Long id, Model model) {
    return "edit";
  }

  @RequestMapping(path = "/edit", method = RequestMethod.POST)
  public String addOrEdit (Model model) {
    Long id = 0L;
    return "redirect:/cats/" + id;
  }
}