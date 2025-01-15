package web.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import web.dao.UserDAO;
import web.dao.UserDAOImpl;
import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UsersController {

    private final UserDAO userDAOImpl;

    @Autowired
    public UsersController(UserDAO userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userDAOImpl.getUsersList());
        return "people/index";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userDAOImpl.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        userDAOImpl.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userDAOImpl.show(id));
        return "people/edit";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        userDAOImpl.update(id, user);
        System.out.println(user);
        return "redirect:/";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userDAOImpl.delete(id);
        return "redirect:/";
    }
}