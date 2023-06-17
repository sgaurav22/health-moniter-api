package com.clipboard.health.services;

import com.clipboard.health.domains.FacilityRequirement;
import com.clipboard.health.exceptions.ClipboardException;

import java.util.List;

public interface FacilityRequirementService {

    public List<FacilityRequirement> findAll() throws ClipboardException;

    public List<FacilityRequirement> findAll(int offset, int limit) throws ClipboardException;

    public FacilityRequirement findById(Integer id) throws ClipboardException;

    public Iterable<FacilityRequirement> saveAll(List<FacilityRequirement> facilityRequirements) throws ClipboardException;

    public FacilityRequirement save(FacilityRequirement facilityRequirement) throws ClipboardException;

    public void delete(int id) throws ClipboardException;
}
