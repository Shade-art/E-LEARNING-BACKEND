package org.example.jpa.repositories;

import org.example.jpa.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository
        extends JpaRepository<Resource, Long> {
}