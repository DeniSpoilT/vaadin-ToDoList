package ru.komarov.crudwithvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ru.komarov.crudwithvaadin.dto.TaskDTO;
import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;
import ru.komarov.crudwithvaadin.service.TaskService;

import java.util.Collections;
import java.util.List;

@Route
public class MainView extends VerticalLayout {
    TaskService taskService;

    private final TaskEditor taskEditor;

    final Grid<TaskDTO> grid;

    final ComboBox<Status> filter;

    private final Button addNewBtn;

    public MainView(TaskService taskService, TaskEditor taskEditor) {
        this.taskService = taskService;
        this.taskEditor = taskEditor;
        this.grid = new Grid<>(TaskDTO.class);
        this.filter = new ComboBox<>();
        this.addNewBtn = new Button("New task", VaadinIcon.PLUS.create());

        initialGrid();

        filter.addValueChangeListener(event -> {
            Status selectedStatus = event.getValue();
            updateTaskList(selectedStatus);
        });

        grid.asSingleSelect().addValueChangeListener(e -> {
            taskEditor.editTask(e.getValue());
        });

        addNewBtn.addClickListener(e ->
                taskEditor.editTask(new TaskDTO("", "")));

        grid.asSingleSelect().addValueChangeListener(e ->
                taskEditor.editTask(e.getValue()));

        taskEditor.setChangeHandler(() -> {
            taskEditor.setVisible(false);
            updateTaskList(null);
        });

        updateTaskList(null);
    }

    private void initialGrid() {
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, taskEditor);

        grid.setHeight("300px");
        grid.setColumns("id", "description", "status");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by status");
        filter.setItems(Status.values());
    }

    private void updateTaskList(Status selectedStatus) {
        if (selectedStatus == null) {
            grid.setItems((taskService.findAll())
                    .stream()
                    .map(Task::entityToDto)
                    .toList());
        } else {
            List<TaskDTO> tasks = taskService.findAllByStatus(selectedStatus)
                    .stream()
                    .map(Task::entityToDto)
                    .toList();
            grid.setItems(Collections.emptyList());
            grid.setItems(tasks);
        }
    }
}
