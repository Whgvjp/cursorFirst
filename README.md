# 图书借阅管理系统

基于Spring Boot + MyBatis + MySQL的图书借阅管理系统，提供完整的图书管理、用户管理和借阅管理功能。

## 技术栈

- **后端框架**: Spring Boot 2.7.14
- **ORM框架**: MyBatis 2.3.1
- **数据库**: MySQL 8.0+
- **构建工具**: Maven
- **Java版本**: JDK 8+

## 功能特性

### 图书管理
- 图书的增删改查
- 图书状态管理（可借阅、已借出、已下架）
- 图书搜索（按名称、作者、分类）
- 分页查询
- ISBN唯一性验证

### 用户管理
- 用户信息管理
- 用户类型区分（普通用户、管理员）
- 用户状态管理

### 借阅管理
- 图书借阅
- 图书归还
- 图书续借
- 借阅记录查询
- 逾期记录查询
- 借阅状态跟踪

## 项目结构

```
src/main/java/com/library/management/
├── config/          # 配置类
├── controller/      # 控制器层
├── service/         # 服务层
│   └── impl/       # 服务实现
├── mapper/          # MyBatis映射接口
├── entity/          # 实体类
└── dto/             # 数据传输对象

src/main/resources/
├── mapper/          # MyBatis XML映射文件
├── sql/             # 数据库脚本
└── application.yml  # 配置文件
```

## 快速开始

### 环境要求
- JDK 8+
- Maven 3.6+
- MySQL 8.0+

### 1. 克隆项目
```bash
git clone <repository-url>
cd library-management
```

### 2. 配置数据库
1. 创建MySQL数据库
2. 执行 `src/main/resources/sql/init.sql` 脚本
3. 修改 `src/main/resources/application.yml` 中的数据库连接信息

### 3. 启动应用
```bash
mvn spring-boot:run
```

应用将在 `http://localhost:8080` 启动

## API接口

### 图书管理接口
- `POST /api/books` - 新增图书
- `PUT /api/books/{id}` - 更新图书
- `DELETE /api/books/{id}` - 删除图书
- `GET /api/books/{id}` - 根据ID查询图书
- `GET /api/books` - 查询所有图书
- `GET /api/books/page` - 分页查询图书
- `GET /api/books/search` - 搜索图书

### 借阅管理接口
- `POST /api/borrows` - 借阅图书
- `PUT /api/borrows/{id}/return` - 归还图书
- `PUT /api/borrows/{id}/renew` - 续借图书
- `GET /api/borrows/{id}` - 查询借阅记录
- `GET /api/borrows/user/{userId}` - 查询用户借阅记录
- `GET /api/borrows/overdue` - 查询逾期记录

## 数据库设计

### 图书表 (book)
- id: 图书ID（主键）
- name: 图书名称
- author: 作者
- isbn: ISBN号（唯一）
- publisher: 出版社
- publish_date: 出版日期
- category: 图书分类
- status: 图书状态
- description: 图书简介
- create_time: 创建时间
- update_time: 更新时间

### 用户表 (user)
- id: 用户ID（主键）
- username: 用户名（唯一）
- password: 密码
- real_name: 真实姓名
- email: 邮箱
- phone: 手机号
- user_type: 用户类型
- status: 用户状态
- create_time: 创建时间
- update_time: 更新时间

### 借阅记录表 (borrow_record)
- id: 借阅记录ID（主键）
- book_id: 图书ID（外键）
- user_id: 用户ID（外键）
- borrow_time: 借阅时间
- due_time: 应还时间
- return_time: 实际归还时间
- status: 借阅状态
- remark: 备注
- create_time: 创建时间
- update_time: 更新时间

## 测试数据

系统预置了以下测试数据：
- 5本计算机技术类图书
- 4个用户（1个管理员，3个普通用户）
- 3条借阅记录

## 注意事项

1. 确保MySQL服务已启动
2. 修改数据库连接配置中的用户名和密码
3. 首次运行前执行数据库初始化脚本
4. 图书删除前会检查是否正在借阅中

## 许可证

MIT License
