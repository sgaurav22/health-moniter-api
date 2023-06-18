package com.clipboard.health.services;

import com.clipboard.health.common.ClipboardConstant;
import com.clipboard.health.domains.DocumentWorker;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.repositories.DocumentWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentWorkerServiceImpl implements DocumentWorkerService{

    @Autowired
    private DocumentWorkerRepository documentWorkerRepository;

    @Override
    public List<DocumentWorker> findAll() throws ClipboardException {
        return findAll(ClipboardConstant.DEFAULT_OFFSET, ClipboardConstant.DEFAULT_PAGE_LIMIT);
    }

    @Override
    public List<DocumentWorker> findAll(int offset, int limit) throws ClipboardException {
        List<DocumentWorker> list = new ArrayList<>();
        Page<DocumentWorker> page = documentWorkerRepository.findAll(PageRequest.of(offset, limit));
        page.forEach(list::add);
        return list;
    }

    @Override
    public DocumentWorker findById(Integer id) throws ClipboardException {
        Optional<DocumentWorker> optionalDocumentWorker = documentWorkerRepository.findById(id);
        return optionalDocumentWorker.orElseThrow();
    }

    @Override
    public Iterable<DocumentWorker> saveAll(List<DocumentWorker> documentWorkers) throws ClipboardException {
        return null;
    }

    @Override
    public DocumentWorker save(DocumentWorker documentWorker) throws ClipboardException {
        return null;
    }

    @Override
    public void delete(int id) throws ClipboardException {

    }
}
