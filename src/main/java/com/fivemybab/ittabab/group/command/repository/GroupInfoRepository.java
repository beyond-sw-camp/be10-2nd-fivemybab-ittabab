package com.fivemybab.ittabab.group.command.repository;


import com.fivemybab.ittabab.group.command.entity.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo, Integer> {

}
