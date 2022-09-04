package com.travelrhythm.domain.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PlaceSmallCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "place_small_category_id")
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "big_category_id", nullable = false, referencedColumnName = "place_big_category_id")
  private PlaceBigCategory placeBigCategory;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlaceSmallCategory)) {
      return false;
    }
    PlaceSmallCategory that = (PlaceSmallCategory) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
