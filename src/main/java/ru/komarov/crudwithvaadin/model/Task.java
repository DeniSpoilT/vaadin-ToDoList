package ru.komarov.crudwithvaadin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.komarov.crudwithvaadin.dto.TaskDTO;
import ru.komarov.crudwithvaadin.view.TaskEditor;

@Data
@Entity
@Table(name = "task", schema = "todo")
@Builder
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

    public  TaskDTO entityToDto(){
        return TaskDTO.builder()
                .id(this.getId())
                .description(this.getDescription())
                .status(this.getStatus())
                .build();
    }
}