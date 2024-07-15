package com.alura.forohub.controller;

import com.alura.forohub.domain.comment.CommentService;
import com.alura.forohub.domain.comment.NewCommentDTO;
import com.alura.forohub.domain.comment.UpdateCommentDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> newComment(@RequestBody @Valid NewCommentDTO data) {
        var newComment = commentService.createComment(data);
        return ResponseEntity.ok(newComment);
    }

    @GetMapping
    public ResponseEntity<?> listAllComments(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged) {
        var comments = commentService.getAllComments(paged);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable(value = "id") Long id){
        var comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getCommentsByUser(@PathVariable(value = "id") Long id,
                                               @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged){
        var commentsbyUser = commentService.getCommentsByUser(id, paged);
        return ResponseEntity.ok(commentsbyUser);
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<?> getCommentsByTopic(@PathVariable(value = "id") Long id,
                                                @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged){
        var commentsByTopic = commentService.getCommentsByTopic(id, paged);
        return ResponseEntity.ok(commentsByTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommentById(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateCommentDTO data){
        var commentUpdated = commentService.updateCommentById(id,data);
                return ResponseEntity.ok(commentUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable(value = "id") Long id){
        if (commentService.deleteCommentById(id)){
            return ResponseEntity.ok("Comentario eliminado correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
