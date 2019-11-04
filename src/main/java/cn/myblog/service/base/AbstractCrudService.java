package cn.myblog.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<DOMAIN,ID> implements CrudService<DOMAIN,ID> {

    private final JpaRepository<DOMAIN,ID> repository;

    public AbstractCrudService(JpaRepository<DOMAIN, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<DOMAIN> listAll() {
        return repository.findAll();
    }

    @Override
    public List<DOMAIN> listAll(Sort sort) {
        Assert.notNull(sort,"Sort info must not be null");
        return repository.findAll(sort);
    }

    @Override
    public Page<DOMAIN> listAll(Pageable pageable) {
        Assert.notNull(pageable,"Pageable info must not be null");
        return repository.findAll(pageable);
    }

    @Override
    public List<DOMAIN> listAllByIds(Collection<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllById(ids);
    }

    @Override
    public Optional<DOMAIN> fetchById(ID id) {
        return Optional.empty();
    }

    @Override
    public DOMAIN getById(ID id) {
        return null;
    }

    @Override
    public boolean existsById(ID id) {
        return false;
    }
}
