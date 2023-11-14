package com.sdev.opa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sdev.opa.domain.repository.DenyRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoDBTest {
    @Autowired
    private DenyRepository denyRepository;


    @Test
    public void print() throws JsonProcessingException {
        ObjectId objId = new ObjectId("6552141500f67f1556da7291");
        String jsonString = denyRepository.findAllById(objId).toString();
        //List<Deny> denyList = denyRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        JsonNode jsonNode = objectMapper.readTree(jsonString);

        String prettyJsonString = objectMapper.writeValueAsString(jsonNode);
        System.out.println(prettyJsonString);
    }
}
