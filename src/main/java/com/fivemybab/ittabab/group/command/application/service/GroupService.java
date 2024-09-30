package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.domain.aggregate.GroupInfo;
import com.fivemybab.ittabab.group.command.domain.aggregate.GroupUser;
import com.fivemybab.ittabab.group.command.domain.repository.GroupInfoRepository;
import com.fivemybab.ittabab.group.command.domain.repository.GroupUserRepository;
import com.fivemybab.ittabab.group.query.service.GroupQueryService;
import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.query.dto.GroupUserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupInfoRepository groupInfoRepository;
    private final GroupUserRepository groupUserRepository;
    private final ModelMapper modelMapper;
    private final GroupQueryService groupQueryService;

    public GroupInfoDto findGroupByGroupId(Long groupId) {
        return groupQueryService.findGroupByGroupId(groupId);
    }

    /* 모임 등록 */
    @Transactional
    public void registGroup(GroupInfoDto newGroupInfo) {
        groupInfoRepository.save(modelMapper.map(newGroupInfo, GroupInfo.class));
    }

    /* 로그인 ID -> 유저 ID */
    public Long loginIdToUserId(String loginUserLoginId) {
        return groupQueryService.loginIdToUserId(loginUserLoginId);
    }

    /* 모임 삭제 */
    @Transactional
    public void deleteGroupInfo(Long groupId) {
        groupInfoRepository.deleteById(groupId);
    }

    /* 모임에 가입된 사용자 아이디 가져오는 메소드 */
    public List<Long> findGroupUserByGroupId(Long groupId) {
        return groupQueryService.findGroupUserByGroupId(groupId);
    }

    /* 모임에 신규 사용자 가입 메소드 */
    public void registGroupUser(Long userId, Long groupId) {
        GroupUserDto newGroupUser = new GroupUserDto(null, userId, groupId);

        groupUserRepository.save(modelMapper.map(newGroupUser, GroupUser.class));
    }
}
