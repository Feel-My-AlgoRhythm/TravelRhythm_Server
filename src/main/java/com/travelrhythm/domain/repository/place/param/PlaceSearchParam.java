package com.travelrhythm.domain.repository.place.param;

import com.travelrhythm.web.dto.PlaceRequestDTO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceSearchParam {

  private List<Long> regionIdList;
  private List<Long> bigCategoryIdList;
  private Long smallCategoryId;

  private Double startX;
  private Double endX;
  private Double startY;
  private Double endY;

  public static PlaceSearchParam valueOf(PlaceRequestDTO dto) {
    PlaceSearchParam param = new PlaceSearchParam();
    param.setRegionIdList(dto.getRegionIdList());
    param.setBigCategoryIdList(dto.getBigCategoryIdList());
    param.setSmallCategoryId(dto.getSmallCategoryId());
    param.setStartX(dto.getStartX());
    param.setEndX(dto.getEndX());
    param.setStartY(dto.getStartY());
    param.setEndY(dto.getEndY());
    return param;
  }

}
