package org.example.controller;

import org.example.model.QuickData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuickSampleControllerTest {

    @Autowired
    private QuickSampleController controller;

    private QuickData data;

    @BeforeEach
    void setUp() {
        data = new QuickData("sample","quick");
    }

    @Test
    void createData() {
        ResponseEntity<QuickData> response = controller.createData(data);
        assertEquals(response.getBody(), data);
    }

    @Test
    void updateData() {
        ResponseEntity<QuickData> response = controller.updateData(data);
        assertEquals(response.getBody(), data);
    }

    @Test
    void getData() {
        ResponseEntity<QuickData> response = controller.getData();
        assertEquals(response.getBody(), data);
    }

    @Test
    void deleteData() {
        ResponseEntity<String> response = controller.deleteData(data.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}