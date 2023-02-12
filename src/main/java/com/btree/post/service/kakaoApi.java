package com.btree.post.service;


import com.btree.post.dto.locationresponsedto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class kakaoApi {

    private final String kakao_apikey="a5ad018e9f3d2ba54593baed905b97a2";

    public locationresponsedto getCoordinate(String address){

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","KakaoAK "+kakao_apikey);

        RestTemplate restTemplate = new RestTemplate();
        String apiURL="https://dapi.kakao.com/v2/local/search/address.JSON?" +
                "query=" + address;
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(apiURL, HttpMethod.GET,entity, locationresponsedto.class).getBody();
    }

    public locationresponsedto getlocation(double locationx,double locationy){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","KakaoAK "+kakao_apikey);

        RestTemplate restTemplate = new RestTemplate();
        String apiURL="https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?" +
                "x="+locationy +"&"+ "y="+locationx;
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(apiURL, HttpMethod.GET,entity, locationresponsedto.class).getBody();

    }

}
