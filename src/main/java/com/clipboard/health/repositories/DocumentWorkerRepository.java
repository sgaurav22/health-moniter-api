package com.clipboard.health.repositories;

import com.clipboard.health.domains.DocumentWorker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentWorkerRepository extends PagingAndSortingRepository<DocumentWorker, Integer>, CrudRepository<DocumentWorker, Integer> {
}