package com.fivemybab.ittabab.picture.command.domain.repository;

import com.fivemybab.ittabab.picture.command.domain.aggregate.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
