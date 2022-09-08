package com.travelrhythm.web.controller;

import com.travelrhythm.web.dto.PlaceSmallCategoryRequestDTO;
import com.travelrhythm.web.dto.PlaceSmallCategoryResponseDTO;
import com.travelrhythm.web.service.PlaceSmallCategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/place-small-categories")
public class PlaceSmallCategoryController {

  @Autowired
  private PlaceSmallCategoryService placeSmallCategoryService;

  @GetMapping
  public ResponseEntity<Page<PlaceSmallCategoryResponseDTO>> getPlaceSmallCategories(
      @Valid PlaceSmallCategoryRequestDTO dto) {
    return new ResponseEntity(placeSmallCategoryService.getPlaceSmallCategories(dto),
        HttpStatus.OK);
  }

}
