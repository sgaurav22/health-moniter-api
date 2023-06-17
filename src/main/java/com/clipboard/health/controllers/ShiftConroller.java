package com.clipboard.health.controllers;

import com.clipboard.health.domains.Shift;
import com.clipboard.health.services.ShiftService;
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
@Tag(name = "Shift", description = "Shift Management")
public class ShiftConroller {

    Logger logger = LoggerFactory.getLogger(ShiftConroller.class);

    @Autowired
    private ShiftService shiftService;

    @GetMapping("/shifts")
    public ResponseEntity<List<Shift>> findAll() {
        return new ResponseEntity<>(shiftService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/shifts/pagination/{offset}/{limit}")
    public ResponseEntity<List<Shift>> findAllShifts(@PathVariable int offset, @PathVariable int limit) {
        List<Shift> shifts = shiftService.findAll(offset, limit);
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

}
