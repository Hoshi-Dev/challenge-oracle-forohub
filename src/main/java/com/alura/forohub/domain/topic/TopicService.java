package com.alura.forohub.domain.topic;


import com.alura.forohub.domain.Category;
import com.alura.forohub.domain.user.UserService;
import com.alura.forohub.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TopicService {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private UserService userService;

    public ResponseTopicDTO createTopic(NewTopicDTO data) {

        var user = userService.validateUserByName(data.user());
        validateTitleNotDuplicated(data.title());
        validateMessageNotDuplicated(data.message());

        var newTopic = new Topic(data, user);
        topicRepository.save(newTopic);
        return createResponse(newTopic);
    }

    public Page<ResponseTopicDTO> getAllTopics(Pageable paged) {
        Page<Topic> topics = topicRepository.findAll(paged);
        return topics.map(this::createResponse);
    }

    public ResponseTopicDTO getTopicById(Long id) {
        var searchedTopic = validateTopicById(id);
        return createResponse(searchedTopic);
    }

    public Page<ResponseTopicDTO> getTopicByUser(Long id, Pageable paged) {
        var user = userService.validateUserById(id);
        Page<Topic> topics = topicRepository.findByUserId(user.getId(), paged);

        return topics.map(this::createResponse);
    }



    public Page<Object> getTopicsByCategory(String category, Pageable paged) {
        Category categoryEnum = Category.fromString(category);
        validateTopicExistByCategory(categoryEnum);

        Page<Topic> topicByCategory = topicRepository.findByCategory(categoryEnum, paged);
        return topicByCategory.map(this::createResponse);
    }


    public ResponseTopicDTO updateTopicById(Long id, UpdateTopicDTO data) {
        var updatedTopic = validateTopicById(id);
        updatedTopic.setUpdatedAt(LocalDateTime.now());

        if (data.title() != null && !data.title().isEmpty()) {
            validateTitleNotDuplicated(data.title());
            updatedTopic.setTitle(data.title());
        }
        if (data.message() != null && !data.message().isEmpty()) {
            validateMessageNotDuplicated(data.message());
            updatedTopic.setMessage(data.message());
        }
        if (data.category() != null && !data.category().toString().isEmpty()) {
            updatedTopic.setCategory(data.category());
        }
        if (data.status() != null && !data.status().toString().isEmpty()) {
            updatedTopic.setStatus(data.status());
        }
        topicRepository.save(updatedTopic);

        return createResponse(updatedTopic);
    }

    public boolean deleteTopicById(Long id) {
        var deleteTopic = validateTopicById(id);
        topicRepository.delete(deleteTopic);
        return true;
    }


    private void validateTitleNotDuplicated(String title) {
        if (topicRepository.existsByTitleIgnoreCase(title)) {
            throw new IntegrityValidation("El título ya se encuentra registrado en la base de datos. " +
                    "Por favor, verifique el topic existente.");
        }
    }

    private void validateMessageNotDuplicated(String message) {
        if (topicRepository.existsByMessageIgnoreCase(message)) {
            throw new ValidationException("El contenido del topic ya se encuentra en la base de datos. " +
                    "Por favor, verifique el topic existente.");
        }
    }

    public Topic validateTopicById(Long id) {
        return topicRepository.findById(id).orElseThrow(() ->
                new ValidationException("El Topic especificado no se encuentra en la base de datos."));
    }

    private void validateTopicExistByCategory(Category category) {
        if (!topicRepository.existsByCategory(category)) {
            throw new ValidationException("No se ha encontrado topics en esa categoría");
        }
    }

    private ResponseTopicDTO createResponse(Topic topic) {
        return new ResponseTopicDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreatedAt(),
                topic.getUpdatedAt(),
                topic.getCategory().toString(),
                topic.getUser().getName(),
                topic.getStatus()
        );
    }


}
