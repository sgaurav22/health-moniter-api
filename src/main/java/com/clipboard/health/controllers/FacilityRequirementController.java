package com.clipboard.health.controllers;

import com.clipboard.health.domains.FacilityRequirement;
import com.clipboard.health.services.FacilityRequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
