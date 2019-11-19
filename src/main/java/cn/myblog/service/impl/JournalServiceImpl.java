package cn.myblog.service.impl;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.JournalParam;
import cn.myblog.model.param.JournalQuery;
import cn.myblog.repository.JournalRepository;
import cn.myblog.service.JournalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;


@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    @Cacheable(cacheNames = "journal", key = "#id")
    public Journal fetchBy(Integer id) {
        return journalRepository.findById(id).orElseThrow(() -> new BadRequestException("文章不存在"));
    }

    @Override
    @Cacheable(cacheNames = "journals", key = "#pageable")
    public Page<Journal> pageBy(JournalType type, Pageable pageable) {
        return journalRepository.findAllByType(type, pageable);
    }

    @Override
    public Page<Journal> pageBy(JournalQuery journalQuery, Pageable pageable) {
        return journalRepository.findAll(buildSpecQuery(journalQuery), pageable);
    }

    @Override
    @CacheEvict(cacheNames = "journals", allEntries = true)
    public JournalDTO saveBy(JournalParam journalParam) {
        Journal journal = journalParam.convertTo(new Journal());
        journalRepository.save(journal);
        return new JournalDTO().convertFrom(journal);
    }

    @Override
    public JournalDTO updateBy(Integer id, JournalParam journalParam) {
        Journal journal = fetchBy(id);
        Journal updated = journalParam.convertTo(journal);
        journalRepository.save(updated);
        return new JournalDTO().convertFrom(updated);
    }

    @Override
    public JournalDTO deleteBy(Integer id) {
        Journal journal = fetchBy(id);
        journalRepository.delete(journal);
        return new JournalDTO().convertFrom(journal);
    }

    @NonNull
    private Specification<Journal> buildSpecQuery(@NonNull JournalQuery journalQuery) {
        return (Specification<Journal>) (root, query, criteriaBuilder) -> {
            String likeCondition = String.format("%%%s%%", StringUtils.strip(journalQuery.getKeyword()));
            Predicate likeTitle = criteriaBuilder.like(root.get("title"), likeCondition);
            Predicate equalType = criteriaBuilder.equal(root.get("type"), journalQuery.getType());
            return criteriaBuilder.and(equalType, likeTitle);
        };
    }
}
