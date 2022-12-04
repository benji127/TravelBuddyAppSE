package com.example.travelbuddyapp.web;


import com.example.travelbuddyapp.entities.ConfirmationType;
import com.example.travelbuddyapp.entities.User;
import com.example.travelbuddyapp.repositories.ConfirmationRepository;
import com.example.travelbuddyapp.repositories.UserRepository;

import com.example.travelbuddyapp.services.UserServiceImpl;
import com.example.travelbuddyapp.utility.CommonMethods;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ConfirmationRepository confirmationRepository;

    @PostMapping("/login")
    public String login(Model model, User loginCred, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "#home";
        }
        try {
            User user = UserServiceImpl.loginUser(loginCred, repository);
            session.setAttribute("user", user);
            //model.addAttribute("user",user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "LoggedIn";

    }

    @GetMapping(path = "/loginok")
    public String testLog(Model model, @RequestParam(name = "email", defaultValue = "")
    String email,@RequestParam(name = "password", defaultValue = "")
                          String password) {
        return "LoggedIn";

    }


    @GetMapping("/logout")
    public String logout(Model model, User user, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        session.invalidate();
        return "redirect:#home";
    }

    @GetMapping("/verify")
    public String verifyLogin(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "byLocation";
        } else {
            return "redirect:#home";
        }
    }

    @PostMapping(path = "/register")
    public String register(Model model, @Valid User user, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "#home";
        } else {
            User u = UserServiceImpl.registerUser(user, repository);
            if(u != null && user.getFile() != null){
                CommonMethods.createConfirmation(confirmationRepository, user.getFile(), u.getId(), null,
                        ConfirmationType.ID.getId());
            }
            return "redirect:#home";
        }

    }

    @PostMapping("/editUser")
    public String editUser(Long id, Model model, @Valid User userRequest, BindingResult
            bindingResult, ModelMap mm, HttpSession session){
        User user = repository.findById(String.valueOf(id)).orElse(null);
        userRequest.setId(user.getId());
        UserServiceImpl.editUser(userRequest, repository);
        if(userRequest.getFile() != null){
            CommonMethods.createConfirmation(confirmationRepository, user.getFile(), user.getId(), null,
                    ConfirmationType.ID.getId());
        }
        return "userProfile";
    }

    @GetMapping(path = "/Register")
    public String regPage(Model model){
        return "Register";
    }

    @GetMapping(path = "/terms")
    public String terms(Model model){
        return "terms";
    }
    @GetMapping(path = "/userProfile")
    public String userProf(Model model){
        return "userProfile";
    }

    @GetMapping(path = "/editProfile")
    public String editProf(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("user"));
        return "editProfile";
    }
    @GetMapping(path = "/loginPage")
    public String loginPage(Model model){
        return "loginForm";
    }

    @InitBinder     /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}