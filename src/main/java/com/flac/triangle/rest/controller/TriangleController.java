package com.flac.triangle.rest.controller;

import com.flac.triangle.rest.model.Triangle;
import com.flac.triangle.rest.model.TriangleType;
import com.flac.triangle.rest.service.TriangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class TriangleController {

    @Autowired
    private TriangleService triangleService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<TriangleType> getTriangleType(@Valid @RequestBody Triangle triangle) {
        TriangleType type = triangleService.getTriangleType(triangle);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }
}
