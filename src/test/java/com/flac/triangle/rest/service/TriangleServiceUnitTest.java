package com.flac.triangle.rest.service;

import com.flac.triangle.rest.model.Triangle;
import com.flac.triangle.rest.model.TriangleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TriangleServiceUnitTest {

    @Autowired
    private TriangleService triangleService;

    @Test
    public void shouldDetectEquilateralTriangle() {
        Triangle triangle = new Triangle();
        triangle.setA(12.3);
        triangle.setB(12.3);
        triangle.setC(12.3);

        assertThat(triangleService.getTriangleType(triangle)).isEqualTo(TriangleType.EQUILATERAL);
    }

    @Test
    public void shouldDetectIsoscelesAsEquilateralCandidateTriangle() {
        Triangle triangle = new Triangle();
        triangle.setA(12.3);
        triangle.setB(12.3);
        triangle.setC(15.6);

        assertThat(triangleService.getTriangleType(triangle)).isEqualTo(TriangleType.ISOSCELES);
    }

    @Test
    public void shouldDetectIsoscelesTriangle() {
        Triangle triangle = new Triangle();
        triangle.setA(12.3);
        triangle.setB(15.6);
        triangle.setC(15.6);

        assertThat(triangleService.getTriangleType(triangle)).isEqualTo(TriangleType.ISOSCELES);
    }

    @Test
    public void shouldDetectScaleneTriangle() {
        Triangle triangle = new Triangle();
        triangle.setA(12.3);
        triangle.setB(15.6);
        triangle.setC(18.9);

        assertThat(triangleService.getTriangleType(triangle)).isEqualTo(TriangleType.SCALENE);
    }
}
