package com.clipboard.health.repositories;

import com.clipboard.health.domains.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends PagingAndSortingRepository<Document, Integer>, CrudRepository<Document, Integer> {
    boolean existsById(Integer id);

    /*Page<Document> findAll();*/
}