package com.clipboard.health.services;

import com.clipboard.health.domains.DocumentWorker;
import com.clipboard.health.exceptions.ClipboardException;

import java.util.List;

public interface DocumentWorkerService {

    public List<DocumentWorker> findAll() throws ClipboardException;

    public List<DocumentWorker> findAll(int offset, int limit) throws ClipboardException;

    public DocumentWorker findById(Integer id) throws ClipboardException;

    public Iterable<DocumentWorker> saveAll(List<DocumentWorker> documentWorkers) throws ClipboardException;

    public DocumentWorker save(DocumentWorker documentWorker) throws ClipboardException;

    public void delete(int id) throws ClipboardException;
}
