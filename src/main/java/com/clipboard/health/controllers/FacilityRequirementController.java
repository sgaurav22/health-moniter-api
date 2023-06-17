package com.clipboard.health.controllers;

import com.clipboard.health.domains.FacilityRequirement;
import com.clipboard.health.services.FacilityRequirementService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    /*@Operation(tags = "GET Facility")*/
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<List<FacilityRequirement>> findAllDocument(@PathVariable int offset, @PathVariable int limit) {
        List<FacilityRequirement> facilities = facilityRequirementService.findAll(offset, limit);
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }
}