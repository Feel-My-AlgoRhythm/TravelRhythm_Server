package com.travelrhythm.domain.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"name", "addressRoadName"})
)
public class Place {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "place_id")
  private Long id;

  private String name;
  private String addressRoadName;

  @ManyToOne
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;
  @OneToOne
  @JoinColumn(name = "big_category_id", nullable = false, referencedColumnName = "place_big_category_id")
  private PlaceBigCategory placeBigCategory;
  @OneToOne
  @JoinColumn(name = "small_category_id", nullable = false, referencedColumnName = "place_small_category_id")
  private PlaceSmallCategory placeSmallCategory;
  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "detail_id", referencedColumnName = "place_detail_id")
  private PlaceDetail placeDetail;
  // TODO detail 요청 횟수 제한? 검색 결과가 없는 경우와 응답코드 5xx 분기 필요

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Place)) {
      return false;
    }
    Place that = (Place) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
