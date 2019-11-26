package cn.myblog.service.impl;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.dto.CategoryDTO;
import cn.myblog.model.entity.Category;
import cn.myblog.model.param.CategoryParam;
import cn.myblog.repository.CategoryRepository;
import cn.myblog.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category fetchBy(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new BadRequestException("分类不存在"));
    }

    @Override
    public Category fetchOrGetDefaultBy(Integer id) {
        return categoryRepository.findById(id).orElse(this.fetchBy(1));
    }

    @Override
//    @Cacheable(cacheNames = "categories", key = "#sort")
    public Page<Category> top10By(Sort sort) {
        return this.pageBy(PageRequest.of(0, 10, sort));
    }

    @Override
//    @Cacheable(cacheNames = "categories", key = "#pageable")
    public Page<Category> pageBy(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public CategoryDTO saveBy(CategoryParam categoryParam) {
        Category category = categoryParam.convertTo(new Category());
        categoryRepository.save(category);
        return new CategoryDTO().convertFrom(category);
    }

    @Override
    public CategoryDTO updateBy(Integer id, CategoryParam categoryParam) {
        Category category = this.fetchBy(id);
        Category update = categoryParam.convertTo(category);
        categoryRepository.save(update);
        return new CategoryDTO().convertFrom(update);
    }

    @Override
    public CategoryDTO deleteBy(Integer id) {
        Category category = this.fetchBy(id);
        categoryRepository.delete(category);
        return new CategoryDTO().convertFrom(category);
    }
}
