package cn.myblog.repository;

import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lazyzzz
 */
public interface JournalRepository extends JpaRepository<Journal, Integer>, JpaSpecificationExecutor<Journal> {

    /**
     * Find all the journals by type with paging
     *
     * @param type     type
     * @param pageable pageable
     * @return Page<Journal>
     */
    @NonNull
    Page<Journal> findAllByType(@NonNull JournalType type, @NonNull Pageable pageable);

    /**
     * Increase views by 1
     *
     * @param id id
     * @return journal
     */
    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Journal j set j.views = j.views+1 where j.id = ?1")
    void incrViews(@NonNull Integer id);
}
