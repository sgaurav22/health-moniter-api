package com.clipboard.health.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "FacilityRequirement", description = "FacilityRequirement Management")
public class FacilityRequirementController {

    Logger logger = LoggerFactory.getLogger(FacilityRequirementController.class);


}
