package cn.myblog.controller.admin.api;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.CategoryParam;
import cn.myblog.model.param.JournalParam;
import cn.myblog.model.param.RegistryParam;
import cn.myblog.model.support.BaseResponse;
import cn.myblog.service.CategoryService;
import cn.myblog.service.JournalService;
import cn.myblog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/install")
public class InstallController {

    private final UserService userService;

    private final JournalService journalService;

    private final CategoryService categoryService;

    public InstallController(UserService userService,
                             JournalService journalService,
                             CategoryService categoryService) {
        this.userService = userService;
        this.journalService = journalService;
        this.categoryService = categoryService;
    }

    @PostMapping
    @ApiOperation("Initialize the blog system")
    public BaseResponse<String> install(@RequestBody @Valid RegistryParam registryParam) {
        if (userService.hasUser()) {
            throw new BadRequestException("博客已经安装，不能重复安装！");
        }
        //register admin account
        userService.register(registryParam);

        //initialize the blog system
        init();

        return BaseResponse.ok("博客安装成功");
    }

    private void init() {
        //Initialize default category
        CategoryParam categoryParam = new CategoryParam();
        categoryParam.setName("未归类");
        categoryService.saveBy(categoryParam);

        //Initialize default journal
        JournalParam journalParam = new JournalParam();
        journalParam.setTitle("Hello MyBlog");
        journalParam.setImage("https://images.unsplash.com/photo-1575072882177-b21fde91400f" +
                "?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
        journalParam.setOriginalContent("## Hello MyBlog\n" +
                "\n" +
                "欢迎您使用此博客系统进行创作，请删除此文章开始创作吧！");
        journalParam.setType(JournalType.PUBLIC);
        journalParam.setCategoryId(1);
        journalService.saveBy(journalParam);
    }
}
