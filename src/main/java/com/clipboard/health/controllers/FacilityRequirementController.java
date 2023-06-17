package com.clipboard.health.controllers;

import com.clipboard.health.domains.Document;
import com.clipboard.health.domains.Facility;
import com.clipboard.health.domains.FacilityRequirement;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.services.FacilityRequirementService;
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
@Tag(name = "FacilityRequirement", description = "FacilityRequirement Management")
public class FacilityRequirementController {

    Logger logger = LoggerFactory.getLogger(FacilityRequirementController.class);

    @Autowired
    private FacilityRequirementService facilityRequirementService;

    @GetMapping("/facilityRequirement")
    public ResponseEntity<List<FacilityRequirement>> findAll() {
        List<FacilityRequirement> facilityRequirements = facilityRequirementService.findAll();
        return new ResponseEntity<>(facilityRequirements, HttpStatus.OK);
    }

    @GetMapping("/facilityRequirement/{id}")
    public ResponseEntity<FacilityRequirement> findById(@PathVariable Integer id) {
        FacilityRequirement facilityRequirements = facilityRequirementService.findById(id);
        return new ResponseEntity<>(facilityRequirements, HttpStatus.OK);
    }

    @GetMapping("/facilityRequirement/pagination/{offset}/{limit}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<List<FacilityRequirement>> findAllDocument(@PathVariable int offset, @PathVariable int limit) {
        List<FacilityRequirement> facilities = facilityRequirementService.findAll(offset, limit);
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

    @PostMapping("/facilityRequirement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<List<FacilityRequirement>> create(@RequestBody List<FacilityRequirement> facilityRequirementList) {
        List<FacilityRequirement> list = new ArrayList<>();
        try {
            if (Objects.nonNull(facilityRequirementList)) {
                Iterable<FacilityRequirement> iterable = facilityRequirementService.saveAll(facilityRequirementList);
                iterable.forEach(list::add);
            }
        } catch (Exception e) {
            logger.error("Error occur while saving facilities", e);
            throw new ClipboardException("Error occur while saving facilities");
        }
        logger.info("Facility created successfully.", list);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PutMapping("/facilityRequirement/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<FacilityRequirement> update(@PathVariable Integer id, @RequestBody FacilityRequirement facilityRequirement) {
        FacilityRequirement existingFacilityRequiremment = null;
        try {
            existingFacilityRequiremment = facilityRequirementService.findById(id);
            if (Objects.nonNull(existingFacilityRequiremment)) {
                Facility existingFacility = existingFacilityRequiremment.getFacility();
                Facility newFacility = facilityRequirement.getFacility();
                existingFacility.setName(newFacility.getName());
                existingFacility.setIsActive(newFacility.getIsActive());
                existingFacilityRequiremment.setFacility(existingFacility);

                Document existingDocument = existingFacilityRequiremment.getDocument();
                Document newDocument = facilityRequirement.getDocument();
                existingDocument.setName(newDocument.getName());
                existingDocument.setIsActive(newDocument.getIsActive());
                existingFacilityRequiremment.setDocument(existingDocument);

                facilityRequirementService.save(existingFacilityRequiremment);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(existingFacilityRequiremment, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingFacilityRequiremment, HttpStatus.OK);
    }

    @DeleteMapping("/facilityRequirement/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            facilityRequirementService.delete(id);
            return new ResponseEntity<>("Facility deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
