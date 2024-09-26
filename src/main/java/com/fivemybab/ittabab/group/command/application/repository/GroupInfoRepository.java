package com.fivemybab.ittabab.group.command.application.repository;


import com.fivemybab.ittabab.group.command.domain.aggregate.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo, Long> {

}
