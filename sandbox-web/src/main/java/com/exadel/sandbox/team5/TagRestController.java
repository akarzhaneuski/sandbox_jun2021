package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagRestController {

    private final TagService tagService;

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable Long id){
        return tagService.getById(id);
    }

    @GetMapping("/")
    public List<Tag> getAll(){
        return tagService.getAll();
    }
}
