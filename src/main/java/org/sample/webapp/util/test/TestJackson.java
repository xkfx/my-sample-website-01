package org.sample.webapp.util.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sample.webapp.entity.Profile;

public class TestJackson {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Profile profile = new Profile("admin", "123456", "admin123");
        String jsonString = mapper.writeValueAsString(profile);
        System.out.println(jsonString);
    }
}
