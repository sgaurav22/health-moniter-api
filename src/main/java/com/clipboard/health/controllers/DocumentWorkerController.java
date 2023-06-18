package com.clipboard.health.controllers;

import com.clipboard.health.domains.Document;
import com.clipboard.health.domains.DocumentWorker;
import com.clipboard.health.domains.Worker;
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

    @GetMapping("/documentWorkers")
    public ResponseEntity<List<DocumentWorker>> findAll() {
        List<DocumentWorker> list = documentWorkerService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/documentWorkers/{id}")
    public ResponseEntity<DocumentWorker> findById(@PathVariable Integer id) {
        DocumentWorker documentWorker = documentWorkerService.findById(id);
        return new ResponseEntity<>(documentWorker, HttpStatus.OK);
    }

    @GetMapping("/documentWorkers/pagination/{offset}/{limit}")
    public ResponseEntity<List<DocumentWorker>> findAll(@PathVariable Integer offset, @PathVariable Integer limit) {
        List<DocumentWorker> list = documentWorkerService.findAll(offset, limit);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/documentWorkers")
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

    @PutMapping("/documentWorkers/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<DocumentWorker> update(@PathVariable Integer id, @RequestBody DocumentWorker documentWorker) {
        DocumentWorker existingDocumentWorker = null;
        try {
            existingDocumentWorker = documentWorkerService.findById(id);
            if (Objects.nonNull(existingDocumentWorker)) {
                Worker worker = documentWorker.getWorker();
                if (Objects.nonNull(worker)) {
                    Worker existingWorker = existingDocumentWorker.getWorker();
                    existingWorker.setName(worker.getName());
                    existingWorker.setProfession(worker.getProfession());
                    existingWorker.setIsActive(worker.getIsActive());
                    existingDocumentWorker.setWorker(existingWorker);
                }
                Document document = documentWorker.getDocument();
                if (Objects.nonNull(document)) {
                    Document existingDocument = existingDocumentWorker.getDocument();
                    existingDocument.setName(document.getName());
                    existingDocument.setIsActive(document.getIsActive());
                    existingDocumentWorker.setDocument(document);
                }
                documentWorkerService.save(existingDocumentWorker);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(existingDocumentWorker, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingDocumentWorker, HttpStatus.OK);
    }

    @DeleteMapping("/documentWorkers/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            documentWorkerService.delete(id);
            return new ResponseEntity<>("DocumentWorker deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
