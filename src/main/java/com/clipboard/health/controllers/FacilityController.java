package com.clipboard.health.controllers;

import com.clipboard.health.domains.Facility;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.services.FacilityService;
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
@Tag(name = "Facility", description = "Facility Management")
public class FacilityController {

    Logger logger = LoggerFactory.getLogger(FacilityController.class);

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/facilities")
    /*@Operation(tags = "GET Facility")*/
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<List<Facility>> findAllDocument() {
        List<Facility> facilities = (List<Facility>) facilityService.findAll();
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

    @GetMapping("/facilities/{id}")
    /*@Operation(tags = "GET Facility")*/
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<Facility> findAllDocument(@PathVariable Integer id) {
        Facility facility = facilityService.findById(id);
        return new ResponseEntity<>(facility, HttpStatus.OK);
    }

    @GetMapping("/facilities/pagination/{offset}/{limit}")
    /*@Operation(tags = "GET Facility")*/
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<List<Facility>> findAllDocument(@PathVariable int offset, @PathVariable int limit) {
        List<Facility> facilities = facilityService.findAll(offset, limit);
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

    @PostMapping("/facilities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<List<Facility>> create(@RequestBody List<Facility> facilityList) {
        List<Facility> list = new ArrayList<>();
        try {
            if (Objects.nonNull(facilityList)) {
                Iterable<Facility> iterable = facilityService.saveAll(facilityList);
                iterable.forEach(list::add);
            }
        } catch (Exception e) {
            logger.error("Error occur while saving facilities", e);
            throw new ClipboardException("Error occur while saving facilities");
        }
        logger.info("Facility created successfully.", list);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PutMapping("/facilities/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<Facility> update(@PathVariable Integer id, @RequestBody Facility facility) {
        Facility existingFacility = null;
        try {
            existingFacility = facilityService.findById(id);
            if (Objects.nonNull(existingFacility)) {
                existingFacility.setName(facility.getName());
                existingFacility.setIsActive(facility.getIsActive());
                facilityService.save(existingFacility);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(existingFacility, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingFacility, HttpStatus.OK);
    }

    @DeleteMapping("/facilities/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<String> deleteFacility(@PathVariable int id) {
        try {
            facilityService.delete(id);
            return new ResponseEntity<>("Facility deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
