package com.eduardocruzdev.foro.section;


import com.eduardocruzdev.foro.domain.model.Section;
import com.eduardocruzdev.foro.topic.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sections/")
public class SectionController {

    private final SectionService sectionService;
    private final TopicService topicService;

    public SectionController(SectionService sectionService, TopicService topicService) {
        this.sectionService = sectionService;
        this.topicService = topicService;
    }

    @RequestMapping("{id}")
    public String getSection(@PathVariable int id,
                             Model model) {
        Section section = sectionService.findOneOrExit(id);
        model.addAttribute("section", section);
        model.addAttribute("topics", topicService.findBySection(section));
        return "sections/section";
    }

}