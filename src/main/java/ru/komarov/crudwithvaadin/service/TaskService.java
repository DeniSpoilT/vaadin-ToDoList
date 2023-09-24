package ru.komarov.crudwithvaadin.service;

import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();

    List<Task> findAllByStatus(Status selectedStatus);

    List<Task> findPaginated(int pageNum, int pageSize);

    Optional<Task> findById(Long id);

    void delete(Task task);

    void save(Task task);
}
