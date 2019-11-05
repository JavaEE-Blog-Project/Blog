package cn.myblog.service;

import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.JournalParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface JournalService {

    /**
     * Lists by type.
     *
     * @param type journal type must not be null
     * @param pageable page info must not be null
     * @return a page of journal
     */
    @NonNull
    Page<Journal> pageBy(@NonNull JournalType type, @NonNull Pageable pageable);

    /**
     * Save the journal
     *
     * @param journalParam journal parameter cannot be null
     * @return Journal DTO
     */
    @NonNull
    JournalDTO saveBy(@NonNull JournalParam journalParam);


    /**
     * Updating an existed journal
     *
     * @param id           the id of the journal
     * @param journalParam journal parameter
     * @return JournalDTO
     */
    @NonNull
    JournalDTO updateBy(@NonNull Integer id, @NonNull JournalParam journalParam);

    /**
     * Deleting an existed journal
     *
     * @param id the id of the journal
     * @return JournalDTO
     */
    @NonNull
    JournalDTO deleteBy(@NonNull Integer id);
}
