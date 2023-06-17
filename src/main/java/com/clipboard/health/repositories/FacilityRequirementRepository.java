package com.clipboard.health.repositories;

import com.clipboard.health.domains.FacilityRequirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FacilityRequirementRepository extends PagingAndSortingRepository<FacilityRequirement, Integer>, CrudRepository<FacilityRequirement, Integer> {
}