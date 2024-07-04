package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleIgnoreCase(String title);

    boolean existsByMessageIgnoreCase(String message);

    boolean existsByCategory(Category category);
}
