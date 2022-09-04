package com.travelrhythm.domain.entity;

import java.util.Objects;
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
