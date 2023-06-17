package com.clipboard.health.services;

import com.clipboard.health.common.ClipboardConstant;
import com.clipboard.health.domains.Worker;
import com.clipboard.health.exceptions.ClipboardException;
import com.clipboard.health.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Worker> findAll() throws ClipboardException {
        return findAll(ClipboardConstant.DEFAULT_OFFSET, ClipboardConstant.DEFAULT_PAGE_LIMIT);
    }

    @Override
    public Worker findById(int id) throws ClipboardException {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElseThrow();
    }

    @Override
    public List<Worker> findAll(int offset, int limit) throws ClipboardException {
        List<Worker> list = new ArrayList<>();
        Page<Worker> workers = workerRepository.findAll(PageRequest.of(offset, limit));
        workers.forEach(list::add);
        return list;
    }

    @Override
    public Iterable<Worker> saveAll(List<Worker> workers) throws ClipboardException {
        return workerRepository.saveAll(workers);
    }

    @Override
    public Worker save(Worker worker) throws ClipboardException {
        return workerRepository.save(worker);
    }

    @Override
    public void delete(int id) throws ClipboardException {
        workerRepository.deleteById(id);
    }
}
