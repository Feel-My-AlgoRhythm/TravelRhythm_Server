package com.travelrhythm.domain.repository;

import com.travelrhythm.domain.entity.Place;
import com.travelrhythm.domain.entity.PlaceDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

  List<Place> findTop100ByPlaceDetailOrderById(PlaceDetail placeDetail);

}
