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

import java.util.List;

/**
 * @author Lazyzzz
 */
public interface JournalRepository extends JpaRepository<Journal, Integer>, JpaSpecificationExecutor<Journal> {

    @NonNull
    Page<Journal> findAllByType(@NonNull JournalType type, @NonNull Pageable pageable);

    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Journal j set j.visits = j.visits+1 where j.id = ?1")
    void incrVisits(@NonNull Integer id);

//    @Query("select function('date_format',j.updateTime,'%Y') as year from Journal j group by function('date_format',j.updateTime,'%Y') order by year desc")
//    List<String> findGroupYear();

    @Query("select j from Journal j where function('YEAR', j.updateTime, '%Y') = ?1")
    List<Journal> findByYear(Integer year);
}
