package com.travelrhythm.util;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtil {

  private final String DATALAB_API_END_POINT = "https://datalab.visitkorea.or.kr";

  @Autowired
  private RestTemplate restTemplate;

  public String findPoisByDatalab(String ssgCode, String bigCategory) {
    LocalDate now = LocalDate.now();
    LocalDate startDate = now.minusYears(1);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("SGG_CD", ssgCode);
    map.add("txtSGG_CD", "1");
    map.add("txtSIDO_ARR", "1");
    map.add("BASE_YM1", startDate.format(DateTimeFormatter.ofPattern("yyyyMM")));
    map.add("BASE_YM2", now.format(DateTimeFormatter.ofPattern("yyyyMM")));
    map.add("TMAP_CATE_MCLS_CD", bigCategory);
    map.add("srchAreaDate", "1");
    map.add("qid", "BDT_03_04_003");

    return postInterface(DATALAB_API_END_POINT + "/visualize/getTempleteData.do", map);
  }

  private String postInterface(String path, MultiValueMap dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(dto, headers);

      ResponseEntity<String> response = restTemplate.postForEntity
          (URI.create(path), request, String.class);

      if (response.getStatusCodeValue() != 200) {
        throw new HttpException();
      }
      return response.getBody();
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
