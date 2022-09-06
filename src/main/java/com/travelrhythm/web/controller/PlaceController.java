package com.travelrhythm.web.controller;

import com.travelrhythm.web.dto.PlaceResponseDTO;
import com.travelrhythm.web.service.PlaceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

  @Autowired
  private PlaceService placeService;

  @GetMapping
  public ResponseEntity<List<PlaceResponseDTO>> getPlaces() {
    return new ResponseEntity(placeService.getPlaces(), HttpStatus.OK);
  }

}
