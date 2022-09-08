package com.travelrhythm.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegionResponseDTO {

  private Long id;
  private String regionCode;
  private String regionName;

  @QueryProjection
  public RegionResponseDTO(Long id, String regionCode, String regionName) {
    this.id = id;
    this.regionCode = regionCode;
    this.regionName = regionName;
  }

}
