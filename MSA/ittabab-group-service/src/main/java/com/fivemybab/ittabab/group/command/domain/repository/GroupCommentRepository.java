package com.fivemybab.ittabab.group.command.domain.repository;

import com.fivemybab.ittabab.group.command.domain.aggregate.GroupComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCommentRepository extends JpaRepository<GroupComment, Long> {
    public void deleteByGroupId(Long groupId);
}
