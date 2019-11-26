package cn.myblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminContentController {

    /**
     * To dashboard if already login in, or else to login page
     *
     * @param model model
     * @return "login.html" or "dashboard.html"
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin/dashboard";
    }
}
