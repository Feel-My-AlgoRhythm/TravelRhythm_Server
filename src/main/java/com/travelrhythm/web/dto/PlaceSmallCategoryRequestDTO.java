package com.travelrhythm.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceSmallCategoryRequestDTO extends PagingDTO{

  private Long placeBigCategoryId;

}
