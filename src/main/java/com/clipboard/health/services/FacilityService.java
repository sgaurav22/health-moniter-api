package com.clipboard.health.services;

import com.clipboard.health.domains.Facility;
import com.clipboard.health.exceptions.ClipboardException;

import java.util.List;

public interface FacilityService {

    public List<Facility> findAll() throws ClipboardException;
    public Facility findById(int id) throws ClipboardException;
    public List<Facility> findAll(int offset, int limit) throws ClipboardException;
    public Iterable<Facility> saveAll(List<Facility> facility) throws ClipboardException;
    public Facility save(Facility facility) throws ClipboardException;

    public void delete(int id) throws ClipboardException;
}
