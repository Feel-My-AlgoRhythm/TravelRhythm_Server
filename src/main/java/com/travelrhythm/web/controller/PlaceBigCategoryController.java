package com.travelrhythm.web.controller;

import com.travelrhythm.web.dto.PlaceBigCategoryResponseDTO;
import com.travelrhythm.web.dto.PagingDTO;
import com.travelrhythm.web.service.PlaceBigCategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/place-big-categories")
public class PlaceBigCategoryController {

  @Autowired
  private PlaceBigCategoryService placeBigCategoryService;

  @GetMapping
  public ResponseEntity<Page<PlaceBigCategoryResponseDTO>> getPlaceBigCategories(
      @Valid PagingDTO dto) {
    return new ResponseEntity(placeBigCategoryService.getPlaceBigCategories(dto), HttpStatus.OK);
  }

}
