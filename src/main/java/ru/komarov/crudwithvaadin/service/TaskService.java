package ru.komarov.crudwithvaadin.service;

import org.springframework.data.domain.PageRequest;
import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();

    List<Task> findAllByStatus(Status selectedStatus);

    List<Task> findPaginated(PageRequest springPageRequest);

    Optional<Task> findById(Long id);

    void delete(Task task);

    void save(Task task);

}
