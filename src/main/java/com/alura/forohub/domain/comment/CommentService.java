package com.alura.forohub.domain.comment;

import com.alura.forohub.domain.topic.Topic;
import com.alura.forohub.domain.topic.TopicService;
import com.alura.forohub.domain.user.User;
import com.alura.forohub.domain.user.UserService;
import com.alura.forohub.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    public ResponseCommentDTO createComment(NewCommentDTO data) {
        validateCommentMessageNotDuplicated(data.message());
        var topic = validateTopicById(Long.valueOf(data.topicId()));
        var user = validateUserById(Long.valueOf(data.userId()));

        var newComment = new Comment(data, topic, user);
        commentRepository.save(newComment);
        return createResponse(newComment);
    }

    public Page<ResponseCommentDTO> getAllComments(Pageable paged) {
        Page<Comment> comments = commentRepository.findAll(paged);
        return comments.map(this::createResponse);
    }

    public ResponseCommentDTO getCommentById(Long id) {
        var searchedComment = validateCommentById(id);
        return createResponse(searchedComment);
    }

    public Page<ResponseCommentDTO> getCommentsByUser(Long id, Pageable paged) {
        var user = validateUserById(id);
        validateUserHasComments(id);
        Page<Comment> commentsByUser = commentRepository.findByUserId(user.getId(), paged);

        return commentsByUser.map(this::createResponse);
    }

    private void validateUserHasComments(Long id){
        if (!commentRepository.existsByUserId(id)){
            throw new ValidationException("No se encontraron comentarios del User indicado.");
        }
    }

    public Page<ResponseCommentDTO> getCommentsByTopic(Long id, Pageable paged){
        var topic =validateTopicById(id);
        Page<Comment> commentsByTopic = commentRepository.findByTopicId(topic.getId(), paged);
        return commentsByTopic.map(this::createResponse);
    }

    public ResponseCommentDTO updateCommentById(Long id, UpdateCommentDTO data) {
        var updatedComment = validateCommentById(id);
        updatedComment.setUpdatedAt(LocalDateTime.now());

        if (data.message() != null && !data.message().isEmpty()) {
            validateCommentMessageNotDuplicated(data.message());
            updatedComment.setMessage(data.message());
        }

        if (data.status() != null && !data.status().toString().isEmpty()) {
            updatedComment.deactivateComment(data.status());
        }

        commentRepository.save(updatedComment);
        return createResponse(updatedComment);
    }

    public Boolean deleteCommentById(Long id) {
        var deletedComment = validateCommentById(id);
        commentRepository.delete(deletedComment);
        return true;
    }

    private void validateCommentMessageNotDuplicated(String message) {
        if (commentRepository.existsByMessage(message)) {
            throw new IntegrityValidation("Ya existe un comentario con el mismo mensaje en la base de datos.");
        }
    }

    private Topic validateTopicById(Long id) {
        return topicService.validateTopicById(id);
    }

    private User validateUserById(Long id) {
        return userService.validateUserById(id);
    }

    private Comment validateCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new ValidationException("No se encontró un comentario con ese Id."));
    }

    private ResponseCommentDTO createResponse(Comment comment) {
        return new ResponseCommentDTO(
                comment.getId(),
                comment.getMessage(),
                comment.getTopic().getId(),
                comment.getTopic().getTitle(),
                comment.getUser().getName(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getStatus()
        );
    }


}
