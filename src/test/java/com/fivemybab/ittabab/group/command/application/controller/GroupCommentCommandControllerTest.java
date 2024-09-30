package com.fivemybab.ittabab.group.command.application.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupCommentCommandControllerTest {

    @Autowired
    private GroupCommentCommandController groupCommentCommandController;
}