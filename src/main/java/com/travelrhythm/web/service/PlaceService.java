package com.travelrhythm.web.service;

import com.travelrhythm.domain.entity.Place;
import com.travelrhythm.domain.repository.place.PlaceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PlaceService {

  @Autowired
  private PlaceRepository placeRepository;

  public List<Place> getPlaces() {
    return placeRepository.findAll();
  }



}
