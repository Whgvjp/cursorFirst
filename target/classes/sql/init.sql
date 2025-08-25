-- 创建数据库
CREATE DATABASE IF NOT EXISTS library_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE library_db;

-- 创建图书表
CREATE TABLE IF NOT EXISTS book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '图书ID',
    name VARCHAR(255) NOT NULL COMMENT '图书名称',
    author VARCHAR(100) NOT NULL COMMENT '作者',
    isbn VARCHAR(20) NOT NULL UNIQUE COMMENT 'ISBN号',
    publisher VARCHAR(100) NOT NULL COMMENT '出版社',
    publish_date TIMESTAMP NOT NULL COMMENT '出版日期',
    category VARCHAR(50) NOT NULL COMMENT '图书分类',
    status INT DEFAULT 0 COMMENT '图书状态：0-可借阅，1-已借出，2-已下架',
    description TEXT COMMENT '图书简介',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '图书表';

-- 创建用户表
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) NOT NULL COMMENT '真实姓名',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    user_type INT DEFAULT 0 COMMENT '用户类型：0-普通用户，1-管理员',
    status INT DEFAULT 0 COMMENT '用户状态：0-正常，1-禁用',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '用户表';

-- 创建借阅记录表
CREATE TABLE IF NOT EXISTS borrow_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '借阅记录ID',
    book_id BIGINT NOT NULL COMMENT '图书ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    borrow_time TIMESTAMP NOT NULL COMMENT '借阅时间',
    due_time TIMESTAMP NOT NULL COMMENT '应还时间',
    return_time TIMESTAMP NULL COMMENT '实际归还时间',
    status INT DEFAULT 0 COMMENT '借阅状态：0-借阅中，1-已归还，2-已逾期',
    remark VARCHAR(500) COMMENT '备注',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) COMMENT '借阅记录表';

-- 插入测试数据

-- 插入图书数据
INSERT INTO book (name, author, isbn, publisher, publish_date, category, status, description) VALUES
('Java编程思想', 'Bruce Eckel', '9787111213826', '机械工业出版社', '2007-06-01 00:00:00', '计算机技术', 0, 'Java编程的经典著作'),
('Spring实战', 'Craig Walls', '9787115417305', '人民邮电出版社', '2016-04-01 00:00:00', '计算机技术', 0, 'Spring框架实战指南'),
('MySQL必知必会', 'Ben Forta', '9787115194320', '人民邮电出版社', '2009-01-01 00:00:00', '计算机技术', 0, 'MySQL数据库入门经典'),
('算法导论', 'Thomas H.Cormen', '9787111187776', '机械工业出版社', '2006-09-01 00:00:00', '计算机技术', 0, '计算机算法的经典教材'),
('设计模式', 'Erich Gamma', '9787111075752', '机械工业出版社', '2007-03-01 00:00:00', '计算机技术', 0, '软件开发中的设计模式');

-- 插入用户数据
INSERT INTO user (username, password, real_name, email, phone, user_type, status) VALUES
('admin', 'admin123', '管理员', 'admin@library.com', '13800138000', 1, 0),
('user1', 'user123', '张三', 'zhangsan@example.com', '13800138001', 0, 0),
('user2', 'user123', '李四', 'lisi@example.com', '13800138002', 0, 0),
('user3', 'user123', '王五', 'wangwu@example.com', '13800138003', 0, 0);

-- 插入借阅记录数据
INSERT INTO borrow_record (book_id, user_id, borrow_time, due_time, status, remark) VALUES
(1, 2, '2024-01-01 10:00:00', '2024-02-01 10:00:00', 1, '已归还'),
(2, 3, '2024-01-15 14:00:00', '2024-02-15 14:00:00', 0, '借阅中'),
(3, 4, '2024-01-20 16:00:00', '2024-02-20 16:00:00', 0, '借阅中');

-- 更新已借出图书的状态
UPDATE book SET status = 1 WHERE id IN (2, 3);
UPDATE book SET status = 0 WHERE id = 1;
