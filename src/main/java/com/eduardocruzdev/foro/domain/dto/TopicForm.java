package com.eduardocruzdev.foro.domain.dto;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Integer sectionId;
    @NotEmpty
    private String content;
}
