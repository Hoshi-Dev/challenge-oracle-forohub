package com.alura.forohub.controller;

import com.alura.forohub.domain.topic.NewTopicDTO;
import com.alura.forohub.domain.topic.TopicService;
import com.alura.forohub.domain.topic.UpdateTopicDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<?> topicRegistration(@RequestBody @Valid NewTopicDTO data){
        var newTopic = topicService.createTopic(data);
        return ResponseEntity.ok(newTopic);
    }

    @GetMapping
    public  ResponseEntity<?> listTopics(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged){
        var topics = topicService.getAllTopics(paged);
       return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable(value = "id") Long id){
        var topic = topicService.getTopicById(id);
        return ResponseEntity.ok(topic);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getTopicsByUser(@PathVariable(value = "id") Long id,
                                             @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged){
        var topics = topicService.getTopicByUser(id, paged);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getTopicByCategory(@PathVariable(value = "category") String category,
                                                @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable paged){
        var topics = topicService.getTopicsByCategory(category, paged);
        return ResponseEntity.ok(topics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopicById(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateTopicDTO data){
        var topicUpdated = topicService.updateTopicById(id,data);
        return ResponseEntity.ok(topicUpdated);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteTopicById(@PathVariable(value = "id") Long id){
        if (topicService.deleteTopicById(id)){
            return ResponseEntity.ok("Topic eliminado correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
