package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.config.redis.MailService;
import com.fivemybab.ittabab.config.redis.RedisService;
import com.fivemybab.ittabab.exception.ServerInternalException;
import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.MailRequestDto;
import com.fivemybab.ittabab.user.command.application.dto.UpdateUserRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final RedisService redisService;

    private String AUTH_CODE_PREFIX = "AuthCode_";
    @Value("${mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    @Transactional
    public void createUser(CreateUserRequest newUser) {

        // 넘어온 객체를 UserEntity로 변환
        UserInfo user = modelMapper.map(newUser, UserInfo.class);

        // 이메일 인증 검증
        EmailVerification(newUser.getEmail(), newUser.getAuthCode());

        user.encryptPwd(passwordEncoder.encode(newUser.getPwd()));
        userRepository.save(user);
    }

    @Transactional
    public void modifyUser(Long userNo, UpdateUserRequest updateUserRequest) {

        UserInfo foundUser = userRepository.findByUserId(userNo);
        foundUser.modifyPwd(passwordEncoder.encode(updateUserRequest.getPwd()));
        foundUser.modifyPhone(updateUserRequest.getPhone());
    }

    @Transactional
    public void deleteUser(Long userNo) {

        userRepository.deleteById(userNo);
    }

    public void sendCodeToEmail(MailRequestDto mailRequestDto) {
        String email = mailRequestDto.getEmail();

        // email 중복검사
        sameUserInDBByEmail(email);

        String title = "회원가입 이메일 인증 번호";
        String authCode = createCode();
        mailService.sendEmail(email, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setValues(AUTH_CODE_PREFIX + email,
                authCode, Duration.ofMillis(authCodeExpirationMillis));
    }

    private void sameUserInDBByEmail(String email) {

        UserInfo user = userRepository.findByEmail(email);

        if (user != null) {
            // 사용자 정보가 존재할 경우 예외 발생
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + email);
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("UserCommandService.createCode() exception occur");
            throw new ServerInternalException("UserCommandService.createCode()에서 에러가 발생했습니다.");
        }
    }

    private void EmailVerification(String email, String authCode) {

        sameUserInDBByEmail(email);

        String key = AUTH_CODE_PREFIX + email;
        String redisAuthCode = redisService.getValues(key);

        if (!(redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode))) {
            throw new IllegalArgumentException("인증번호가 틀렸습니다. 다시 입력해주세요.");
        } else {
            redisService.deleteValues(key);
        }
    }

}
