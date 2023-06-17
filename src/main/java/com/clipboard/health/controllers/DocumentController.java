package com.clipboard.health.controllers;

import com.clipboard.health.domains.Document;
import com.clipboard.health.services.DocumentService;
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
@Tag(name = "Document", description = "Document Management")
public class DocumentController {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);
    @Autowired
    DocumentService documentService;

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> findAllDocument(){
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
    public ResponseEntity<List<Document>> findAllDocument(@PathVariable int offset, @PathVariable int limit){
        List<Document> documentPage = documentService.findAll(offset, limit);
        return new ResponseEntity<>(documentPage, HttpStatus.OK);
    }
}
