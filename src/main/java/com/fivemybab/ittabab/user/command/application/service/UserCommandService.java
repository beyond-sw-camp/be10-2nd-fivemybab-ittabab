package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateUserRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void createUser(CreateUserRequest newUser) {

        // 넘어온 객체를 UserEntity로 변환
        UserInfo user = modelMapper.map(newUser, UserInfo.class);
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

}
