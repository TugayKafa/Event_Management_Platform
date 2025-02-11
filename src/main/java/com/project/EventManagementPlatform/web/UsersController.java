package com.project.EventManagementPlatform.web;

import com.project.EventManagementPlatform.dto.ShowUserInformationDto;
import com.project.EventManagementPlatform.dto.UserUpdateDto;
import com.project.EventManagementPlatform.entity.Role;
import com.project.EventManagementPlatform.entity.User;
import com.project.EventManagementPlatform.exception.UserNotFoundException;
import com.project.EventManagementPlatform.mapper.EntityMapper;
import com.project.EventManagementPlatform.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("users")
@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsersData(Model model) {
        List<User> allUsers = userService.getAllUsers();
        List<ShowUserInformationDto> users = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++) {
            users.add(EntityMapper.mapEntityToDto(allUsers.get(i), ShowUserInformationDto.class));
        }
        model.addAttribute("users", users);
        return "users";
    }

    @PutMapping("{username}")
    public String updateUserData(@PathVariable String username, @Valid @ModelAttribute UserUpdateDto userUpdateDto, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            user.setFirstName(userUpdateDto.getFirstName());
            user.setLastName(userUpdateDto.getLastName());
            user.setEmail(userUpdateDto.getEmail());
            userService.updateUser(user);
            model.addAttribute("user", user);
            return "redirect:/users/" + user.getUsername();
        } catch (UserNotFoundException ex) {
            return "error/404";
        }
    }

    @GetMapping("{username}")
    public String loadEditUserData(@PathVariable String username, Model model) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals(Role.ADMIN.getDescription()));

        boolean isSameUser = loggedInUsername.equals(username);

        if (!isAdmin && !isSameUser) {
            return "error/404";
        }

        model.addAttribute("user", userService.getUserByUsername(username));
        return "editUser";
    }

    @DeleteMapping("{username}")
    public String deleteUsersData(@PathVariable String username, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = userService.getUserByUsername(username);
            userService.deleteUser(user);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, response, null);
        } catch (UserNotFoundException ex) {
            return "error/404";
        }

        return "redirect:/home";
    }

}
