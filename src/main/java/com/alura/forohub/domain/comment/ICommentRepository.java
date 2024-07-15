package com.alura.forohub.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    boolean existsByMessage(String message);

    Page<Comment> findByUserId(Long id, Pageable paged);

    boolean existsByUserId(Long id);

    Page<Comment> findByTopicId(Long id, Pageable paged);
}
