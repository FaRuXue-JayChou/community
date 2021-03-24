CREATE TABLE WECHATUSER
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    unionId VARCHAR(100),
    nickName VARCHAR(50),
    headImg_Url VARCHAR(100),
    token CHAR(36),
    gmt_Created BIGINT,
    gmt_Modified BIGINT
);