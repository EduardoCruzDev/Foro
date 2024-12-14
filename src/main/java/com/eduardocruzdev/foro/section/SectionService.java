package com.eduardocruzdev.foro.section;

import com.eduardocruzdev.foro.domain.model.Section;
import com.eduardocruzdev.foro.domain.utiles.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Page<Section> findSections(Pageable pageable) {
        return sectionRepository.findAll(pageable);
    }

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Section findOneOrExit(int id) {
        Optional<Section> sectionOptional = sectionRepository.findById((long) id);
        if (!sectionOptional.isPresent()) {
            throw new NotFoundException();
        }
        return sectionOptional.get();
    }

    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    public void delete(int id) {
        delete(findOneOrExit(id));
    }

    public void delete(Section section) {
        sectionRepository.delete(section);
    }
}
