package com.quick.controller;

import com.quick.model.dto.QuickDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuickSampleControllerTest {

    private final QuickSampleController controller;

    @Autowired
    public QuickSampleControllerTest(QuickSampleController controller) {
        this.controller = controller;
    }

    private QuickDataDto data;
    private QuickDataDto scriptInjectedData;
    private QuickDataDto sqlInjectedData;

    @BeforeEach
    void setUp() {
        data = new QuickDataDto("sample","quick");
        scriptInjectedData = new QuickDataDto("<script>alert('XSS Attack');</script>","quick");
        sqlInjectedData = new QuickDataDto("admin' OR '1'='1","quick");
    }

    @Test
    void createData() {
        ResponseEntity<QuickDataDto> response = controller.createData(data);
        assertEquals(response.getBody(), data);
    }

    @Test
    void updateData() {
        ResponseEntity<QuickDataDto> response = controller.updateData(data);
        assertEquals(response.getBody(), data);
    }

    @Test
    void getData() {
        ResponseEntity<QuickDataDto> response = controller.getData();
        assertEquals(response.getBody(), data);
    }

    @Test
    void deleteData() {
        ResponseEntity<String> response = controller.deleteData(data.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteData_SanitizeRequest() {
        ResponseEntity<String> response = controller.deleteData(sqlInjectedData.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String sanitizedResponse = sqlInjectedData.getName().replaceAll("[^a-zA-Z0-9\\s]", "");
        assertEquals("Successfully deleted : "+ sanitizedResponse, response.getBody());
    }

    @Test
    void deleteData_SanitizeScriptRequest() {
        ResponseEntity<String> response = controller.deleteData(scriptInjectedData.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String sanitizedResponse = scriptInjectedData.getName().replaceAll("[^a-zA-Z0-9\\s]", "");
        assertEquals("Successfully deleted : "+ sanitizedResponse, response.getBody());
    }
}