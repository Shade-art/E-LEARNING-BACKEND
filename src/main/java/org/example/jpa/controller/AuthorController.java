package org.example.jpa.controller;


import org.example.jpa.models.Author;
import org.example.jpa.repositories.AuthorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    private final AuthorRepository repo;
    AuthorController(AuthorRepository repo)
    {
        this.repo = repo;
    }
    @RequestMapping("/author")
    @PostMapping
    public String Saved(@RequestBody Author author)
    {
        repo.save(author);
        return "SAVED";
    }
}
