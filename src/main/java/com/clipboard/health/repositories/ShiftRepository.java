package com.clipboard.health.repositories;

import com.clipboard.health.domains.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends PagingAndSortingRepository<Shift, Integer>, CrudRepository<Shift, Integer> {

    //@Query(value = "select * from 'Shift' where is_deleted = {isDeleted}", nativeQuery = true)
    Page<Shift> findByIsDeleted(boolean isDeleted, Pageable pageable);

    @Query(value = "select distinct on (s.id) s.id, s.\"start\", s.\"end\" , s.\"profession\", s.is_deleted , s.facility_id , s.worker_id  \n" +
            "from \"Shift\" s \n" +
            "left join \"Facility\" f ON\n" +
            "s.facility_id = f.id \n" +
            "and f.is_active = ?2\n" +
            "left join \"Worker\" w ON \n" +
            "s.worker_id = w.id\n" +
            "and s.\"profession\"  = w.\"profession\"\n" +
            "where s.facility_id is not null \n" +
            "and s.is_deleted = ?1 \n" +
            "and s.worker_id is null ", nativeQuery = true)
    public List<Shift> getActiveShifts(boolean shiftDeleted, boolean facilityActive, Pageable pageable);
}
