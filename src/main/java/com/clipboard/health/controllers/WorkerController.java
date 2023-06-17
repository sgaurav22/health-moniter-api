package com.clipboard.health.controllers;

import com.clipboard.health.domains.Worker;
import com.clipboard.health.services.WorkerService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Worker", description = "Worker Management")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> workers = workerService.findAll();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Integer id) {
        Worker worker = workerService.findById(id);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @GetMapping("/workers/pagination/{offset}/{limit}")
    public ResponseEntity<List<Worker>> findAllWorkers(@PathVariable int offset, @PathVariable int limit) {
        List<Worker> workers = workerService.findAll(offset, limit);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

}
