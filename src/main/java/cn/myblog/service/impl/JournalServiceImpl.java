package cn.myblog.service.impl;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.JournalParam;
import cn.myblog.repository.JournalRepository;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public Page<Journal> pageBy(JournalType type, Pageable pageable) {
        return journalRepository.findAllByType(type, pageable);
    }

    @Override
    public JournalDTO saveBy(JournalParam journalParam) {
        Journal journal = journalParam.convertTo(new Journal());
        journalRepository.save(journal);
        return new JournalDTO().convertFrom(journal);
    }

    @Override
    public JournalDTO updateBy(Integer id, JournalParam journalParam) {
        Journal journal = journalRepository.findById(id).orElseThrow(() -> new BadRequestException("文章不存在"));
        Journal updated = journalParam.convertTo(journal);
        journalRepository.save(updated);
        return new JournalDTO().convertFrom(updated);
    }

    @Override
    public JournalDTO deleteBy(Integer id) {
        Journal journal = journalRepository.findById(id).orElseThrow(() -> new BadRequestException("文章不存在"));
        journalRepository.delete(journal);
        return new JournalDTO().convertFrom(journal);
    }
}
