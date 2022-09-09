package com.travelrhythm.batch;

import com.travelrhythm.domain.entity.Place;
import com.travelrhythm.domain.entity.PlaceBigCategory;
import com.travelrhythm.domain.entity.PlaceDetail;
import com.travelrhythm.domain.entity.PlaceSmallCategory;
import com.travelrhythm.domain.entity.Region;
import com.travelrhythm.domain.repository.category.PlaceBigCategoryRepository;
import com.travelrhythm.domain.repository.place.PlaceDetailRepository;
import com.travelrhythm.domain.repository.place.PlaceRepository;
import com.travelrhythm.domain.repository.category.PlaceSmallCategoryRepository;
import com.travelrhythm.domain.repository.region.RegionRepository;
import com.travelrhythm.util.HttpUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

@Slf4j
@Service
public class BatchService {

  @Autowired
  private RegionRepository regionRepository;
  @Autowired
  private PlaceRepository placeRepository;
  @Autowired
  private PlaceDetailRepository placeDetailRepository;
  @Autowired
  private PlaceBigCategoryRepository placeBigCategoryRepository;
  @Autowired
  private PlaceSmallCategoryRepository placeSmallCategoryRepository;
  @Autowired
  private HttpUtil httpUtil;

  @Transactional
  public void findPoisByDatalab() {
    // get region and category list for requesting to get place list
    List<Region> regionList = regionRepository.findAll();
    List<PlaceBigCategory> categoryList = placeBigCategoryRepository.findAll();

    //TODO get one randomly
    // https://dazbee.tistory.com/49?category=1040314
    int randomValue = (int) (Math.random() * regionList.size());
    Region region = regionList.get(randomValue);
    randomValue = (int) (Math.random() * categoryList.size());
    PlaceBigCategory bigCategory = categoryList.get(randomValue);

    List<Place> placeListForSave = new ArrayList<>();
    try {
      log.info("findPoisByDatalab REQ : {} {} | {}", region.getSsgCode(), region.getSsgName(),
          bigCategory.getName());
      String responseValue = httpUtil.findPoisByDatalab(region.getSsgCode(), bigCategory.getName());

      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObj = (JSONObject) jsonParser.parse(responseValue);
      JSONArray jsonArray = (JSONArray) jsonObj.get("list");

      for (int i = 0; i < jsonArray.size(); ++i) {
        JSONObject jo = (JSONObject) jsonArray.get(i);

        String smallCategoryValue = jo.get("KTO_CATE_SCLS_NM").toString();
        PlaceSmallCategory smallCategory = placeSmallCategoryRepository
            .findByName(smallCategoryValue).orElse(null);

        // add new small category
        if (smallCategory == null) {
          log.info("new small category : {}", smallCategoryValue);
          smallCategory = new PlaceSmallCategory();
          smallCategory.setName(smallCategoryValue);
          smallCategory.setPlaceBigCategory(bigCategory);
          placeSmallCategoryRepository.save(smallCategory);
        }

        String name = jo.get("ITS_BRO_NM").toString();
        String addressRoadName = jo.get("ADDR_ROAD_NM").toString();
        Place place = placeRepository.findByNameAndAddressRoadName(name, addressRoadName)
            .orElse(null);

        // add new place
        if (place == null) {
          Place newPlace = new Place();
          newPlace.setName(name);
          newPlace.setAddressRoadName(addressRoadName);
          newPlace.setNumberOfPlaceDetailRequest(0);
          newPlace.setPlaceBigCategory(bigCategory);
          newPlace.setPlaceSmallCategory(smallCategory);
          newPlace.setRegion(region);
          placeListForSave.add(newPlace);
        }
      }
    } catch (RestClientException e) {
      log.error("RestClientException: {}", e.getMessage());
    } catch (ParseException e) {
      log.error("ParseException: {}", e.getMessage());
    } catch (Exception e) {
      log.error("UndefinedException: {}", e.getMessage());
    }
    log.info("JOB [{}] ended : total new place: {}", this.getClass().getSimpleName(), placeListForSave.size());
    placeRepository.saveAll(placeListForSave);
  }

  @Transactional
  public void addPoiDetailDataByNaver() {
    // get place list without detail info
    List<Place> placeList = placeRepository
        .findTop200ByPlaceDetailOrderByNumberOfPlaceDetailRequest(null);

    for (Place place : placeList) {
      PlaceDetail placeDetail = getPlaceDetailTransaction(place);
      // set detail info
      if (placeDetail != null) {
        place.setPlaceDetail(placeDetail);
      }
      // set number of request
      place.setNumberOfPlaceDetailRequest(place.getNumberOfPlaceDetailRequest() + 1);
      placeRepository.save(place);
    }
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public PlaceDetail getPlaceDetailTransaction(Place place) {
    PlaceDetail placeDetail = null;
    try {
      log.info("HTTP REQ : {}, {}", place.getId(), place.getName());
      String responseValue = httpUtil.findPoiDetailDataByNaver(place.getName());

      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObj = (JSONObject) jsonParser.parse(responseValue);
      JSONObject resultObj = (JSONObject) jsonParser.parse(jsonObj.get("result").toString());
      JSONObject placeObj = (JSONObject) jsonParser.parse(resultObj.get("place").toString());
      JSONObject dataObj = (JSONObject) ((JSONArray) placeObj.get("list")).get(0);

      placeDetail = new PlaceDetail();
      placeDetail.setData(dataObj);
      placeDetail.setNaverId(dataObj.get("id").toString());
      placeDetail.setPosExact(dataObj.get("posExact").toString());
      placeDetail.setX(dataObj.get("x").toString());
      placeDetail.setY(dataObj.get("y").toString());
      placeDetailRepository.save(placeDetail);
    } catch (RestClientException e) {
      log.error("RestClientException: {}", e.getMessage());
    } catch (ParseException e) {
      log.error("ParseException: {}", e.getMessage());
    } catch (Exception e) {
      log.error("UndefinedException: {}", e.getMessage());
    }
    return placeDetail;
  }

}
