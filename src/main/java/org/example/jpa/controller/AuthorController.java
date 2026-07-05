package org.example.jpa.controller;


import org.example.jpa.models.Author;
import org.example.jpa.repositories.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorRepository repo;
    AuthorController(AuthorRepository repo)
    {
        this.repo = repo;
    }

    @PostMapping
    public String Saved(@RequestBody Author author)
    {
        repo.save(author);
        return "SAVED";
    }
    @GetMapping
    public List<Author> getallAuthor()
    {
        return repo.findAll();
    }
}
