package cn.myblog.controller.admin.api;

import cn.myblog.model.dto.UserDTO;
import cn.myblog.model.param.RegistryParam;
import cn.myblog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation("Register a new user")
    public UserDTO registry(@RequestBody @Valid RegistryParam registryParam) {
        return userService.register(registryParam);
    }
}
