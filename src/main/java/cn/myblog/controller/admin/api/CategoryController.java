package cn.myblog.controller.admin.api;

import cn.myblog.model.dto.CategoryDTO;
import cn.myblog.model.entity.Category;
import cn.myblog.model.param.CategoryParam;
import cn.myblog.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<Category> pageBy(@RequestParam("page") Integer page,
                                 @RequestParam("limit") Integer limit) {
        Sort sort = Sort.by("updateTime").descending();
        PageRequest pageable = PageRequest.of(page >= 1 ? page - 1 : page, limit, sort);
        return categoryService.pageBy(pageable);
    }

    @PostMapping
    public CategoryDTO createBy(@RequestBody CategoryParam categoryParam) {
        return categoryService.saveBy(categoryParam);
    }

    @PutMapping("{id:\\d+}")
    public CategoryDTO updateBy(@PathVariable Integer id,
                                @RequestBody CategoryParam categoryParam) {
        return categoryService.updateBy(id, categoryParam);
    }

    @DeleteMapping("{id:\\d+}")
    public CategoryDTO deleteBy(@PathVariable Integer id) {
        return categoryService.deleteBy(id);
    }
}
