## 项目介绍
    "Sibase"项目创建于2017年10月3号，正在慢慢成长中。努力打造一个JavaEE企业应用快速开发的架构平台。


## 核心功能
* 通用的DAO、Service、Controller、Module抽象封装为基础类，简化代码书写
* 代码自动生成，新模块的DAO、Service、Controller、Module等基础代码可一键生成
* 统一异常处理


## 项目结构
``` lua
Sibase
├── sibase-common -- Sibase公共模块
|    ├── java -- 公共模块代码
|    └── resources -- 公共的资源文件
```


## 技术选型

### 管理
* maven依赖和项目管理
* git版本控制

### 后端
技术 | 名称 | 官网
----|------|----
Spring Framework | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
SpringMVC | MVC框架  | [http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
SpringDataJpa | ORM框架 | [http://projects.spring.io/spring-data-jpa/](http://projects.spring.io/spring-data-jpa/)
Druid | 数据库连接池  | [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
Thymeleaf | 模板引擎  | [http://www.thymeleaf.org/](http://www.thymeleaf.org/)
Velocity | 模板引擎  | [http://velocity.apache.org/](http://velocity.apache.org/)
Lombok | Bean书写简化 | [https://projectlombok.org/](https://projectlombok.org/)
Mapstruct | Bean之间自动转换 | [http://mapstruct.org/](http://mapstruct.org/)
Fastjson | Json数据 | [https://github.com/alibaba/fastjson](https://github.com/alibaba/fastjson)
Slf4j | 日志组件  | [https://www.slf4j.org/](https://www.slf4j.org/)




## 许可证
Sibase是[GNU LGPL](./LICENSE)。我们还提供额外的[专利授权](./PATENTS)。