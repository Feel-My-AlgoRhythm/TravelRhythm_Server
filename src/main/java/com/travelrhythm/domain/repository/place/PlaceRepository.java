package com.travelrhythm.domain.repository.place;

import com.travelrhythm.domain.entity.Place;
import com.travelrhythm.domain.entity.PlaceDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {

  List<Place> findTop200ByPlaceDetailOrderByNumberOfPlaceDetailRequest(PlaceDetail placeDetail);

}
