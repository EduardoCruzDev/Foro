package com.eduardocruzdev.foro.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionForm {

    private Integer id;
    @Size(min = 5)
    private String name;
    private String description;
    private String imageFilename;

}