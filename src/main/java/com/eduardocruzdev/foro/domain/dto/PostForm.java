package com.eduardocruzdev.foro.domain.dto;

import com.eduardocruzdev.foro.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostForm {
    private String content;
    private Post.ContentType contentType;
}
