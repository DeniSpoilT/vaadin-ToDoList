package ru.komarov.crudwithvaadin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.komarov.crudwithvaadin.dao.TaskRepository;
import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;
import ru.komarov.crudwithvaadin.service.TaskService;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllByStatus(Status selectedStatus) {
        return taskRepository.findAllByStatus(selectedStatus);
    }

    @Override
    public List<Task> findPaginated(int pageNum, int pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        Page<Task> pagedResult = taskRepository.findAll(paging);

        return pagedResult.toList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }
}
