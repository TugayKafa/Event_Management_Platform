package com.project.EventManagementPlatform.web;

import com.project.EventManagementPlatform.dto.UserDto;
import com.project.EventManagementPlatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @GetMapping("/hi")
    public String hi(Model model) {
        return "hi";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        userService.createUser(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
