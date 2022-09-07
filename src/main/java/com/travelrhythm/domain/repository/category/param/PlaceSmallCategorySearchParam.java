package com.travelrhythm.domain.repository.category.param;

import com.travelrhythm.web.dto.PlaceSmallCategoryRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceSmallCategorySearchParam {

  private Long placeBigCategoryId;

  public static PlaceSmallCategorySearchParam valueOf(PlaceSmallCategoryRequestDTO dto) {
    PlaceSmallCategorySearchParam param = new PlaceSmallCategorySearchParam();
    param.setPlaceBigCategoryId(dto.getPlaceBigCategoryId());
    return param;
  }

}
