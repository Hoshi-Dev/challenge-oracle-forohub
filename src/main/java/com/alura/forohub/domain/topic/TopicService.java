package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.user.IUserRepository;
import com.alura.forohub.domain.user.User;
import com.alura.forohub.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private IUserRepository userRepository;

    public ResponseTopicDTO createTopic(NewTopicDTO data) {
        //valida si el usuario está registrado
        if (!userRepository.existsByName(data.user())) {
            throw new IntegrityValidation("El usuario no se encuentra registrado.");
        }

        //valida si el título no está duplicado
        var title = data.title();
        if (title != null && topicRepository.existsByTitleIgnoreCase(title)) {
            throw new IntegrityValidation("El título ya se encuentra registrado en la base de datos. " +
                    "Por favor, verifique la consulta existente.");
        }

        //valida si el mensaje no está duplicado
        var message = data.message();
        if (message != null && topicRepository.existsByMessageIgnoreCase(message)) {
            throw new ValidationException("El contenido del topic ya se encuentra en la base de datos. " +
                    "Por favor, verifique la consulta existente.");
        }

        User user = (User) userRepository.findByName(data.user());
        var newTopic = new Topic(data, user);
        topicRepository.save(newTopic);

        return new ResponseTopicDTO(
                newTopic.getId(),
                newTopic.getTitle(),
                newTopic.getMessage(),
                newTopic.getCreatedAt(),
                newTopic.getUpdatedAt(),
                newTopic.getCategory().toString(),
                newTopic.getUser().getName(),
                newTopic.getStatus()
        );
    }

    public Page<ResponseTopicDTO> getAllTopics(Pageable paged){
        Page<Topic> topics = topicRepository.findAll(paged);

        return  topics.map(t -> new ResponseTopicDTO(
                t.getId(),
                t.getTitle(),
                t.getMessage(),
                t.getCreatedAt(),
                t.getUpdatedAt(),
                t.getCategory().toString(),
                t.getUser().getName(),
                t.getStatus()
        ));
    }
}
