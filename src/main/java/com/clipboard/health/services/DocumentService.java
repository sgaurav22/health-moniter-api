package com.clipboard.health.services;

import com.clipboard.health.domains.Document;
import com.clipboard.health.exceptions.ClipboardException;

import java.util.List;

public interface DocumentService {

    public boolean validateDocument(String id) throws ClipboardException;

    public List<Document> findAll() throws ClipboardException;

    public List<Document> findAll(int offset, int limit) throws ClipboardException;

    public Document findById(Integer id) throws ClipboardException;

}
