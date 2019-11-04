package cn.myblog.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * To dashboard if already login in, or else to login page
     *
     * @param model model
     * @return "login.html" || "dashboard.html"
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        Object user = model.getAttribute("user");
        if (user == null) {
            return "/admin/login";
        }

        return "/admin/dashboard";
    }
}
