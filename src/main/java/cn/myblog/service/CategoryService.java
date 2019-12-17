package cn.myblog.service;

import cn.myblog.model.dto.CategoryDTO;
import cn.myblog.model.entity.Category;
import cn.myblog.model.param.CategoryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

public interface CategoryService {

    /**
     * Get a category of non-null
     *
     * @param id id of the category
     * @return category
     */
    @NonNull
    Category fetchBy(@NonNull Integer id);

    /**
     * Get a category or return default category
     *
     * @param id id
     * @return category
     */
    @NonNull
    Category fetchOrGetDefaultBy(@NonNull Integer id);

    /**
     * List top 10 category by sort
     *
     * @param sort sort
     * @return Page<Category>
     */
    @NonNull
    Page<Category> top10By(@NonNull Sort sort);

    /**
     * List of categories
     *
     * @param pageable pageable
     * @return categories
     */
    @NonNull
    Page<Category> pageBy(@NonNull Pageable pageable);

    /**
     * Add a new category
     *
     * @param categoryParam category parameter
     * @return category DTO
     */
    @NonNull
    CategoryDTO saveBy(@NonNull CategoryParam categoryParam);

    /**
     * Update a category
     *
     * @param id            id of the category
     * @param categoryParam category parameter
     * @return Category DTO
     */
    @NonNull
    CategoryDTO updateBy(@NonNull Integer id, @NonNull CategoryParam categoryParam);

    /**
     * Delete a category
     *
     * @param id id of the category
     * @return CategoryDTO
     */
    CategoryDTO deleteBy(@NonNull Integer id);
}
