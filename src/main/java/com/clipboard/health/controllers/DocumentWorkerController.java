package com.clipboard.health.controllers;

import com.clipboard.health.domains.DocumentWorker;
import com.clipboard.health.services.DocumentWorkerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "DocumentWorker", description = "DocumentWorker Management")
public class DocumentWorkerController {

    Logger logger = LoggerFactory.getLogger(DocumentWorkerController.class);

    @Autowired
    private DocumentWorkerService documentWorkerService;

    @GetMapping("/documentWorker")
    public ResponseEntity<List<DocumentWorker>> findAll() {
        List<DocumentWorker> list = documentWorkerService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/documentWorker/{id}")
    public ResponseEntity<DocumentWorker> findById(@PathVariable Integer id) {
        DocumentWorker documentWorker = documentWorkerService.findById(id);
        return new ResponseEntity<>(documentWorker, HttpStatus.OK);
    }

    @GetMapping("/documentWorker/pagination/{offset}/{limit}")
    public ResponseEntity<List<DocumentWorker>> findAll(@PathVariable Integer offset, @PathVariable Integer limit) {
        List<DocumentWorker> list = documentWorkerService.findAll(offset, limit);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
