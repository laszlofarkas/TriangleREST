package com.flac.triangle.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.flac.triangle.rest.model.Triangle;
import com.flac.triangle.rest.model.TriangleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TriangleControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldDetectEquilateralTriangle() {
        // all sides are equal
        Triangle triangle = this.buildTriangle(12.3, 12.3, 12.3);
        ResponseEntity<TriangleType> response = restTemplate.postForEntity(baseUrl(), triangle, TriangleType.class);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TriangleType.EQUILATERAL);
    }

    @Test
    public void shouldDetectIsoscelesTriangle() {
        // two of the sides are equal
        Triangle triangle = this.buildTriangle(12.3, 12.3, 15.6);
        ResponseEntity<TriangleType> response = restTemplate.postForEntity(baseUrl(), triangle, TriangleType.class);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TriangleType.ISOSCELES);
    }

    @Test
    public void shouldDetectScaleneTriangle() {
        // all side are different
        Triangle triangle = this.buildTriangle(12.3, 17.8, 15.6);
        ResponseEntity<TriangleType> response = restTemplate.postForEntity(baseUrl(), triangle, TriangleType.class);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TriangleType.SCALENE);
    }

    @Test
    public void shouldCheckTriangleSideConstraint() throws Exception {
        // All sides should be less then the sum of other two sides
        Triangle triangle = this.buildTriangle(12.3, 17.8, 156);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl(), triangle, String.class);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // There should be one TriangleSideConstraint error
        JsonNode errors = mapper.readTree(response.getBody()).get("errors");
        assertThat(errors.get(0).get("code").asText()).isEqualToIgnoringCase("TriangleSideConstraint");
        assertThat(errors.get(0).get("defaultMessage").asText()).containsIgnoringCase("All sides of the triangle should be less then the sum of other two sides");
    }

    @Test
    public void shouldCheckTriangleSideLength() throws Exception {
        // sides should be greater than 0
        Triangle triangle = this.buildTriangle(-12.3, 0, 36);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl(), triangle, String.class);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // There are three error: 2 invalid side length and side constraint error
        ArrayNode errors = (ArrayNode) mapper.readTree(response.getBody()).get("errors");
        assertThat(errors.size()).isEqualTo(3);
        for (JsonNode element : errors) {
            if ("Positive".equalsIgnoreCase(element.get("code").asText())) {
                assertThat(element.get("defaultMessage").asText()).containsIgnoringCase("must be greater than 0");
            }
        }
    }

    /**
     * Get the base url of the application with the dynamic port
     *
     * @return the application url
     */
    private String baseUrl() {
        return "http://localhost:" + port + "/";
    }

    /**
     * Build a triangle
     *
     * @param a 1st side of the triangle
     * @param b 2nd side of the triangle
     * @param c 3rd side of the triangle
     * @return a triangle with the given sides
     */
    private Triangle buildTriangle(double a, double b, double c) {
        Triangle result = new Triangle();
        result.setA(a);
        result.setB(b);
        result.setC(c);
        return result;
    }
}
