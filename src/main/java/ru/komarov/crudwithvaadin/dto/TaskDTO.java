package ru.komarov.crudwithvaadin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.komarov.crudwithvaadin.model.Status;
import ru.komarov.crudwithvaadin.model.Task;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;

    private String description;

    private Status status;

    public TaskDTO(String description, String status) {
        this.description = description;
        if (status.isEmpty()) {
            this.status = Status.IN_PROGRESS;
        } else {
            this.status = Status.valueOf(status);
        }
    }

    public Task dtoToEntity(){
        return Task.builder()
                .id(this.getId())
                .description(this.getDescription())
                .status(this.getStatus())
                .build();
    }
}
