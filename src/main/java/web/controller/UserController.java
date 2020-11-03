package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.Optional;


@Controller
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView allUsers(@AuthenticationPrincipal UserDetails authUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        //Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userCurrent = userService.getUserByName(authUser.getUsername());
        modelAndView.addObject("userCurrent", userCurrent.get());
        return modelAndView;
    }

}
