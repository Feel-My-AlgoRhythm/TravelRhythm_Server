package com.travelrhythm.web.service;

import com.travelrhythm.domain.repository.category.PlaceSmallCategoryRepository;
import com.travelrhythm.domain.repository.category.param.PlaceSmallCategorySearchParam;
import com.travelrhythm.web.dto.PlaceSmallCategoryRequestDTO;
import com.travelrhythm.web.dto.PlaceSmallCategoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PlaceSmallCategoryService {

  @Autowired
  private PlaceSmallCategoryRepository placeSmallCategoryRepository;

  public Page<PlaceSmallCategoryResponseDTO> getPlaceSmallCategories(
      PlaceSmallCategoryRequestDTO dto) {
    return placeSmallCategoryRepository.findAll(
        PlaceSmallCategorySearchParam.valueOf(dto), dto.getPageRequest());
  }

}
