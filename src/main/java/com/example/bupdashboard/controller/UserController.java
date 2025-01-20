package com.example.bupdashboard.controller;

import com.example.bupdashboard.dao.UserDto;
import com.example.bupdashboard.dashboardDb.entity.Role;
import com.example.bupdashboard.dashboardDb.entity.User;
import com.example.bupdashboard.dashboardDb.repository.RoleRepository;
import com.example.bupdashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("noResults", users.isEmpty());
        return "users";
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);

            redirectAttributes.addFlashAttribute("message", "The User with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> roles = roleRepository.findAll(); // Fetch all roles
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user-edit";
    }

    @PostMapping("/users/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(id, user);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        return "redirect:/users";
    }

    @GetMapping("/users/search")
    public String searchUses(@RequestParam("query") String query, Model model) {
        // Fetch users based on the search query
        List<User> users = userService.getUserByUserEmail(query);
        model.addAttribute("users", users);
        model.addAttribute("noResults", users.isEmpty()); // Add a flag for empty results
        return "users :: #user-container";
    }
}
