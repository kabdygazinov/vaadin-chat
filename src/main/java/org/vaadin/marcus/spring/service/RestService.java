package org.vaadin.marcus.spring.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.marcus.spring.model.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {
    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Message saveMessage(Message message) {
        String url = "http://localhost:8080/api/save";

        return this.restTemplate.postForObject(url, message, Message.class);
    }



    public List<Message> getUnreadMessages() {
        List<Message> list = new ArrayList<>();
        String url = "http://localhost:8080/api/unread";
        return (List<Message>) restTemplate.getForEntity(url, Message[].class);
    }

    public List<Message> getLast() {
        String url = "http://localhost:8080/api/last";

        String json = restTemplate.getForObject(url, String.class);
        return new Gson().fromJson(json, new TypeToken<List<Message>>(){}.getType());
    }
}
