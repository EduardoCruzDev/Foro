package com.eduardocruzdev.foro.domain.model;


import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "user_preferences")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPreferences {

    @Id
    private Long id;

}
