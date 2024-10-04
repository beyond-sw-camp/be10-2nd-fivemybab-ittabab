package com.fivemybab.ittabab.good.command.application.service;

import com.fivemybab.ittabab.good.command.domain.aggregate.Good;
import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import com.fivemybab.ittabab.good.command.domain.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoodCommandService {

    private final GoodRepository goodRepository;

    @Transactional
    public void createGood(Long userId, Target target, Long targetId) {

        // 좋아요를 눌렀는지 확인
        goodRepository.findByUserIdAndTargetAndTargetId(userId, target, targetId)
                .ifPresent(good -> {
                    throw new IllegalStateException("이미 좋아요 함");
                });

        Good good = new Good(userId, target, targetId);
        goodRepository.save(good);
    }

    @Transactional
    public void deleteGood(Long userId, Target target, Long targetId) {

        // 좋아요를 눌렀는지 확인
        Good good = (Good) goodRepository.findByUserIdAndTargetAndTargetId(userId, target, targetId)
                .orElseThrow(() -> new IllegalStateException("좋아요 누른 적 없음"));

        goodRepository.delete(good);
    }
}