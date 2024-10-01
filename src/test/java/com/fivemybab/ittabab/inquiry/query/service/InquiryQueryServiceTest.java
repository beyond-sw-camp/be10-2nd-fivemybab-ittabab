package com.fivemybab.ittabab.inquiry.query.service;

import com.fivemybab.ittabab.inquiry.query.dto.InquiryDto;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InquiryQueryServiceTest {

    @Autowired
    private InquiryQueryService inquiryService;


    @Test
    @DisplayName("관리자 문의 목록 조회 테스트")
    void findInquiryListTest() throws NotFoundException {

        List<InquiryDto> inquiryList = inquiryService.findInquiryList();

        if(inquiryList.isEmpty()){
            assertTrue(true, "해당 사용자의 문의 목록이 비어있음");
        }else{
            for(InquiryDto inquiry : inquiryList){
                System.out.println(inquiry.getInquiryContent());
            }
        }
        // 첫 번째 문의 검증
        InquiryDto firstInquiry = inquiryList.get(0);
        assertEquals("문의 드립니다", firstInquiry.getInquiryContent());

        // 두 번째 문의 검증
        InquiryDto secondInquiry = inquiryList.get(1);
        assertEquals("문의 드립니다", secondInquiry.getInquiryContent());
    }

    @Test
    @DisplayName("관리자 문의 목록이 비어있을 때 예외 테스트")
    void findInquiryListNotFoundTest() {

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            inquiryService.findInquiryList();
        });

        assertEquals("들어온 문의 없음", exception.getMessage());
    }

    @Test
    @DisplayName("사용자 ID로 문의 목록 조회 테스트")
    void findInquiryListByUserIdTest() throws NotFoundException {
        // 문의를 작성한 userId로 변경하기
        Long userId = 1L;

        List<InquiryDto> inquiryList = inquiryService.findInquiryListByUserId(userId);

        System.out.println("해당 유저가 작성한 문의 개수: " + inquiryList.size());

        if(inquiryList.isEmpty()){
            assertTrue(true, "해당 사용자의 문의 목록이 비어있음");
        }else{
            for(InquiryDto inquiry : inquiryList){
                System.out.println(inquiry.getInquiryContent());
            }
        }
        // 처음 문의한 내용 비교
        InquiryDto firstInquiry = inquiryList.get(2);
        assertEquals("문의 드립니다", firstInquiry.getInquiryContent());

    }

    @Test
    @DisplayName("존재하지 않는 사용자 ID에 대한 문의 목록 조회 테스트")
    void findInquiryListByNonExistentUserIdTest() {

        Long nonExistentUserId = 999L;

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            inquiryService.findInquiryListByUserId(nonExistentUserId);
        });

        assertEquals("사용자 ID " + nonExistentUserId + "에 대한 문의가 없습니다.", exception.getMessage());
    }
}