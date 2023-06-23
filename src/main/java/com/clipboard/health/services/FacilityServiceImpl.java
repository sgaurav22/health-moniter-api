package com.clipboard.health.services;

import com.clipboard.health.common.ClipboardConstant;
import com.clipboard.health.domains.Facility;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.repositories.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository repository;

    @Override
    @Cacheable(value = "facilities")
    public List<Facility> findAll() throws ClipboardException {
        List<Facility> list = this.findAll(ClipboardConstant.DEFAULT_OFFSET, ClipboardConstant.DEFAULT_PAGE_LIMIT);
        return list;
    }

    @Override
    @Cacheable(value = "facilities_id")
    public Facility findById(int id) throws ClipboardException {
        Optional<Facility> optional = repository.findById(id);
        return optional.orElseThrow();
    }

    @Override
    @Cacheable(value = "facilities_offset")
    public List<Facility> findAll(int offset, int limit) throws ClipboardException {
        List<Facility> list = new ArrayList<>();
        Page<Facility> documents = repository.findAll(PageRequest.of(offset, limit));
        documents.forEach(list::add);
        return list;
    }

    public Iterable<Facility> saveAll(List<Facility> facility) throws ClipboardException {
        return repository.saveAll(facility);
    }

    @Override
    public Facility save(Facility facility) throws ClipboardException {
        return repository.save(facility);
    }

    @Override
    public void delete(int id) throws ClipboardException {
        repository.deleteById(id);
    }
}
