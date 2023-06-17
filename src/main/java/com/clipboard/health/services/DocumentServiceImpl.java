package com.clipboard.health.services;

import com.clipboard.health.common.ClipboardConstant;
import com.clipboard.health.domains.Document;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.repositories.DocumentRepository;
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
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean validateDocument(String id) throws ClipboardException {
        return repository.existsById(Integer.valueOf(id));
    }

    @Override
    public List<Document> findAll() throws ClipboardException {
        List<Document> list = findAll(ClipboardConstant.DEFAULT_OFFSET, ClipboardConstant.DEFAULT_PAGE_LIMIT);
        return list;
    }

    @Override
    public List<Document> findAll(int offset, int limit) throws ClipboardException {
        List<Document> list = new ArrayList<>();
        Page<Document> documents = repository.findAll(PageRequest.of(offset, limit));
        documents.forEach(list::add);
        return list;
    }

    @Override
    public Document findById(Integer id) throws ClipboardException {
        Optional<Document> document = repository.findById(id);
        return document.orElseThrow();
    }

}
