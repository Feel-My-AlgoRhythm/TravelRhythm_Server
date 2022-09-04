package com.travelrhythm.domain.repository;

import com.travelrhythm.domain.entity.PlaceSmallCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceSmallCategoryRepository extends JpaRepository<PlaceSmallCategory, Long> {

  Optional<PlaceSmallCategory> findByName(String name);
}
