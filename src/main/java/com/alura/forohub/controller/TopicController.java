package com.alura.forohub.controller;

import com.alura.forohub.domain.topic.ITopicRepository;
import com.alura.forohub.domain.topic.NewTopicDTO;
import com.alura.forohub.domain.topic.TopicService;
import com.alura.forohub.domain.user.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")

public class TopicController {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity topicRegistration(@RequestBody @Valid NewTopicDTO data){
        var newTopic = topicService.createTopic(data);
        return ResponseEntity.ok(newTopic);
    }

    @GetMapping
    public  ResponseEntity listTopics(@PageableDefault(size = 10) Pageable paged){
        var topics = topicService.getAllTopics(paged);
       return ResponseEntity.ok(topics);
    }

}
