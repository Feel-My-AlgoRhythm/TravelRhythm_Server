package com.travelrhythm.domain.repository.place.param;

import com.travelrhythm.web.dto.PlaceRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceSearchParam {

  private Long regionId;
  private Long bigCategoryId;
  private Long smallCategoryId;

  public static PlaceSearchParam valueOf(PlaceRequestDTO dto) {
    PlaceSearchParam param = new PlaceSearchParam();
    param.setRegionId(dto.getRegionId());
    param.setBigCategoryId(dto.getBigCategoryId());
    param.setSmallCategoryId(dto.getSmallCategoryId());
    return param;
  }

}
