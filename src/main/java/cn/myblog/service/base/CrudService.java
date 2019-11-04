package cn.myblog.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CrudService<DOMAIN,ID> {

    /**
     * List All
     *
     * @return List
     */
    @NonNull
    List<DOMAIN> listAll();

    /**
     * List all by sort
     *
     * @param sort sort
     * @return List
     */
    @NonNull
    List<DOMAIN> listAll(@NonNull Sort sort);

    /**
     * List all by pageable
     *
     * @param pageable pageable
     * @return Page
     */
    @NonNull
    Page<DOMAIN> listAll(@NonNull Pageable pageable);

    /**
     * List all by ids
     *
     * @param ids ids
     * @return List
     */
    @NonNull
    List<DOMAIN> listAllByIds(@Nullable Collection<ID> ids);

    /**
     *
     *
     * @param id id
     * @return optional
     */
    @NonNull
    Optional<DOMAIN> fetchById(@NonNull ID id);

    /**
     *
     * @param id id
     * @return DOMAIN
     */
    @NonNull
    DOMAIN getById(@NonNull ID id);

    /**
     * If exists
     *
     * @param id id
     * @return boolean
     */
    boolean existsById(@NonNull ID id);
}
