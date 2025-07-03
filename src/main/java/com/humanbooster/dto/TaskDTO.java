package com.humanbooster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Task Data Transfer Object (DTO) for transferring task data.
 * This class is used to encapsulate task information in a structured format.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String status;
    private String assignee;

    private Long assigneeId;
    private Long projectId;

    private String projectName;
}
