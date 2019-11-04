package cn.myblog.repository;

import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface JournalRepository extends JpaRepository<Journal,Integer> {
    @NonNull
    Page<Journal> findAllByType(@NonNull JournalType type, @NonNull Pageable pageable);
}
