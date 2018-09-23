package com.flac.triangle.rest.controller;

import com.flac.triangle.rest.model.Triangle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TriangleController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> getTriangleType(@Valid @RequestBody Triangle triangle) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
