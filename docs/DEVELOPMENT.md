# 蛋蛋巴士官网与后台管理系统开发文档

## 1. 开发原则

本项目后续采用前后端分离方式开发。

技术方向：

```text
官网前端：Vue 3 + Vite
后台前端：Vue 3 + Vite + Element Plus
后端服务：Spring Boot
数据库：MySQL
部署环境：腾讯云 Ubuntu
```

重要协作约定：

```text
每次生成代码前，必须先确认需求。
```

确认内容包括：

- 本次要做哪个模块。
- 本次要改哪些页面。
- 本次需要哪些接口。
- 本次涉及哪些数据库表。
- 本次是否需要联调。
- 本次是否需要部署。

当前阶段只编写文档，不生成业务代码，不部署服务器。

功能开发完成后的固定动作：

```text
用户确认功能没有问题后，更新本开发文档。
```

更新内容包括：

- 已完成功能。
- 涉及页面和组件。
- 涉及接口。
- 涉及数据库表。
- 本地运行和验证方式。
- 已知问题或后续待办。

## 2. 当前项目状态

当前目录：

```text
D:/Projects/2026-05-27/new-chat-2
```

当前项目是静态官网，文件包括：

```text
index.html
styles.css
main.js
server.mjs
assets/
```

当前静态项目价值：

- 可作为官网视觉原型。
- 可复用图片素材。
- 可复用页面文案。
- 可复用页面结构。
- 可作为 Vue 官网重构参考。

当前静态项目限制：

- 没有真实后端接口。
- 表单数据只保存在浏览器本地。
- 没有数据库。
- 没有后台管理。
- 不适合长期扩展业务功能。

## 3. 推荐项目结构

后续建议新建总项目目录：

```text
busegg/
  web/                    官网前端
  admin/                  后台管理前端
  server/                 Spring Boot 后端
  docs/                   项目文档
```

当前静态项目可以保留为：

```text
prototype/
```

或者作为 `docs/design-reference/` 保存。

## 4. 官网前端开发方案

### 4.1 技术栈

```text
Vue 3
Vite
Vue Router
Pinia
Axios
```

### 4.2 官网目录结构

```text
web/
  public/
  src/
    api/
      request.js
      site.js
    assets/
      images/
    components/
      SiteHeader.vue
      SiteFooter.vue
      HeroSection.vue
      ValueSection.vue
      ProductSection.vue
      SolutionSection.vue
      PartnerSection.vue
      AboutSection.vue
      NewsSection.vue
      ContactSection.vue
      ContactForm.vue
    router/
      index.js
    stores/
      site.js
    views/
      Home.vue
      NewsDetail.vue
    App.vue
    main.js
  package.json
  vite.config.js
```

### 4.3 官网组件对应关系

| 静态项目模块 | Vue 组件 | 说明 |
| --- | --- | --- |
| 顶部导航 | `SiteHeader.vue` | logo、菜单、移动端导航 |
| 首页首屏 | `HeroSection.vue` | 主标题、按钮、首屏图片 |
| 品牌价值 | `ValueSection.vue` | 三个价值点 |
| 产品服务 | `ProductSection.vue` | 产品和服务展示 |
| 解决方案 | `SolutionSection.vue` | 社区店增长方案 |
| 招商合作 | `PartnerSection.vue` | 三类合作入口 |
| 关于我们 | `AboutSection.vue` | 公司与品牌介绍 |
| 新闻动态 | `NewsSection.vue` | 新闻列表 |
| 联系我们 | `ContactSection.vue` | 联系方式和表单 |
| 表单 | `ContactForm.vue` | 合作意向提交 |
| 页脚 | `SiteFooter.vue` | 版权信息 |

### 4.4 官网开发顺序

1. 搭建 Vue 官网项目。
2. 迁移当前 `assets/` 图片。
3. 还原首页静态展示。
4. 拆分 Vue 组件。
5. 实现移动端响应式。
6. 接入合作意向提交接口。
7. 接入新闻列表接口。
8. 接入产品列表接口。
9. 接入官网配置接口。

第一版可以先完成 1 到 6。

## 5. 后台管理前端开发方案

### 5.1 技术栈

```text
Vue 3
Vite
Vue Router
Pinia
Axios
Element Plus
```

### 5.2 后台目录结构

```text
admin/
  public/
  src/
    api/
      request.js
      auth.js
      dashboard.js
      lead.js
      product.js
      news.js
      banner.js
      file.js
      user.js
      role.js
      siteConfig.js
    components/
      AppLayout.vue
      PageHeader.vue
      SearchForm.vue
      UploadImage.vue
    router/
      index.js
    stores/
      auth.js
      permission.js
    views/
      Login.vue
      Dashboard.vue
      LeadManage.vue
      ProductManage.vue
      NewsManage.vue
      BannerManage.vue
      SiteConfig.vue
      FileManage.vue
      AdminUserManage.vue
      RoleManage.vue
    App.vue
    main.js
  package.json
  vite.config.js
```

### 5.3 后台页面

第一阶段页面：

```text
Login.vue
Dashboard.vue
LeadManage.vue
```

第二阶段页面：

```text
ProductManage.vue
NewsManage.vue
FileManage.vue
```

第三阶段页面：

```text
BannerManage.vue
SiteConfig.vue
AdminUserManage.vue
RoleManage.vue
```

### 5.4 后台开发顺序

1. 搭建后台 Vue 项目。
2. 接入 Element Plus。
3. 实现登录页。
4. 实现后台布局。
5. 实现路由守卫。
6. 实现工作台统计。
7. 实现留资管理。
8. 实现产品管理。
9. 实现新闻管理。
10. 实现图片上传。
11. 实现官网配置。
12. 实现管理员和角色权限。

## 6. 后端开发方案

### 6.1 技术栈

```text
Spring Boot 3
Spring Web
Spring Security
JWT
MyBatis Plus
MySQL
Validation
Lombok
```

### 6.2 后端目录结构

```text
server/
  src/main/java/com/eggbus/
    EggbusApplication.java
    common/
      Result.java
      PageResult.java
      GlobalExceptionHandler.java
    config/
      SecurityConfig.java
      CorsConfig.java
      WebMvcConfig.java
    controller/
      site/
        SiteLeadController.java
        SiteProductController.java
        SiteNewsController.java
        SiteConfigController.java
      admin/
        AuthController.java
        DashboardController.java
        LeadController.java
        ProductController.java
        NewsController.java
        BannerController.java
        FileController.java
        UserController.java
        RoleController.java
    entity/
    mapper/
    service/
    service/impl/
    dto/
    vo/
    security/
    utils/
  src/main/resources/
    application.yml
    mapper/
  pom.xml
```

### 6.3 接口路径约定

官网公开接口：

```text
/api/site/**
```

后台管理接口：

```text
/api/admin/**
```

上传文件访问：

```text
/uploads/**
```

### 6.4 统一响应格式

成功：

```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

失败：

```json
{
  "code": 400,
  "message": "参数错误",
  "data": null
}
```

### 6.5 分页响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [],
    "total": 0,
    "page": 1,
    "pageSize": 10
  }
}
```

### 6.6 认证方式

后台使用 JWT。

登录成功返回：

```json
{
  "token": "jwt-token",
  "user": {
    "id": 1,
    "username": "admin",
    "nickname": "管理员"
  }
}
```

后台接口请求头：

```text
Authorization: Bearer <token>
```

## 7. 数据库设计

### 7.0 数据库命名模板

数据库命名按业务场景区分，统一使用小写字母、数字和下划线。

推荐格式：

```text
项目名_业务场景_环境
```

命名字段：

```text
项目名：busegg
业务场景：site / admin / mall / miniapp / order / test
环境：dev / test / prod
```

示例：

```text
busegg_site_dev       官网与后台第一版开发库
busegg_site_test      官网与后台测试库
busegg_site_prod      官网与后台生产库
busegg_mall_dev       后续商城业务开发库
busegg_miniapp_dev    后续小程序业务开发库
busegg_order_prod     后续订单业务生产库
```

第一版本地开发建议使用：

```text
busegg_site_dev
```

生产环境建议使用：

```text
busegg_site_prod
```

### 7.1 第一版最小表

第一版先做留言闭环，需要：

```text
admin_user
leads
```

### 7.2 完整表规划

```text
admin_user       管理员
admin_role       角色
product          产品
news             新闻
leads            留资
banner           轮播图
site_config      官网配置
file_asset       文件资源
```

### 7.3 表字段建议

管理员表：

```text
id
username
password
nickname
phone
role_id
status
last_login_time
created_at
updated_at
deleted
```

留资表 `leads`：

```text
id
name
phone
type
message
source
status
remark
created_at
updated_at
deleted
```

产品表：

```text
id
name
category
summary
content
cover_url
sort_order
status
created_at
updated_at
deleted
```

新闻表：

```text
id
title
summary
content
cover_url
publish_time
sort_order
status
created_at
updated_at
deleted
```

轮播图表：

```text
id
title
image_url
link_url
sort_order
status
created_at
updated_at
deleted
```

官网配置表：

```text
id
config_key
config_value
description
created_at
updated_at
```

文件表：

```text
id
original_name
file_name
file_url
file_size
file_type
storage_type
created_at
deleted
```

## 8. 第一版开发闭环

第一版目标：

```text
访客在官网提交合作意向
后端接收并保存到 MySQL
管理员登录后台
管理员查看留言
管理员修改留言状态
管理员填写跟进备注
```

第一版包含：

- Vue 官网首页。
- 合作意向表单。
- Spring Boot 留资接口。
- MySQL 留资表。
- 后台登录。
- 后台留资管理。

第一版不包含：

- 产品管理。
- 新闻管理。
- 图片上传。
- 轮播图管理。
- 精细权限。
- 正式部署。

## 9. 开发流程

每个模块开发前执行以下流程：

```text
1. 确认需求
2. 确认页面原型或展示效果
3. 确认接口字段
4. 确认数据库字段
5. 生成代码
6. 本地运行
7. 修复问题
8. 给出变更说明
9. 用户确认功能无问题
10. 更新开发文档
```

示例：

```text
如果要开发留资管理：
先确认列表字段、筛选条件、状态枚举、详情内容、备注规则。
确认后再生成前端、后端和数据库代码。
```

### 9.1 功能完成记录模板

每个功能完成并确认后，在本文档追加记录：

```text
功能名称：
完成时间：
确认状态：
涉及前端：
涉及后端：
涉及数据库：
验证方式：
后续待办：
```

## 10. 本地开发环境

建议端口：

```text
官网前端：   http://localhost:5173
后台前端：   http://localhost:5174
后端服务：   http://localhost:8080
MySQL：      localhost:3306
```

建议本地工具：

```text
Node.js 20+
JDK 17+
Maven 3.9+
MySQL 8
Navicat 或 DBeaver
VS Code 或 IntelliJ IDEA
```

## 11. 腾讯云 Ubuntu 部署预留方案

后续部署到腾讯云 Ubuntu 时，建议安装：

```text
Nginx
JDK 17
MySQL 8
Node.js
PM2 或 systemd
```

推荐生产路径：

```text
/www/busegg/web             官网前端构建产物
/www/busegg/admin           后台前端构建产物
/www/busegg/server          Spring Boot jar
/www/busegg/uploads         上传文件
/www/busegg/logs            日志
```

推荐域名规划：

```text
官网：     https://www.example.com
后台：     https://admin.example.com
接口：     https://api.example.com
```

如果只有一个域名，也可以：

```text
官网：     https://www.example.com
后台：     https://www.example.com/admin
接口：     https://www.example.com/api
```

部署前需要确认：

- 腾讯云服务器公网 IP。
- Ubuntu 版本。
- 域名是否已备案。
- 域名解析方式。
- MySQL 是否同机部署。
- 后端端口。
- HTTPS 证书。
- 上传文件存储位置。

## 12. 后续实施建议

建议按以下顺序推进：

1. 确认第一版范围。
2. 创建新项目结构。
3. 先开发 Vue 官网静态版。
4. 开发 Spring Boot 后端基础。
5. 接通合作意向表单入库。
6. 开发后台登录。
7. 开发后台留资管理。
8. 再扩展产品、新闻、图片和配置模块。
9. 本地验收后再准备腾讯云部署。

## 13. 下一次开发前需要确认

以下内容已经确认：

```text
总项目目录：busegg
第一版范围：官网 + 留资 + 后台查看留言
官网形式：单页滚动
后台 UI：Element Plus
后端包名：com.eggbus
数据库命名：按模板根据业务场景命名
初始管理员账号：bugegg
初始管理员密码：bugegg
```

正式开始生成代码前，仍需确认：

1. 初始账号密码是否最终确认使用 `bugegg`。
2. 管理员第一版是否只做账号密码登录。
3. 图片第一版是否使用服务器本地存储。
4. 是否先创建完整 `web/admin/server/docs` 目录。
5. 第一版是否暂不实现产品、新闻、图片上传和权限管理。

## 14. 功能开发记录

当前状态：

```text
已完成第一阶段代码生成和腾讯云服务器部署。
```

### 14.1 第一阶段官网、后台和后端闭环

功能名称：第一阶段官网留资与后台管理闭环
完成时间：2026-06-10
确认状态：已部署到腾讯云服务器并完成接口验证

涉及前端：
- `web/` 官网单页滚动页面
- `admin/` 后台登录、工作台、留资管理
- 后台构建基础路径：`/admin/`

涉及后端：
- `server/` Spring Boot 服务
- 官网留资接口：`POST /api/site/leads`
- 后台登录接口：`POST /api/admin/auth/login`
- 后台留资接口：`/api/admin/leads`

涉及数据库：
- 数据库：`busegg_site_dev`
- 项目数据库账号：`busegg_app`
- 表：`admin_user`、`leads`
- 初始后台账号：`bugegg`
- 初始后台密码：`bugegg`

部署记录：
- 服务器：腾讯云 Ubuntu，公网 IP `152.136.235.102`
- 部署目录：`/www/static/busegg`
- 官网访问地址：`http://152.136.235.102/`
- 后台访问地址：`http://152.136.235.102/admin/`
- 后端服务：systemd 服务 `busegg-server`
- 后端监听：`127.0.0.1:8080`
- Nginx：80 端口统一提供官网、后台和 `/api/` 反向代理

验证方式：
- 官网首页返回 `200`
- 后台 `/admin/` 返回 `200`
- 后台深链 `/admin/login` 返回后台入口文件
- 后台登录接口返回 `200`
- 官网留资接口返回 `200`

后续待办：
- 修复仓库中 `schema.sql` 和部分源码中文显示/编码问题
- 后续部署建议继续使用 GitHub 仓库作为源码来源，本地构建后上传产物，或在服务器补齐 Node/Maven 后改为服务器构建
- 后台模块继续扩展产品管理、新闻管理、图片上传和官网配置
