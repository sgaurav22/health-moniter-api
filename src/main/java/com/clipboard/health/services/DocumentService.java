package com.clipboard.health.services;

import com.clipboard.health.domains.Document;
import com.clipboard.health.exceptions.ClipboardException;

import java.util.List;

public interface DocumentService {

    public List<Document> findAll() throws ClipboardException;

    public List<Document> findAll(int offset, int limit) throws ClipboardException;

    public Document findById(Integer id) throws ClipboardException;

    public Iterable<Document> saveAll(List<Document> documents) throws ClipboardException;

    public Document save(Document document) throws ClipboardException;

    public void delete(int id) throws ClipboardException;

}
