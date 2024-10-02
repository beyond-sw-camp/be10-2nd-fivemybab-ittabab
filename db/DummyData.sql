-- 1. bootcamp 데이터 삽입
INSERT INTO bootcamp (boot_name, latitude, longitude)
VALUES ('Bootcamp A', 37.5665, 126.9780),
       ('Bootcamp B', 37.5775, 126.9880),
       ('Bootcamp C', 37.5885, 126.9990);

-- 2. course 데이터 삽입
INSERT INTO course (boot_id, start_date, end_date, class_name, season_num)
VALUES (1, '2024-01-01', '2024-06-01', 'Java Backend', 1),
       (2, '2024-02-01', '2024-07-01', 'Web Frontend', 2),
       (3, '2024-03-01', '2024-08-01', 'Data Science', 3);

-- 3. user_info 데이터 삽입
INSERT INTO user_info (username, login_id, pwd, email, phone, birth, course_id, user_status, user_role, sign_up_date)
VALUES ('Alice', 'alice123', 'password1', 'alice@example.com', '01012345678', '1990-01-01', 1, 'ACTIVE', 'USER',
        '2024-01-01'),
       ('Bob', 'bob123', 'password2', 'bob@example.com', '01023456789', '1992-02-02', 2, 'ACTIVE', 'USER',
        '2024-02-02'),
       ('Charlie', 'charlie123', 'password3', 'charlie@example.com', '01034567890', '1993-03-03', 3, 'ACTIVE', 'USER',
        '2024-03-03'),
       ('David', 'david123', 'password4', 'david@example.com', '01045678901', '1994-04-04', 1, 'REPORTED', 'USER',
        '2024-04-04'),
       ('Eve', 'eve123', 'password5', 'eve@example.com', '01056789012', '1995-05-05', 2, 'WITHDRAWN', 'USER',
        '2024-05-05'),
       ('Frank', 'frank123', 'password6', 'frank@example.com', '01067890123', '1996-06-06', 3, 'ACTIVE', 'ADMIN',
        '2024-06-06'),
       ('Grace', 'grace123', 'password7', 'grace@example.com', '01078901234', '1997-07-07', 1, 'ACTIVE', 'USER',
        '2024-07-07');

-- 4. friend 데이터 삽입
INSERT INTO friend (from_user_id, to_user_id, friend_status)
VALUES (1, 2, 'ACCEPTED'),
       (1, 3, 'PENDING'),
       (2, 3, 'REJECTED'),
       (4, 5, 'ACCEPTED'),
       (6, 7, 'PENDING'),
       (3, 6, 'ACCEPTED'),
       (2, 4, 'REJECTED');

-- 5. store 데이터 삽입
INSERT INTO store (store_name, store_location, store_open_time, store_end_time, store_week, store_info, store_status,
                   user_id)
VALUES ('Store A', 'Location A', '09:00:00', '18:00:00', 'Mon-Fri', 'Info A', 'OPEN', 1),
       ('Store B', 'Location B', '10:00:00', '20:00:00', 'Tue-Sat', 'Info B', 'CLOSED', 2),
       ('Store C', 'Location C', '11:00:00', '22:00:00', 'Wed-Sun', 'Info C', 'TEMPORARILY_CLOSED', 3),
       ('Store D', 'Location D', '08:00:00', '17:00:00', 'Mon-Fri', 'Info D', 'OPEN', 4),
       ('Store E', 'Location E', '09:30:00', '19:30:00', 'Mon-Sat', 'Info E', 'CLOSED_PERMANENTLY', 5),
       ('Store F', 'Location F', '10:30:00', '20:30:00', 'Tue-Sun', 'Info F', 'OPEN', 6),
       ('Store G', 'Location G', '07:00:00', '16:00:00', 'Mon-Fri', 'Info G', 'OPEN', 7);

-- 6. menu_category 데이터 삽입
INSERT INTO menu_category (menu_category_name)
VALUES ('Korean'),
       ('Chinese'),
       ('Japanese'),
       ('Western');

-- 7. menu 데이터 삽입
INSERT INTO menu (store_id, menu_name, menu_price, menu_category_id)
VALUES (1, 'Bibimbap', 8000, 1),
       (2, 'Sweet and Sour Pork', 12000, 2),
       (3, 'Sushi', 15000, 3),
       (4, 'Pasta', 13000, 4),
       (5, 'Tteokbokki', 7000, 1),
       (6, 'Fried Rice', 9000, 2),
       (7, 'Ramen', 6000, 3);

-- 8. review 데이터 삽입
INSERT INTO review (store_id, user_id, review_content, rating, create_date, update_date, is_blinded)
VALUES (1, 1, 'Great food!', 5, '2024-01-01 12:00:00', NULL, TRUE),
       (2, 2, 'Nice service.', 4, '2024-02-01 13:00:00', NULL, TRUE),
       (3, 3, 'Good atmosphere.', 5, '2024-03-01 14:00:00', NULL, TRUE),
       (4, 4, 'Delicious.', 5, '2024-04-01 15:00:00', NULL, TRUE),
       (5, 5, 'Not bad.', 3, '2024-05-01 16:00:00', NULL, TRUE),
       (6, 6, 'Worth the price.', 4, '2024-06-01 17:00:00', NULL, TRUE),
       (7, 7, 'Average.', 3, '2024-07-01 18:00:00', NULL, TRUE);

-- 9. post 데이터 삽입
INSERT INTO post (user_id, post_title, post_content, create_date, update_date, is_blinded)
VALUES (1, 'Post Title 1', 'Post Content 1', '2024-01-01 12:00:00', NULL, TRUE),
       (2, 'Post Title 2', 'Post Content 2', '2024-02-01 13:00:00', NULL, TRUE),
       (3, 'Post Title 3', 'Post Content 3', '2024-03-01 14:00:00', NULL, TRUE),
       (4, 'Post Title 4', 'Post Content 4', '2024-04-01 15:00:00', NULL, TRUE),
       (5, 'Post Title 5', 'Post Content 5', '2024-05-01 16:00:00', NULL, TRUE),
       (6, 'Post Title 6', 'Post Content 6', '2024-06-01 17:00:00', NULL, TRUE),
       (7, 'Post Title 7', 'Post Content 7', '2024-07-01 18:00:00', NULL, TRUE);

-- 10. post_comment 데이터 삽입
INSERT INTO post_comment (post_id, parent_comment_id, user_id, comment_content, create_date, update_date, is_blinded)
VALUES (1, NULL, 2, 'Comment 1 on Post 1', '2024-01-02 12:00:00', NULL, TRUE),
       (1, NULL, 3, 'Comment 2 on Post 1', '2024-01-03 12:00:00', NULL, TRUE),
       (2, NULL, 4, 'Comment 1 on Post 2', '2024-02-02 13:00:00', NULL, TRUE),
       (3, NULL, 5, 'Comment 1 on Post 3', '2024-03-02 14:00:00', NULL, TRUE),
       (3, NULL, 6, 'Comment 2 on Post 3', '2024-03-03 14:00:00', NULL, TRUE),
       (4, NULL, 7, 'Comment 1 on Post 4', '2024-04-02 15:00:00', NULL, TRUE),
       (5, NULL, 1, 'Comment 1 on Post 5', '2024-05-02 16:00:00', NULL, TRUE);

-- 11. schedule_info 데이터 삽입
INSERT INTO schedule_info (schedule_date, schedule_title, schedule_content, user_id)
VALUES ('2024-01-10', 'Schedule 1', 'Content 1', 1),
       ('2024-02-10', 'Schedule 2', 'Content 2', 2),
       ('2024-03-10', 'Schedule 3', 'Content 3', 3),
       ('2024-04-10', 'Schedule 4', 'Content 4', 4),
       ('2024-05-10', 'Schedule 5', 'Content 5', 5),
       ('2024-06-10', 'Schedule 6', 'Content 6', 6),
       ('2024-07-10', 'Schedule 7', 'Content 7', 7);

-- 12. group_category 데이터 삽입
INSERT INTO group_category (group_category_name)
VALUES ('Hiking'),
       ('Reading'),
       ('Cooking'),
       ('Sports');

-- 13. group_info 데이터 삽입
INSERT INTO group_info (group_category_id, user_id, group_title, user_counting, group_status, create_date, end_date,
                        group_post, is_blinded, chat_room_status)
VALUES (1, 1, 'Hiking Group', 10, TRUE, '2024-01-01 12:00:00', '2024-01-31 12:00:00', 'Hiking group post content', TRUE,
        'CREATED'),
       (2, 2, 'Reading Club', 15, TRUE, '2024-02-01 12:00:00', '2024-02-28 12:00:00', 'Reading club post content', TRUE,
        'NOT_CREATED'),
       (3, 3, 'Cooking Class', 20, TRUE, '2024-03-01 12:00:00', '2024-03-31 12:00:00', 'Cooking class post content',
        TRUE, 'CLOSED'),
       (4, 4, 'Sports Team', 25, TRUE, '2024-04-01 12:00:00', '2024-04-30 12:00:00', 'Sports team post content', TRUE,
        'CREATED'),
       (1, 5, 'Outdoor Adventures', 30, TRUE, '2024-05-01 12:00:00', '2024-05-31 12:00:00',
        'Outdoor adventures post content', TRUE, 'NOT_CREATED'),
       (2, 6, 'Book Lovers', 35, TRUE, '2024-06-01 12:00:00', '2024-06-30 12:00:00', 'Book lovers post content', TRUE,
        'CLOSED'),
       (3, 7, 'Master Chefs', 40, TRUE, '2024-07-01 12:00:00', '2024-07-31 12:00:00', 'Master chefs post content', TRUE,
        'CREATED');

-- 14. notification 데이터 삽입
INSERT INTO notification (content, target, target_id, user_id, is_read, create_date)
VALUES ('You have a new comment', 'POST', 1, 1, FALSE, '2024-01-01 12:00:00'),
       ('Your post was liked', 'POST', 2, 2, FALSE, '2024-02-01 12:00:00'),
       ('Your comment received a reply', 'COMMENT', 3, 3, TRUE, '2024-03-01 12:00:00'),
       ('New member joined your group', 'GROUP', 4, 4, FALSE, '2024-04-01 12:00:00'),
       ('Your review was featured', 'REVIEW', 5, 5, TRUE, '2024-05-01 12:00:00'),
       ('Group event is starting soon', 'GROUP', 6, 6, FALSE, '2024-06-01 12:00:00'),
       ('New friend request received', 'USER', 7, 7, TRUE, '2024-07-01 12:00:00');

