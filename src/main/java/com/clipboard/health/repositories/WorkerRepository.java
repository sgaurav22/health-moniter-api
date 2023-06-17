package com.clipboard.health.repositories;

import com.clipboard.health.domains.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends PagingAndSortingRepository<Worker, Integer>, CrudRepository<Worker, Integer> {
}
