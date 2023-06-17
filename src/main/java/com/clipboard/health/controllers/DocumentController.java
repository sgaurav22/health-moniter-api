package com.clipboard.health.controllers;

import com.clipboard.health.domains.Document;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.services.DocumentService;
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
@Tag(name = "Document", description = "Document Management")
public class DocumentController {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);
    @Autowired
    DocumentService documentService;

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> findAllDocument() {
        List<Document> documents = (List<Document>) documentService.findAll();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/documents/{id}")
    public ResponseEntity<Document> findDocumentById(@PathVariable Integer id) {
        Document document = null;
        try {
            document = documentService.findById(id);
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(document, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/documents/pagination/{offset}/{limit}")
    public ResponseEntity<List<Document>> findAllDocument(@PathVariable int offset, @PathVariable int limit) {
        List<Document> documentPage = documentService.findAll(offset, limit);
        return new ResponseEntity<>(documentPage, HttpStatus.OK);
    }

    @PostMapping("/documents")
    public ResponseEntity<List<Document>> createDocument(@RequestBody List<Document> documents) {
        List<Document> list = new ArrayList<>();
        try {
            Iterable<Document> iterable = documentService.saveAll(documents);
            iterable.forEach(list::add);
        } catch (Exception e) {
            logger.error("Error occur while saving documents", e);
            throw new ClipboardException("Error occur while saving facilities");
        }
        return new ResponseEntity<>(documents, HttpStatus.CREATED);
    }

    @PutMapping("/documents/{id}")
    public ResponseEntity<Document> update(@PathVariable Integer id, @RequestBody Document document) {
        Document existingDocument = null;
        try{
            existingDocument = documentService.findById(id);
            if (Objects.nonNull(existingDocument)) {
                existingDocument.setName(document.getName());
                existingDocument.setIsActive(document.getIsActive());
                documentService.save(existingDocument);
            }
        } catch (Exception e) {
            logger.error("Error occurred ", e);
            return new ResponseEntity<>(existingDocument, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingDocument, HttpStatus.OK);
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            documentService.delete(id);
            return new ResponseEntity<>("Document deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
