package ru.komarov.crudwithvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import ru.komarov.crudwithvaadin.dao.TaskRepository;
import ru.komarov.crudwithvaadin.dto.TaskDTO;
import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;
import ru.komarov.crudwithvaadin.service.TaskService;

@SpringComponent
@UIScope
public class TaskEditor extends VerticalLayout implements KeyNotifier {

    private final TaskService taskService;

    private ChangeHandler changeHandler;
    private TaskDTO task;

    TextField description = new TextField("Description");
    ComboBox<Status> status = new ComboBox<>("Status");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<TaskDTO> binder = new Binder<>(TaskDTO.class);


    @Autowired
    public TaskEditor(TaskService taskService) {
        this.taskService = taskService;

        status.setItems(Status.values());

        binder.bindInstanceFields(this);
        binder.forField(status).bind(TaskDTO::getStatus, TaskDTO::setStatus);

        add(description, status, actions);

        setSpacing(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editTask(task));
        setVisible(false);
    }

    void delete() {
        taskService.delete(task.dtoToEntity());
        changeHandler.onChange();
    }

    void save() {
        taskService.save(task.dtoToEntity());
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editTask(TaskDTO t) {
        if (t == null) {
            setVisible(false);
            return;
        }

        final boolean persisted = t.getId() != null;

        if (persisted) {
            task = taskService.findById(t.getId())
                    .get()
                    .entityToDto();
        } else {
            task = t;
        }

        cancel.setVisible(persisted);

        binder.setBean(task);

        setVisible(true);

        description.focus();
    }
    public void setChangeHandler(ChangeHandler handler) {
        changeHandler = handler;
    }
}
