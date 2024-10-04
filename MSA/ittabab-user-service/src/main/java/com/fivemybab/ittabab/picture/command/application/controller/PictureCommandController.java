package com.fivemybab.ittabab.picture.command.application.controller;

import com.fivemybab.ittabab.picture.command.application.service.PictureCommandService;
import com.fivemybab.ittabab.picture.command.domain.aggregate.Target;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/picture")
@RequiredArgsConstructor
@Tag(name = "Picture", description = "사진 업로드 관련 API")
public class PictureCommandController {

    private final PictureCommandService pictureCommandService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadPictures(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("target") String target,
            @RequestParam("targetId") Long targetId
    ) {
        try {
            List<String> savedFileUrls = pictureCommandService.savePictures(files, Target.valueOf(target), targetId);
            return new ResponseEntity<>(savedFileUrls, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("파일 업로드 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
