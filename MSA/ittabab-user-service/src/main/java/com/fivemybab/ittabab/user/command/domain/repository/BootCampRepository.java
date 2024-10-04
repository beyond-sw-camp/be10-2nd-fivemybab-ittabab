package com.fivemybab.ittabab.user.command.domain.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.BootCamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootCampRepository extends JpaRepository<BootCamp, Long> {
}