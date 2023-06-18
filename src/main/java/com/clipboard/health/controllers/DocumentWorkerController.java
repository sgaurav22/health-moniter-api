package com.clipboard.health.controllers;

import com.clipboard.health.domains.DocumentWorker;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.services.DocumentWorkerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @PostMapping("/documentWorker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<List<DocumentWorker>> create(@RequestBody List<DocumentWorker> documentWorkers) {
        List<DocumentWorker> list = new ArrayList<>();
        try {
            if (Objects.nonNull(documentWorkers)) {
                Iterable<DocumentWorker> iterable = documentWorkerService.saveAll(documentWorkers);
                iterable.forEach(list::add);
            }
        } catch (Exception e) {
            logger.error("Error occur while saving documentWorkers", e);
            throw new ClipboardException("Error occur while saving documentWorkers");
        }
        logger.info("DocumentWorker created successfully.", list);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }


}
