package cn.myblog.controller.admin.api;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.param.LoginParam;
import cn.myblog.security.token.AuthToken;
import cn.myblog.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("login")
    @ApiOperation("Authenticate an user")
    public AuthToken auth(@RequestBody @Valid LoginParam loginParam,
                          HttpSession session) {
        if (session.getAttribute("token") != null) {
            throw new BadRequestException("请勿重复登录！");
        }
        AuthToken token = adminService.authenticate(loginParam);
        session.setAttribute("token", token);
        return token;
    }

    @GetMapping("logout")
    @ApiOperation("Logout from admin system")
    public void logout(HttpSession session) {
        if (session.getAttribute("token") != null) {
            session.removeAttribute("token");
        }
    }
}
