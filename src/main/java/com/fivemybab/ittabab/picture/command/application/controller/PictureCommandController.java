package com.fivemybab.ittabab.picture.command.application.controller;

import com.fivemybab.ittabab.picture.command.application.service.PictureCommandService;
import com.fivemybab.ittabab.picture.command.domain.aggregate.Target;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("/picture")
public class PictureCommandController {

    private final PictureCommandService pictureCommandService;

    public PictureCommandController(PictureCommandService pictureCommandService) {
        this.pictureCommandService = pictureCommandService;
    }

    @PostMapping
    public ResponseEntity<?> uploadPictures(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("target") String target,
            @RequestParam("targetId") Long targetId
    ) {
        try {
            // 사진 파일을 서비스로 전달하여 저장
            List<String> savedFileUrls = pictureCommandService.savePictures(files, Target.valueOf(target), targetId);
            return new ResponseEntity<>(savedFileUrls, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("파일 업로드 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
