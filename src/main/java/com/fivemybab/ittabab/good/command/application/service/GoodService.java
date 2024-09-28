package com.fivemybab.ittabab.good.command.application.service;

import com.fivemybab.ittabab.good.command.domain.aggregate.Good;
import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import com.fivemybab.ittabab.good.command.domain.repository.GoodRepository;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GoodService {


    private final GoodRepository goodRepository;

    // 좋아요 등록
    public void createGood(Long userId, Target target, Long targetId) {
        // 이미 좋아요를 눌렀는지 확인
        if (goodRepository.existsByUserIdAndTargetAndTargetId(userId, target, targetId)) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }

        // 좋아요 객체 생성
        Good good = Good.builder()
                .userId(userId)
                .target(target)
                .targetId(targetId)
                .build();

        goodRepository.save(good);
    }

    // 좋아요 삭제
    public void deleteGood(Long userId, Target target, Long targetId) {
        // 좋아요 존재 여부 확인
        Good good = (Good) goodRepository.findByUserIdAndTargetAndTargetId(userId, target, targetId)
                .orElseThrow(() -> new IllegalStateException("좋아요가 존재하지 않습니다."));

        // 좋아요 삭제
        goodRepository.delete(good);
    }
}