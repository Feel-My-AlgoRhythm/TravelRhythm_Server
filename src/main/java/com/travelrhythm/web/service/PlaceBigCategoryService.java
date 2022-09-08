package com.travelrhythm.web.service;

import com.travelrhythm.domain.repository.category.PlaceBigCategoryRepository;
import com.travelrhythm.web.dto.PlaceBigCategoryResponseDTO;
import com.travelrhythm.web.dto.PagingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PlaceBigCategoryService {

  @Autowired
  private PlaceBigCategoryRepository placeBigCategoryRepository;

  public Page<PlaceBigCategoryResponseDTO> getPlaceBigCategories(PagingDTO dto) {
    return placeBigCategoryRepository.findAllPlaceBigCategories(dto.getPageRequest());
  }

}
