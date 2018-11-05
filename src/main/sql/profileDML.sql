-- 登陆：getProfileByUsername
SELECT  `profileId`,  `username`,  `password`,  `nickname`,  `last_online`,  `gender`,  `birthday`,  `location`,  `joined`
FROM `profiles`.`profile`
WHERE `username`='123';

-- 注册：register
INSERT ignore INTO `profiles`.`profile` (`username`, `password`, `nickname`)
VALUES ('1234', '123', '123');

-- 查看某人的信息：listProfileByNickname
SELECT  `profileId`,  `username`,  `password`,  `nickname`,  `last_online`,  `gender`,  `birthday`,  `location`,  `joined`
FROM `profiles`.`profile`
WHERE `nickname`='123';

-- 修改个人信息：updateProfileById
UPDATE `profiles`.`profile`
SET `nickname`='12345', `gender`='bb'
WHERE  `username`='123';
