package com.fivemybab.ittabab.bootcamp.command.repository;

import com.fivemybab.ittabab.bootcamp.command.entity.BootCamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BootCampRepository extends JpaRepository<BootCamp, Long> {
}