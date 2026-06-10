# busegg

蛋蛋巴士官网与后台管理系统，采用前后端分离架构。

## 目录

```text
busegg/
  web/      官网前端，Vue 3 + Vite
  admin/    后台管理前端，Vue 3 + Vite + Element Plus
  server/   后端服务，Spring Boot + MySQL
  docs/     需求文档和开发文档
```

## 第一阶段范围

```text
官网单页展示
合作意向表单提交
后台登录
后台查看留言
后台修改留言状态和备注
Spring Boot 留资接口
MySQL 数据表脚本
```

## 本地端口

```text
web:    http://localhost:5173
admin:  http://localhost:5174
server: http://localhost:8080
```

## 后端本地配置

后端默认从环境变量读取数据库和 JWT 配置：

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_EXPIRE_HOURS
INIT_ADMIN_USERNAME
INIT_ADMIN_PASSWORD
INIT_ADMIN_NICKNAME
```

也可以复制示例文件：

```text
server/src/main/resources/application-local.example.yml
```

为本机创建不提交 Git 的：

```text
server/src/main/resources/application-local.yml
```

启动时指定：

```powershell
java -jar target/busegg-server-1.0.0.jar --spring.profiles.active=local
```
