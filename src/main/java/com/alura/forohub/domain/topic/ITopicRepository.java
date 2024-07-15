package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleIgnoreCase(String title);

    boolean existsByMessageIgnoreCase(String message);

    boolean existsByCategory(Category category);

    Page<Topic> findByUserId(Long id, Pageable paged);

    Page<Topic> findByCategory(Category category, Pageable paged);
}
