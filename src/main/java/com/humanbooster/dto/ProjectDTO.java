package com.humanbooster.dto;

import com.humanbooster.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String creator;

    private Long creatorId;

    private List<Task> tasks = new ArrayList<>();
}
