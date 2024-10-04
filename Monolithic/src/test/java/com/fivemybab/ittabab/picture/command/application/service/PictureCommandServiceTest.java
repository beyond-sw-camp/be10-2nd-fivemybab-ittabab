package com.fivemybab.ittabab.picture.command.application.service;

import com.fivemybab.ittabab.picture.command.domain.aggregate.Picture;
import com.fivemybab.ittabab.picture.command.domain.aggregate.Target;
import com.fivemybab.ittabab.picture.command.domain.repository.PictureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PictureCommandServiceTest {

    @Autowired
    private PictureCommandService pictureCommandService;

    @Autowired
    private PictureRepository pictureRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @BeforeEach
    void setUp() throws IOException {
        // 테스트용 디렉토리 생성
        Path directoryPath = Paths.get(uploadPath);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
    }

    @Test
    @DisplayName("사진 저장 성공")
    void savePictures_Success() throws IOException {
        // MockMultipartFile을 사용하여 테스트 파일 생성
        MockMultipartFile file1 = new MockMultipartFile("files", "image1.jpg", "image/jpeg", "test image 1".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("files", "image2.jpg", "image/jpeg", "test image 2".getBytes());

        List<MultipartFile> files = Arrays.asList(file1, file2);
        Target target = Target.POST;
        Long targetId = 1L;

        // 파일 저장 메서드 호출
        List<String> savedFileUrls = pictureCommandService.savePictures(files, target, targetId);

        // 저장된 URL 확인
        assertEquals(2, savedFileUrls.size());
        assertTrue(savedFileUrls.get(0).contains("uploads/"));
        assertTrue(savedFileUrls.get(1).contains("uploads/"));

        // 실제로 파일이 저장되었는지 확인
        for (String fileUrl : savedFileUrls) {
            Path filePath = Paths.get(uploadPath);
            assertTrue(Files.exists(filePath), "파일이 존재해야 합니다: " + filePath);
        }

        // DB에 저장된 사진 확인
        List<Picture> pictures = pictureRepository.findAll();
        assertEquals(2, pictures.size());
    }
}