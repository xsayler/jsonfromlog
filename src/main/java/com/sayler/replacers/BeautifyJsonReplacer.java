package com.sayler.replacers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author sayler
 */
public class BeautifyJsonReplacer implements Replacer {
    @Override
    public String execute(String text) throws ReplacerException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            JsonNode tree = objectMapper.readTree(text);
            return objectMapper.writeValueAsString(tree);
        } catch (JsonProcessingException e) {
            throw new ReplacerException("Error replacing", e);
        }
    }
}
