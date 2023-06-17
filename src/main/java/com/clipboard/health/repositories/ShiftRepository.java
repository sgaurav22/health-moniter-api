package com.clipboard.health.repositories;

import com.clipboard.health.domains.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends PagingAndSortingRepository<Shift, Integer>, CrudRepository<Shift, Integer> {
}
