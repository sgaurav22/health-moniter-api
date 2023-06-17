package com.clipboard.health.repositories;

import com.clipboard.health.domains.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends PagingAndSortingRepository<Facility, Integer>, CrudRepository<Facility, Integer> {
}
