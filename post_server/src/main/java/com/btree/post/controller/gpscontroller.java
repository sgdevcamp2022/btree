package com.btree.post.controller;


import com.btree.post.dto.gpsrequestdto;
import com.btree.post.dto.locationresponsedto;
import com.btree.post.dto.userdto;
import com.btree.post.service.distancecheck;
import com.btree.post.service.kakaoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/post/api/gps")
@RestController
@RequiredArgsConstructor
public class gpscontroller {
    private final kakaoApi kakaoApi;
    private final distancecheck distancecheck;
    @GetMapping("/location")
    public ResponseEntity<userdto> gps_auth(@RequestBody gpsrequestdto gpsrequestdto){

        userdto userdto=new userdto();

        locationresponsedto georesponse=kakaoApi.getCoordinate(gpsrequestdto.getUseraddress()); //유저가 입력한 주소의 좌표 구하기
        locationresponsedto realresponse=kakaoApi.getlocation(gpsrequestdto.getUserx(),gpsrequestdto.getUsery()); //client에서 받은 유저의 실제 좌표
        if(distancecheck.comparelocation(georesponse,realresponse)){
            userdto.setGpsauth(true);
            userdto.setLocate(realresponse.documents[0].address_name);
        }

        System.out.println(userdto.getLocate());
        //카카오api에서 받은 좌표와 실제 유저의 좌표를 거리를 비교해서 5km이내에 있을때 인증되도록 설계

        //카카오 api에서 받은 address_name 을 user db에 저장하기 위해 통신
        //post 서버에서 전달받은 address_name을 이용하여 게시글을 노출하도록 설정
       return ResponseEntity.ok(userdto);
    }
}
