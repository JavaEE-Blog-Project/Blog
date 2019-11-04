package cn.myblog.service.impl;

import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.JournalParam;
import cn.myblog.repository.JournalRepository;
import cn.myblog.service.JournalService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    @Cacheable(cacheNames = "journals",key = "#pageable")
    public List<JournalDTO> pageBy(JournalType type, Pageable pageable) {
        return journalRepository
                .findAllByType(type,pageable)
                .stream()
                .map(journal -> {
                    JournalDTO journalDTO = new JournalDTO().convertFrom(journal);
                    return journalDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public JournalDTO saveBy(JournalParam journalParam) {
        return null;
    }
}
