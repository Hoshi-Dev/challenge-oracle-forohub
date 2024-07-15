package com.alura.forohub.controller;

import com.alura.forohub.domain.comment.CommentService;
import com.alura.forohub.domain.comment.NewCommentDTO;
import com.alura.forohub.domain.comment.UpdateCommentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@SecurityRequirement(name = "bearer-key")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "Registra un nuevo Comentario.")
    public ResponseEntity<?> newComment(@RequestBody @Valid NewCommentDTO data) {
        var newComment = commentService.createComment(data);
        return ResponseEntity.ok(newComment);
    }

    @GetMapping
    @Operation(summary = "Trae una lista de todos los Comentarios.")
    public ResponseEntity<?> listAllComments(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged) {
        var comments = commentService.getAllComments(paged);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae un Comentario según su ID.")
    public ResponseEntity<?> getCommentById(@PathVariable(value = "id") Long id){
        var comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Trae todos los Comentarios de un Usuario según su ID.")
    public ResponseEntity<?> getCommentsByUser(@PathVariable(value = "id") Long id,
                                               @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged){
        var commentsbyUser = commentService.getCommentsByUser(id, paged);
        return ResponseEntity.ok(commentsbyUser);
    }

    @GetMapping("/topic/{id}")
    @Operation(summary = "Trae todos los Comentarios de un Topic según su ID.")
    public ResponseEntity<?> getCommentsByTopic(@PathVariable(value = "id") Long id,
                                                @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged){
        var commentsByTopic = commentService.getCommentsByTopic(id, paged);
        return ResponseEntity.ok(commentsByTopic);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un Comentario según su ID.")
    public ResponseEntity<?> updateCommentById(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateCommentDTO data){
        var commentUpdated = commentService.updateCommentById(id,data);
                return ResponseEntity.ok(commentUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un Comentario según su ID.")
    public ResponseEntity<?> deleteCommentById(@PathVariable(value = "id") Long id){
        if (commentService.deleteCommentById(id)){
            return ResponseEntity.ok("Comentario eliminado correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
