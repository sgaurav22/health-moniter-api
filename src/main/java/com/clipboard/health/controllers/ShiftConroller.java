package com.clipboard.health.controllers;

import com.clipboard.health.domains.Shift;
import com.clipboard.health.services.ShiftService;
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
@Tag(name = "Shift", description = "Shift Management")
public class ShiftConroller {

    Logger logger = LoggerFactory.getLogger(ShiftConroller.class);

    @Autowired
    private ShiftService shiftService;

    @GetMapping("/shifts")
    public ResponseEntity<List<Shift>> findAll() {
        return new ResponseEntity<>(shiftService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/shifts/{id}")
    public ResponseEntity<Shift> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(shiftService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/shifts/pagination/{offset}/{limit}")
    public ResponseEntity<List<Shift>> findAllShifts(@PathVariable int offset, @PathVariable int limit) {
        List<Shift> shifts = shiftService.findAll(offset, limit);
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Shift>> addShift(@RequestBody List<Shift> shifts) {
        List<Shift> list = new ArrayList<>();
        if (Objects.nonNull(shifts)) {
            Iterable<Shift> iterable = shiftService.saveAll(shifts);
            iterable.forEach(list::add);
        }

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

}
