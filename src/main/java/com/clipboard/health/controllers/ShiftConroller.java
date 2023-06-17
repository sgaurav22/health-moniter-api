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

    @PostMapping("/shifts")
    public ResponseEntity<List<Shift>> addShift(@RequestBody List<Shift> shifts) {
        List<Shift> list = new ArrayList<>();
        if (Objects.nonNull(shifts)) {
            Iterable<Shift> iterable = shiftService.saveAll(shifts);
            iterable.forEach(list::add);
        }

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PutMapping("/shifts")
    public ResponseEntity<Shift> update(@PathVariable Integer id, @RequestBody Shift shift) {
        Shift existingShift = null;
        try {
            existingShift = shiftService.findById(id);
            if (Objects.nonNull(existingShift)) {
                existingShift.setStart(shift.getStart());
                existingShift.setEnd(shift.getEnd());
                existingShift.setIsDeleted(shift.getIsDeleted());
                existingShift.setProfession(shift.getProfession());
                if (Objects.nonNull(shift.getFacility())) {
                    existingShift.setFacility(shift.getFacility());
                }
                if (Objects.nonNull(shift.getWorker())) {
                    existingShift.setWorker(shift.getWorker());
                }

                shiftService.save(existingShift);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(existingShift, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingShift, HttpStatus.OK);
    }

    @DeleteMapping("/shifts/{id}")
    public ResponseEntity<String> deleteShift(@PathVariable Integer id) {
        try {
            shiftService.delete(id);
            return new ResponseEntity<>("Shift deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
