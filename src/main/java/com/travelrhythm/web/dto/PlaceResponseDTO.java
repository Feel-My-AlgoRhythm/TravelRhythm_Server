package com.travelrhythm.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceResponseDTO {

  private Long id;
  private String name;
  private String addressRoadName;

  private Long regionId;
  private String regionCode;
  private String regionName;

  private Long bigCategoryId;
  private String bigCategoryName;

  private Long smallCategoryId;
  private String smallCategoryName;

  private Long placeDetailId;
  private String naverId;
  private String posExact;
  private String x;
  private String y;
  private Map<String, Object> placeDetailData;

  @QueryProjection
  public PlaceResponseDTO(Long id, String name, String addressRoadName, Long regionId,
      String regionCode, String regionName, Long bigCategoryId, String bigCategoryName,
      Long smallCategoryId, String smallCategoryName, Long placeDetailId, String naverId,
      String posExact, String x, String y,
      Map<String, Object> placeDetailData) {
    this.id = id;
    this.name = name;
    this.addressRoadName = addressRoadName;
    this.regionId = regionId;
    this.regionCode = regionCode;
    this.regionName = regionName;
    this.bigCategoryId = bigCategoryId;
    this.bigCategoryName = bigCategoryName;
    this.smallCategoryId = smallCategoryId;
    this.smallCategoryName = smallCategoryName;
    this.placeDetailId = placeDetailId;
    this.naverId = naverId;
    this.posExact = posExact;
    this.x = x;
    this.y = y;
    this.placeDetailData = placeDetailData;
  }
}
