package ru.komarov.crudwithvaadin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.komarov.crudwithvaadin.view.TaskEditor;

@Data
@Entity
@Table(name = "task", schema = "todo")
@NoArgsConstructor
@AllArgsConstructor
public class Task {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    public Task(String description, String status) {
        this.description = description;
        if (status.isEmpty()) {
            this.status = Status.IN_PROGRESS;
        } else {
            this.status = Status.valueOf(status);
        }
    }
}