package com.office.employees_storage_spring_framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.office.employees_storage_spring_framework.dto.InputEmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FetchApiResponse {

    private static String responseBody;


    private static void saveBodyResponse() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://randomuser.me/api/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        responseBody = response.getBody();
    }


    public static InputEmployeeDTO fetchBody() {
        saveBodyResponse();
        InputEmployeeDTO generatedEmployee;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(getAPIResponse());
            JsonNode resultNode = rootNode.get("results").get(0);
            JsonNode nameNode = resultNode.get("name");
            JsonNode locationNode = resultNode.get("location");
            JsonNode emailNode = resultNode.get("email");
            JsonNode loginNode = resultNode.get("login");
            JsonNode dobNode = resultNode.get("dob");
            JsonNode phoneNode = resultNode.get("phone");
            JsonNode pictureNode = resultNode.get("picture");

            StringBuilder fullAddress = new StringBuilder();
            fullAddress.append(locationNode.get("street").get("number").asText());
            fullAddress.append(" ");
            fullAddress.append(locationNode.get("street").get("name").asText());

            generatedEmployee = InputEmployeeDTO.builder()
                    .title(nameNode.get("title").asText())
                    .firstName(nameNode.get("first").asText())
                    .secondName(nameNode.get("last").asText())
                    .email(emailNode.asText())
                    .age(dobNode.get("age").asText())
                    .country(locationNode.get("country").asText())
                    .town(locationNode.get("city").asText())
                    .address(fullAddress.toString())
                    .phoneNumber(convertPhoneNumberToEU(phoneNode.asText()))
                    .username(loginNode.get("username").asText())
                    .password(loginNode.get("password").asText())
                    .picture(pictureNode.get("large").asText())
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return generatedEmployee;
    }

    private static String convertPhoneNumberToEU(String phoneNumber) {
        phoneNumber.replaceAll("\\s", "");
        phoneNumber.replace("-", "");
        return phoneNumber.substring(1, 4) + phoneNumber.substring(4);
    }

    private static String getAPIResponse() {
        return responseBody;
    }

}
