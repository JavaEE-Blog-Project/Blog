package cn.myblog.service.impl;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.entity.Category;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.JournalParam;
import cn.myblog.model.param.JournalQuery;
import cn.myblog.repository.JournalRepository;
import cn.myblog.service.CategoryService;
import cn.myblog.service.JournalService;
import cn.myblog.utils.MarkdownUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;

    private final CategoryService categoryService;

    public JournalServiceImpl(JournalRepository journalRepository, CategoryService categoryService) {
        this.journalRepository = journalRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Journal fetchBy(Integer id) {
        return journalRepository.findById(id).orElseThrow(() -> new BadRequestException("文章不存在"));
    }

    @Override
//    @Cacheable(cacheNames = "journals", key = "#pageable")
    public Page<Journal> pageBy(Pageable pageable) {
        return journalRepository.findAll(pageable);
    }

    @Override
//    @Cacheable(cacheNames = "journals", key = "#pageable")
    public Page<Journal> pageBy(JournalType type, Pageable pageable) {
        return journalRepository.findAllByType(type, pageable);
    }

    @Override
//    @Cacheable(cacheNames = "journals", key = "#journalQuery")
    public Page<Journal> pageBy(JournalQuery journalQuery, Pageable pageable) {
        return journalRepository.findAll(buildSpecQuery(journalQuery), pageable);
    }

    @Override
    public Map<String, List<Journal>> groupByYear() {
        Map<String, List<Journal>> map = new HashMap<>();
        map.put("2019", journalRepository.findByYear(2019));
        return map;
    }

    @Override
//    @CacheEvict(cacheNames = "journals", allEntries = true)
    public JournalDTO saveBy(JournalParam journalParam) {
        Category category = categoryService.fetchOrGetDefaultBy(journalParam.getCategoryId());
        Journal journal = journalParam.convertTo(new Journal());

        String formatContent = MarkdownUtils.toHtml(journalParam.getOriginalContent());
        String summary = StringUtils.substring(MarkdownUtils.cleanHtmlTag(formatContent), 0, 100);

        journal.setFormatContent(formatContent);
        journal.setSummary(summary);
        journal.setCategory(category);
        journalRepository.save(journal);

        return new JournalDTO().convertFrom(journal);
    }

    @Override
//    @CachePut(cacheNames = "journal", key = "#id")
//    @CacheEvict(cacheNames = "journals", allEntries = true)
    public JournalDTO updateBy(Integer id, JournalParam journalParam) {
        Journal journal = fetchBy(id);
        Category category = categoryService.fetchOrGetDefaultBy(journalParam.getCategoryId());
        Journal updated = journalParam.convertTo(journal);
        updated.setCategory(category);
        journalRepository.save(updated);
        return new JournalDTO().convertFrom(updated);
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(cacheNames = "journal", key = "#id"),
//            @CacheEvict(cacheNames = "journals", allEntries = true)
//    })
    public JournalDTO deleteBy(Integer id) {
        Journal journal = fetchBy(id);
        journalRepository.delete(journal);
        return new JournalDTO().convertFrom(journal);
    }

    @Override
    public void incrVisitsBy(Integer id) {
        journalRepository.incrVisits(id);
    }

    @NonNull
    private Specification<Journal> buildSpecQuery(@NonNull JournalQuery journalQuery) {
        return (Specification<Journal>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            if (journalQuery.getKeyword() != null) {
                String likeCondition = String.format("%%%s%%", StringUtils.strip(journalQuery.getKeyword()));
                predicates.add(criteriaBuilder.like(root.get("title"), likeCondition));
            }

            if (journalQuery.getType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), journalQuery.getType()));
            }

            if (journalQuery.getCategoryId() != null) {
                Category category = categoryService.fetchOrGetDefaultBy(journalQuery.getCategoryId());
                predicates.add(criteriaBuilder.equal(root.get("category"), category));
            }

            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
