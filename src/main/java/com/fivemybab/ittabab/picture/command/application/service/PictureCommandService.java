package com.fivemybab.ittabab.picture.command.application.service;

import com.fivemybab.ittabab.picture.command.application.dto.PictureRequest;
import com.fivemybab.ittabab.picture.command.domain.aggregate.Picture;
import com.fivemybab.ittabab.picture.command.domain.aggregate.Target;
import com.fivemybab.ittabab.picture.command.domain.repository.PictureRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${upload.path}") // application.yml에서 설정된 업로드 경로
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

            // 파일을 지정된 경로에 저장
            Files.write(filePath, file.getBytes());

            // 파일의 URL 경로를 생성
            String fileUrl = "/uploads/" + fileName;

            // Picture 엔티티 저장
            Picture picture = new Picture(fileUrl, target, targetId);
            pictureRepository.save(picture);

            // 저장된 파일 URL을 리스트에 추가
            savedFileUrls.add(fileUrl);
        }

        return savedFileUrls;
    }
}
