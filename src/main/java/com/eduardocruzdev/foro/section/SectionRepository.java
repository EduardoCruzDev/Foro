package com.eduardocruzdev.foro.section;

import com.eduardocruzdev.foro.domain.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Long> {
    Section findByName(String name);
}
