package com.clipboard.health.repositories;

import com.clipboard.health.domains.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends PagingAndSortingRepository<Shift, Integer>, CrudRepository<Shift, Integer> {

    //@Query(value = "select * from 'Shift' where is_deleted = {isDeleted}", nativeQuery = true)
    Page<Shift> findByIsDeleted(boolean isDeleted, Pageable pageable);
}
