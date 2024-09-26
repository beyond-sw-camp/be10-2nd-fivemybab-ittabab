package com.fivemybab.ittabab.board.query.controller;

import com.fivemybab.ittabab.board.query.service.BoardQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//https://dorothy-koo.gitbooks.io/springboot/content/chapter1.html

@RestController // restful한 웹 서비스를 구축하기 위해 사용하는 것이 restcontroller rest를 위한 전용 컨트롤러 REST는 REpresentative State Transfer의 약자로 분산 시스템을 위한 아키텍처다.
@RequestMapping("/api/v1") // 서버의 URL과 특정 처리를 연동(매핑)시키는 구조이다.
@RequiredArgsConstructor // 롬복을 사용해서 생성자를 따로 만들 필요가 없게 해준다. 그래서 final로 영원한 초기화를 시켜줌 그럼 롬복은 뭐냐
/*
1. 롬복이란, getter, setter, toString 등의 메소드를 자동으로 만들어주는 기능 눈에 안보여서 가독성이 떨어지긴 해도 편함.
    1-1.https://velog.io/@swjy1216/Lombok-%EB%A1%AC%EB%B3%B5%EC%9D%B4%EB%9E%80
2. api란, 두 어플리케이션이 소통하는 방법 애플리케이션 프로그램 인터페이스의 약자임
    2-1 restApi란, 위에 있는 rest와 합쳐서 get put delete를 사용하게 해줌
* */
public class BoardQueryController {

    //의존성 주입
    private final BoardQueryService boardQueryService;


    @GetMapping("/board") // requestmapping해오고 그 해온 url에서 알맞은 메소드를 찾게 매핑을 시켜줌
    public ResponseEntity</*dto를 불러옴(꼭 디티오여야만 하는가)*/> getBoard(
            @RequestParam //파라미터 이름으로 바인딩한다. *바인딩, 데이터나 값을 특정한 변수, 메서드, 객체 등과 연결하는 과정 변수를 통해 해당 값을 사용 가능하게 만드는 과정 -> 프론트에서 넘겨준 입력 받은 값을 스프링 부트가 알아서 형태에 맞춰서 넣어준다 이거네
    ){
        디티오 response = boardQueryService.getBoard(/*받아온 requestparam 값들 */);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/board/{postId}")
    public ResponseEntity<다른 디티오> getBoard(@PathVariable Long postId){ //pathvariable 경로를 표시하기 위해 매개변수에 사용
        다른 디티오 reponse = boardQueryService.getBoard(postId);
        return ResponseEntity.ok(response);
    }

    /*ResponseEntity는 Http 응답을 포함하는 클래스이다.  HTTP 응답을 구성하는 데 사용되는 클래스
    httpStatus객체와 함께 반환하면(이라고 하지만 결국 무조건 같이 쓰는거임) HTTP 응답 코드를 지정할 수 있다.
    또한, 응답 본문을 커스터마이징하거나 응답 헤더를 추가할 수 있다.
    @RequestParam: URL 쿼리 파라미터나 폼 데이터를 바인딩.
    @RequestBody: HTTP 요청의 본문을 객체에 바인딩.
    @PathVariable: URL 경로에 있는 값을 바인딩.
    @RequestHeader: HTTP 헤더 값을 바인딩.
    @CookieValue: 쿠키 값을 바인딩.
    @ModelAttribute: 요청 파라미터를 객체에 바인딩.
    @RequestPart: 멀티파트 요청의 일부를 바인딩.
    @RequestAttribute: 요청 속성을 바인딩.*/



}
