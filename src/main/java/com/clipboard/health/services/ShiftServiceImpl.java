package com.clipboard.health.services;

import com.clipboard.health.common.ClipboardConstant;
import com.clipboard.health.domains.Shift;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Override
    public List<Shift> findAll() throws ClipboardException {
        return findAll(ClipboardConstant.DEFAULT_OFFSET, ClipboardConstant.DEFAULT_PAGE_LIMIT);
    }

    @Override
    public Shift findById(int id) throws ClipboardException {
        Optional<Shift> optionalShift = shiftRepository.findById(id);
        return optionalShift.orElseThrow();
    }

    @Override
    public List<Shift> findAll(int offset, int limit) throws ClipboardException {
        List<Shift> shifts = new ArrayList<>();
        Page<Shift> shiftPage = shiftRepository.findAll(PageRequest.of(offset, limit));
        shiftPage.forEach(shifts::add);
        return shifts;
    }

    @Override
    public Iterable<Shift> saveAll(List<Shift> shifts) throws ClipboardException {
        return shiftRepository.saveAll(shifts);
    }

    @Override
    public Shift save(Shift shift) throws ClipboardException {
        return shiftRepository.save(shift);
    }

    @Override
    public void delete(int id) throws ClipboardException {
        shiftRepository.deleteById(id);
    }
}
