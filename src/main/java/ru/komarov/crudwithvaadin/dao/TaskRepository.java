package ru.komarov.crudwithvaadin.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    List<Task> findAllByStatus(Status status);

    List<Task> findAll();

    Optional<Task> findById(Long id);

    void delete(Task task);

    void save(Task task);
}
