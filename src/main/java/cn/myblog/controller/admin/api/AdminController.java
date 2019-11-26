package cn.myblog.controller.admin.api;

import cn.myblog.model.param.LoginParam;
import cn.myblog.security.token.AuthToken;
import cn.myblog.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AuthToken auth(@RequestBody @Valid LoginParam loginParam) {
        return adminService.authenticate(loginParam);
    }
}
