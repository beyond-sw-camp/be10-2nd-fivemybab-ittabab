package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommandService implements UserDetailsService {

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


    public void deleteUser(Long userNo) {

        userRepository.deleteById(userNo);

    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        UserInfo loginUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException(loginId));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(loginUser.getUserRole().name()));

        return new User(loginUser.getLoginId(), loginUser.getPwd(), grantedAuthorities);
    }
}
