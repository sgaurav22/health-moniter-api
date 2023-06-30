package com.clipboard.health.services;

import com.clipboard.health.domains.Shift;
import com.clipboard.health.exceptions.ClipboardException;

import java.sql.SQLException;
import java.util.List;

public interface ShiftService {

    public List<Shift> getActiveShifts(int offset, int limit ) throws SQLException;

    public List<Shift> findAll() throws ClipboardException;

    public Shift findById(int id) throws ClipboardException;

    public List<Shift> findAll(int offset, int limit) throws ClipboardException, SQLException;

    public Iterable<Shift> saveAll(List<Shift> shifts) throws ClipboardException;

    public Shift save(Shift shift) throws ClipboardException;

    public void delete(int id) throws ClipboardException;

    List<Shift> findByIsDeleted(boolean deleted, int offset, int limit) throws SQLException;
}