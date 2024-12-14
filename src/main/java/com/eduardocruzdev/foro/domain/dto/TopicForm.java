package com.eduardocruzdev.foro.domain.dto;

import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TopicForm {

    @NotEmpty
    private String title;
    @NotEmpty
    private Integer sectionId;
    @NotEmpty
    private String content;
}
