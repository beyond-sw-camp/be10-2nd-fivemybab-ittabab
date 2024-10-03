DROP TABLE IF EXISTS notification CASCADE;
DROP TABLE IF EXISTS picture CASCADE;
DROP TABLE IF EXISTS good CASCADE;
DROP TABLE IF EXISTS report CASCADE;
DROP TABLE IF EXISTS group_comment CASCADE;
DROP TABLE IF EXISTS group_user CASCADE;
DROP TABLE IF EXISTS group_info CASCADE;
DROP TABLE IF EXISTS group_category CASCADE;
DROP TABLE IF EXISTS schedule_info CASCADE;
DROP TABLE IF EXISTS post_comment CASCADE;
DROP TABLE IF EXISTS post CASCADE;
DROP TABLE IF EXISTS order_menu CASCADE;
DROP TABLE IF EXISTS menu CASCADE;
DROP TABLE IF EXISTS menu_category CASCADE;
DROP TABLE IF EXISTS review CASCADE;
DROP TABLE IF EXISTS favorite CASCADE;
DROP TABLE IF EXISTS store CASCADE;
DROP TABLE IF EXISTS inquiry CASCADE;
DROP TABLE IF EXISTS friend CASCADE;
DROP TABLE IF EXISTS user_info CASCADE;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS bootcamp CASCADE;

-- 훈련 기관(bootcamp)
CREATE TABLE IF NOT EXISTS bootcamp (
    boot_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '부트캠프 번호',
    boot_name VARCHAR(100) NOT NULL COMMENT '부트캠프 이름',
    address VARCHAR(300) NOT NULL COMMENT '주소',
    PRIMARY KEY (boot_id)
) ENGINE=INNODB COMMENT='훈련 기관';

-- 훈련 과정(course)
CREATE TABLE IF NOT EXISTS course (
    course_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '과정번호',
    boot_id BIGINT NOT NULL COMMENT '부트캠프 번호',
    start_date DATE NOT NULL COMMENT '시작일',
    end_date DATE NOT NULL COMMENT '종료일',
    class_name VARCHAR(100) NOT NULL COMMENT '과정 명',
    season_num INT NOT NULL COMMENT '기수번호',
    PRIMARY KEY (course_id),
    FOREIGN KEY (boot_id) REFERENCES bootcamp(boot_id)
) ENGINE=INNODB COMMENT='훈련 과정';

-- 회원(user_info)
CREATE TABLE IF NOT EXISTS user_info (
    user_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '회원번호',
    username VARCHAR(15) NOT NULL COMMENT '회원명',
    login_id VARCHAR(30) UNIQUE NOT NULL COMMENT '로그인 ID',
    pwd VARCHAR(100) NOT NULL COMMENT '비밀번호',
    email VARCHAR(100) NOT NULL COMMENT '이메일',
    phone CHAR(11) UNIQUE NOT NULL COMMENT '연락처',
    birth DATE NOT NULL COMMENT '생년월일',
    course_id BIGINT NOT NULL COMMENT '과정번호',
    user_status ENUM('ACTIVE', 'REPORTED', 'WITHDRAWN') NOT NULL COMMENT '회원 상태',
    user_role ENUM('USER', 'ADMIN') NOT NULL COMMENT '회원 권한',
    sign_up_date DATE NOT NULL COMMENT '가입일',
    sign_out_date DATE NULL COMMENT '탈퇴일',
    PRIMARY KEY (user_id),
    FOREIGN KEY (course_id) REFERENCES course(course_id)
) ENGINE=INNODB COMMENT='회원';

-- 친구(friend)
CREATE TABLE IF NOT EXISTS friend (
    friend_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '친구 번호',
    from_user_id BIGINT NOT NULL COMMENT '요청 보낸 친구',
    to_user_id BIGINT NOT NULL COMMENT '요청 받은 친구',
    friend_status ENUM('PENDING', 'ACCEPTED', 'REJECTED') NOT NULL COMMENT '친구 상태',
    PRIMARY KEY (friend_id),
    FOREIGN KEY (from_user_id) REFERENCES user_info(user_id),
    FOREIGN KEY (to_user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='친구';

-- 문의(inquiry)
CREATE TABLE IF NOT EXISTS inquiry (
    inquiry_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '문의 번호',
    inquiry_content VARCHAR(300) NOT NULL COMMENT '문의 내용',
    create_date DATETIME NOT NULL COMMENT '문의 일시',
    inquiry_reply VARCHAR(300) NULL COMMENT '문의 답변',
    inquiry_reply_time DATETIME NULL COMMENT '문의 답변 시간',
    response_user_id BIGINT NULL COMMENT '문의 답변자',
    inquiry_user_id BIGINT NOT NULL COMMENT '문의자',
    PRIMARY KEY (inquiry_id),
    FOREIGN KEY (response_user_id) REFERENCES user_info(user_id),
    FOREIGN KEY (inquiry_user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='문의';

-- 가게(store)
CREATE TABLE IF NOT EXISTS store (
    store_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '가게 번호',
    store_name VARCHAR(300) NOT NULL COMMENT '가게 이름',
    store_location VARCHAR(300) NOT NULL COMMENT '가게 위치',
    store_open_time TIME NULL COMMENT '가게 오픈 시간',
    store_end_time TIME NULL COMMENT '가게 마감 시간',
    store_week VARCHAR(300) NULL COMMENT '가게 영업 요일',
    store_info VARCHAR(300) NOT NULL COMMENT '가게 소개',
    store_status ENUM('OPEN', 'CLOSED', 'TEMPORARILY_CLOSED', 'CLOSED_PERMANENTLY') NOT NULL COMMENT '가게 상태',
    user_id BIGINT NOT NULL COMMENT '가게 등록자',
    PRIMARY KEY (store_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='가게';

-- 즐겨찾기(favorite)
CREATE TABLE IF NOT EXISTS favorite (
    favorite_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '즐겨찾기 번호',
    user_id BIGINT NOT NULL COMMENT '회원 번호',
    store_id BIGINT NOT NULL COMMENT '가게 번호',
    PRIMARY KEY (favorite_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id),
    FOREIGN KEY (store_id) REFERENCES store(store_id)
) ENGINE=INNODB COMMENT='즐겨찾기';

-- 리뷰(review)
CREATE TABLE IF NOT EXISTS review (
    review_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '리뷰 번호',
    store_id BIGINT NOT NULL COMMENT '가게 번호',
    user_id BIGINT NOT NULL COMMENT '작성자 번호',
    review_content VARCHAR(300) NOT NULL COMMENT '리뷰 내용',
    rating INT NOT NULL COMMENT '별점',
    create_date DATETIME NOT NULL COMMENT '작성 일시',
    update_date DATETIME NULL COMMENT '수정 일시',
    is_blinded BOOLEAN NOT NULL COMMENT '블라인드 여부',
    PRIMARY KEY (review_id),
    FOREIGN KEY (store_id) REFERENCES store(store_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='리뷰';

-- 메뉴 카테고리(menu_category)
CREATE TABLE IF NOT EXISTS menu_category (
    menu_category_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '메뉴 카테고리 번호',
    menu_category_name VARCHAR(300) NOT NULL COMMENT '메뉴 카테고리 이름',
    PRIMARY KEY (menu_category_id)
) ENGINE=INNODB COMMENT='메뉴 카테고리';

-- 메뉴(menu)
CREATE TABLE IF NOT EXISTS menu (
    menu_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '메뉴 번호',
    store_id BIGINT NOT NULL COMMENT '가게 번호',
    menu_name VARCHAR(300) NOT NULL COMMENT '메뉴 이름',
    menu_price INT NOT NULL COMMENT '메뉴 가격',
    menu_category_id BIGINT NOT NULL COMMENT '메뉴 카테고리 번호',
    PRIMARY KEY (menu_id),
    FOREIGN KEY (store_id) REFERENCES store(store_id),
    FOREIGN KEY (menu_category_id) REFERENCES menu_category(menu_category_id)
) ENGINE=INNODB COMMENT='메뉴';

-- 주문 메뉴(order_menu)
CREATE TABLE IF NOT EXISTS order_menu (
    order_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '주문 번호',
    review_id BIGINT NOT NULL COMMENT '리뷰 번호',
    menu_id BIGINT NOT NULL COMMENT '메뉴 번호',
    user_id BIGINT NOT NULL COMMENT '주문자 번호',
    PRIMARY KEY (order_id),
    FOREIGN KEY (review_id) REFERENCES review(review_id),
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
    ) ENGINE=INNODB COMMENT='주문 메뉴';

-- 게시판 글(post)
CREATE TABLE IF NOT EXISTS post (
    post_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '게시글 번호',
    user_id BIGINT NOT NULL COMMENT '작성자 번호',
    post_title VARCHAR(300) NOT NULL COMMENT '게시글 제목',
    post_content VARCHAR(300) NOT NULL COMMENT '게시글 내용',
    create_date DATETIME NOT NULL COMMENT '작성 일시',
    update_date DATETIME NULL COMMENT '수정 일시',
    is_blinded BOOLEAN NOT NULL COMMENT '블라인드 여부',
    PRIMARY KEY (post_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='게시판 글';

-- 게시판 댓글(post_comment)
CREATE TABLE IF NOT EXISTS post_comment (
    post_comment_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '게시판 댓글 번호',
    post_id BIGINT NOT NULL COMMENT '게시글 번호',
    parent_comment_id BIGINT NULL COMMENT '부모댓글 번호',
    user_id BIGINT NOT NULL COMMENT '작성자 번호',
    comment_content VARCHAR(300) NOT NULL COMMENT '댓글 내용',
    create_date DATETIME NOT NULL COMMENT '작성 일시',
    update_date DATETIME NULL COMMENT '수정 일시',
    is_blinded BOOLEAN NOT NULL COMMENT '블라인드 여부',
    PRIMARY KEY (post_comment_id),
    FOREIGN KEY (post_id) REFERENCES post(post_id),
    FOREIGN KEY (parent_comment_id) REFERENCES post_comment(post_comment_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='게시판 댓글';

-- 일정(schedule_info)
CREATE TABLE IF NOT EXISTS schedule_info (
    schedule_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '일정 번호',
    schedule_date DATE NULL COMMENT '일정 날짜',
    schedule_title VARCHAR(300) NOT NULL COMMENT '일정 제목',
    schedule_content VARCHAR(300) NOT NULL COMMENT '일정 내용',
    user_id BIGINT NOT NULL COMMENT '작성자 번호',
    PRIMARY KEY (schedule_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='일정';

-- 모임 카테고리(group_category)
CREATE TABLE IF NOT EXISTS group_category (
    group_category_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '모임 카테고리 번호',
    group_category_name VARCHAR(300) NOT NULL COMMENT '모임 카테고리 이름',
    PRIMARY KEY (group_category_id)
) ENGINE=INNODB COMMENT='모임 카테고리';

-- 모임(group_info)
CREATE TABLE IF NOT EXISTS group_info (
    group_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '모임 번호',
    group_category_id BIGINT NOT NULL COMMENT '모임 카테고리 번호',
    user_id BIGINT NOT NULL COMMENT '모임장 번호',
    group_title VARCHAR(300) NOT NULL COMMENT '모임 제목',
    user_counting INT NOT NULL COMMENT '모집 인원',
    group_status BOOLEAN NOT NULL COMMENT '모집 상태',
    create_date DATETIME NOT NULL COMMENT '생성 일시',
    end_date DATETIME NOT NULL COMMENT '마감 일시',
    group_post VARCHAR(300) NOT NULL COMMENT '게시글 내용',
    is_blinded BOOLEAN NOT NULL COMMENT '블라인드 여부',
    chat_room_status ENUM('NOT_CREATED', 'CREATED', 'CLOSED') NOT NULL DEFAULT 'NOT_CREATED',
    PRIMARY KEY (group_id),
    FOREIGN KEY (group_category_id) REFERENCES group_category(group_category_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='모임';

-- 모임 댓글(group_comment)
CREATE TABLE IF NOT EXISTS group_comment (
    group_comment_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '모임 댓글 번호',
    group_id BIGINT NOT NULL COMMENT '모임 번호',
    parent_comment_id BIGINT NULL COMMENT '부모댓글 번호',
    user_id BIGINT NOT NULL COMMENT '작성자 번호',
    comment_content VARCHAR(300) NOT NULL COMMENT '댓글 내용',
    create_date DATETIME NOT NULL COMMENT '작성 일시',
    update_date DATETIME NULL COMMENT '수정 일시',
    is_blinded BOOLEAN NOT NULL COMMENT '블라인드 여부',
    PRIMARY KEY (group_comment_id),
    FOREIGN KEY (group_id) REFERENCES group_info(group_id),
    FOREIGN KEY (parent_comment_id) REFERENCES group_comment(group_comment_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='모임 댓글';

-- 모임별 회원(group_user)
CREATE TABLE IF NOT EXISTS group_user (
    group_user_id   BIGINT AUTO_INCREMENT NOT NULL COMMENT '모임 회원 번호',
    user_id 		  BIGINT NOT NULL COMMENT '회원 번호',
    group_id		  BIGINT NOT NULL COMMENT '모임 번호',
    PRIMARY KEY (group_user_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id),
    FOREIGN KEY (group_id) REFERENCES group_info(group_id)
) ENGINE=INNODB COMMENT '모임별 회원';

-- 신고(report)
CREATE TABLE IF NOT EXISTS report (
    report_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '신고 번호',
    report_title VARCHAR(300) NOT NULL COMMENT '신고 제목',
    report_content VARCHAR(300) NOT NULL COMMENT '신고 내용',
    target ENUM('REVIEW', 'POST', 'COMMENT') NOT NULL COMMENT '신고 대상',
    target_id BIGINT NOT NULL COMMENT '대상 번호',
    user_id BIGINT NOT NULL COMMENT '작성자 번호',
    create_date DATETIME NOT NULL COMMENT '신고 일시',
    block_start_date DATETIME NULL COMMENT '신고 처리 일시',
    is_resolved BOOLEAN NOT NULL COMMENT '처리 여부',
    PRIMARY KEY (report_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='신고';

-- 좋아요(good)
CREATE TABLE IF NOT EXISTS good (
    good_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '좋아요 번호',
    user_id BIGINT NOT NULL COMMENT '작성자 번호',
    target ENUM('REVIEW', 'POST', 'COMMENT', 'GROUP') NOT NULL COMMENT '대상',
    target_id BIGINT NOT NULL COMMENT '대상 번호',
    PRIMARY KEY (good_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='좋아요';

-- 사진(picture)
CREATE TABLE IF NOT EXISTS picture (
    picture_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '사진 번호',
    picture_url VARCHAR(300) NOT NULL COMMENT '사진 URL',
    target ENUM('REVIEW', 'POST', 'COMMENT', 'GROUP') NOT NULL COMMENT '대상',
    target_id BIGINT NOT NULL COMMENT '대상 번호',
    PRIMARY KEY (picture_id)
) ENGINE=INNODB COMMENT='사진';

-- 알림(notification)
CREATE TABLE IF NOT EXISTS notification (
    notification_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '알림 번호',
    content VARCHAR(300) NOT NULL COMMENT '알림 내용',
    target ENUM('REVIEW', 'POST', 'COMMENT', 'GROUP', 'USER') NOT NULL COMMENT '대상',
    target_id BIGINT NOT NULL COMMENT '대상 번호',
    user_id BIGINT NOT NULL COMMENT '알림 대상자 번호',
    is_read BOOLEAN NOT NULL COMMENT '읽음 여부',
    create_date DATETIME NOT NULL COMMENT '생성 일시',
    PRIMARY KEY (notification_id),
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
) ENGINE=INNODB COMMENT='알림';
