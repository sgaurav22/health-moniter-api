package com.clipboard.health.services;

import com.clipboard.health.domains.Worker;
import com.clipboard.health.exceptions.ClipboardException;

import java.util.List;

public interface WorkerService {

    public List<Worker> findAll() throws ClipboardException;

    public Worker findById(int id) throws ClipboardException;

    public List<Worker> findAll(int offset, int limit) throws ClipboardException;

    public Iterable<Worker> saveAll(List<Worker> workers) throws ClipboardException;

    public Worker save(Worker worker) throws ClipboardException;

    public void delete(int id) throws ClipboardException;
}
