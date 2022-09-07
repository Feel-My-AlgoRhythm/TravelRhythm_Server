package com.travelrhythm.web.controller;

import com.travelrhythm.web.dto.PagingDTO;
import com.travelrhythm.web.dto.RegionResponseDTO;
import com.travelrhythm.web.service.RegionService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

  @Autowired
  private RegionService regionService;

  @GetMapping
  public ResponseEntity<Page<RegionResponseDTO>> getRegions(@Valid PagingDTO dto) {
    return new ResponseEntity(regionService.getRegions(dto), HttpStatus.OK);
  }

}
