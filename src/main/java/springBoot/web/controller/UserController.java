package springBoot.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class UserController {


    @GetMapping(value = "/*")
    public String loginPage() {
        return "login";
    }
    @GetMapping(value = "/panel")
    public String getTable(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "table";
    }
}

