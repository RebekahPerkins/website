package com.rebekahperkins.website.web.controller;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.User;
import com.rebekahperkins.website.domain.UserEntity;
import com.rebekahperkins.website.service.PoemService;
import com.rebekahperkins.website.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import javax.validation.Valid;

@Controller
public class PoemController {

  @Autowired
  private PoemService poemService;

  @RequestMapping("/cats")
  public String getLangingPage(Model model, Pageable pageable, Principal principal) {
    Page<Poem> page = poemService.findAll(pageable);
    model.addAttribute("page", page);
    model.addAttribute("referrer", "/cats");
    model.addAttribute("principal", principal);
    return "cats";
  }

  @RequestMapping(path = "/cats/{id}/favorite", method = RequestMethod.POST)
  public String toggle(@PathVariable Long id, Model model, Principal principal) {
    UserEntity user = (UserEntity)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    poemService.toggleFavorite(id, user);

    return "redirect:/cats/" + id;
  }

  @RequestMapping("/my_cats")
  public String getMyCats(Model model, Pageable pageable, Principal principal) {
    UserEntity user = (UserEntity)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

    Page<Poem> page = poemService.findBySubmittedBy(user, pageable);
    model.addAttribute("page", page);
    model.addAttribute("referrer", "/my_cats");
    model.addAttribute("principal", principal);
    return "my_cats";
  }

  @RequestMapping("/my_favorites")
  public String getMyFavoriteCats(Model model, Pageable pageable, Principal principal) {
    UserEntity user = (UserEntity)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

    Page<Poem> page = poemService.findFavorites(user, pageable);
    model.addAttribute("page", page);
    model.addAttribute("referrer", "/my_favorites");
    model.addAttribute("principal", principal);
    return "my_favorites";
  }

  @RequestMapping("cats/{id}")
  public String detail(@PathVariable Long id, Model model, Principal principal) {
    UserEntity user = (UserEntity)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

    Poem poem = poemService.get(id);
    poem.setFavorite(poemService.isFavorite(id, user.getId()));
    model.addAttribute("poem", poem);
    boolean canEdit = poem.getSubmittedBy().getId() == user.getId();
    model.addAttribute("canedit", canEdit);
    model.addAttribute("principal", principal);
    return "detail";
  }

  @RequestMapping(path = "/add", method = RequestMethod.GET)
  public String add(Model model, Principal principal) {
    model.addAttribute("header", "Contribute");
    model.addAttribute("principal", principal);
    if (!model.containsAttribute("poem")) {
      model.addAttribute("poem", new Poem());
    }
    return "edit";
  }

  @RequestMapping(path = "/cats/{id}/edit", method = RequestMethod.GET)
  public String edit(@PathVariable Long id, Model model, Principal principal) {
    UserEntity user = (UserEntity)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

    Poem poem = poemService.get(id);
    if (poem.getSubmittedBy().getId() != user.getId()){
      return "redirect:/cats/" + id;
    }
    model.addAttribute("principal", principal);
    model.addAttribute("header", "Edit");
    if (!model.containsAttribute("poem")) {
      model.addAttribute("poem", poem);
    }
    return "edit";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST, params={"submit"})
  public String edit (@Valid Poem poem, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.poem", bindingResult);
      redirectAttributes.addFlashAttribute("poem", poem);
      //poem id is null if this is add. otherwise it's edit
      String addOrEditRedirect = poem.getId() == null ? "/add" : String.format("/cats/%d/edit", poem.getId());
      return "redirect:" + addOrEditRedirect;
    }

    UserEntity user = (UserEntity)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    if (poem.getId() == null){
      poem.setSubmittedBy(user);
      poem = poemService.addOrUpdate(poem);
      redirectAttributes.addFlashAttribute("flash", new FlashMessage("Successfully added", FlashMessage.Status.SUCCESS));
    } else {
      Poem poemFromDb = poemService.get(poem.getId());
      if (poemFromDb.getSubmittedBy().getId() == user.getId()) {
        poem.setSubmittedBy(user);
        poemService.addOrUpdate(poem);
        redirectAttributes.addFlashAttribute("flash",
            new FlashMessage("Successfully edited", FlashMessage.Status.SUCCESS));
      }
    }
    return "redirect:/cats/" + poem.getId();
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST, params={"delete"})
  public String delete (Poem poem, Principal principal, RedirectAttributes redirectAttributes) {
    UserEntity loggedInUser = (UserEntity)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    Poem poemFromDb = poemService.get(poem.getId());
    if (poemFromDb.getSubmittedBy().getId() == loggedInUser.getId()) {
      poemService.delete(poemFromDb);
      redirectAttributes.addFlashAttribute("flash", new FlashMessage("Successfully deleted", FlashMessage.Status.SUCCESS));
    }
    return "redirect:/cats";
  }
}