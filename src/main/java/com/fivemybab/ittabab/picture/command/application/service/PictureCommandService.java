package com.fivemybab.ittabab.picture.command.application.service;

import com.fivemybab.ittabab.picture.command.domain.aggregate.Picture;
import com.fivemybab.ittabab.picture.command.domain.aggregate.Target;
import com.fivemybab.ittabab.picture.command.domain.repository.PictureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PictureCommandService {

    @Value("${upload.path}") // config.properties 에서 본인 파일 경로로 변경 하시길
    private String uploadPath;

    private final PictureRepository pictureRepository;

    public PictureCommandService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Transactional
    public List<String> savePictures(List<MultipartFile> files, Target target, Long targetId) throws IOException {
        List<String> savedFileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            // 파일 이름 중복 방지를 위해 UUID 사용
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath + File.separator + fileName);

            Path directoryPath = Paths.get(uploadPath);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); // 디렉토리가 존재하지 않으면 생성
            }

            // 파일을 지정된 경로에 저장
            Files.write(filePath, file.getBytes());
            System.out.println(filePath.toAbsolutePath());
            // 파일의 URL 경로를 생성
            String fileUrl = "/uploads/" + fileName;

            Picture picture = new Picture(fileUrl, target, targetId);
            pictureRepository.save(picture);

            savedFileUrls.add(fileUrl);
        }

        return savedFileUrls;

    }
}
