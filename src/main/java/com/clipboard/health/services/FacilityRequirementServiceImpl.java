package com.clipboard.health.services;

import com.clipboard.health.common.ClipboardConstant;
import com.clipboard.health.domains.FacilityRequirement;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.repositories.FacilityRequirementRepository;
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
@Transactional(readOnly = true)
public class FacilityRequirementServiceImpl implements FacilityRequirementService {

    @Autowired
    private FacilityRequirementRepository facilityRequirementRepository;

    @Override
    @Cacheable(value = "facility_requirements")
    public List<FacilityRequirement> findAll() throws ClipboardException {
        return findAll(ClipboardConstant.DEFAULT_OFFSET, ClipboardConstant.DEFAULT_PAGE_LIMIT);
    }

    @Override
    @Cacheable(value = "facility_requirements_offset")
    public List<FacilityRequirement> findAll(int offset, int limit) throws ClipboardException {
        List<FacilityRequirement> list = new ArrayList<>();
        Page<FacilityRequirement> page = facilityRequirementRepository.findAll(PageRequest.of(offset, limit));
        page.forEach(list::add);
        return list;
    }

    @Override
    @Cacheable(value = "facility_requirements_id")
    public FacilityRequirement findById(Integer id) throws ClipboardException {
        Optional<FacilityRequirement> optionalFacilityRequirement = facilityRequirementRepository.findById(id);
        return optionalFacilityRequirement.orElseThrow();
    }

    @Override
    @Transactional
    public Iterable<FacilityRequirement> saveAll(List<FacilityRequirement> facilityRequirements) throws ClipboardException {
        return facilityRequirementRepository.saveAll(facilityRequirements);
    }

    @Override
    @Transactional
    public FacilityRequirement save(FacilityRequirement facilityRequirement) throws ClipboardException {
        return facilityRequirementRepository.save(facilityRequirement);
    }

    @Override
    public void delete(int id) throws ClipboardException {
        facilityRequirementRepository.deleteById(id);
    }
}
