package org.example.jpa.controller;

import org.example.jpa.models.File;
import org.example.jpa.models.Resource;
import org.example.jpa.models.Text;
import org.example.jpa.models.Video;
import org.example.jpa.repositories.ResourceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    private final ResourceRepository resourceRepository;
    ResourceController(ResourceRepository resourceRepository){
        this.resourceRepository = resourceRepository;
    }

    @PostMapping("/video")
    public String saveVideo(@RequestBody Video video)
    {
        resourceRepository.save(video);
        return "Video saved";
    }
    @PostMapping("/file")
    public String saveFile(@RequestBody File file)
    {
        resourceRepository.save(file);
        return "file saved";
    }
    @PostMapping("/text")
    public String saveText(@RequestBody Text text)
    {
        resourceRepository.save(text);
        return "text saved";
    }

    @GetMapping
    public List<Resource> getAllResources()
    {
        return resourceRepository.findAll();
    }

}
