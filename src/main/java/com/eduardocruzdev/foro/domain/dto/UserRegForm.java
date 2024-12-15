package com.eduardocruzdev.foro.domain.dto;

import com.eduardocruzdev.foro.domain.utiles.UniqueEmailValidator;
import com.eduardocruzdev.foro.domain.utiles.UniqueUsernameValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegForm {

    @Email(message = "{Email.Invalid}")
    @NotNull(message = "{Email.Empty}")
    @UniqueEmailValidator
    private String email;

    @NotNull(message = "{Field.Required}")
    @Size(min = 8, max = 100, message = "{Password.InvalidSize}")
    private String password;

    @NotNull(message = "{Field.Required}")
    @Size(min = 4, max = 60, message = "{Username.InvalidSize}")
    @UniqueUsernameValidator
    private String username;


}
