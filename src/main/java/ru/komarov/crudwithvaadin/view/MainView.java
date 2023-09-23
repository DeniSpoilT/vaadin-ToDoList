package ru.komarov.crudwithvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ru.komarov.crudwithvaadin.dao.TaskRepository;
import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;

import java.util.Collections;
import java.util.List;

@Route
public class MainView extends VerticalLayout {
    private final TaskRepository taskRepository;

    private final TaskEditor taskEditor;

    final Grid<Task> grid;

    final ComboBox<Status> filter;

    private final Button addNewBtn;

    public MainView(TaskRepository taskRepository, TaskEditor taskEditor) {
        this.taskRepository = taskRepository;
        this.taskEditor = taskEditor;
        this.grid = new Grid<>(Task.class);
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
                taskEditor.editTask(new Task("", "")));

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
            grid.setItems(taskRepository.findAll());
        } else {
            List<Task> tasks = taskRepository.findAllByStatus(selectedStatus);
            grid.setItems(Collections.emptyList());
            grid.setItems(tasks);
        }
    }
}
