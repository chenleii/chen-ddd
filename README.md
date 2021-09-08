# DDD模板项目

以订单系统为背景的DDD示例项目

## 应用启动说明
* 项目启动： 执行 ```com.chen.ddd.start.Application.java```
* 访问API: http://127.0.0.1:7001/doc.html
* 访问H2控制台: http://127.0.0.1:7001/h2-console 登录信息查看 ```application.properties```

## 应用架构
DDD + CQRS + 事件总线

## 包结构说明
````
+-ddd-core: 核心业务组件层。按照业务域划分模块。
|   +-ddd-core-common：公共域（共享内核）
|       +-cqrs：cqrs相关公共接口
|       +-eventbus：事件总线
|       +-specification：业务规则抽象
|       +-page：分页
|       +-objectmapper：对象映射抽象接口
|
|   +-ddd-core-xxx：xx域
|       +-application：应用层
|           +-commandservice：命令服务
|           +-queryservice：查询服务
|           +-eventlistener：事件监听
|       +-domain.model：领域层
|           +-cqrs：cqrs相关
|           +-event：事件
|           +-service：领域服务
|           +-port：端口（当前域对外部能力的依赖. 例如订单域需要物流查询能力。）
|
+-ddd-infrastructure: 基础设施层
|   +-framework: 框架相关
|   +-eventbus: 事件总线实现
|   +-json: json
|   +-persistence: 持久化相关，按业务子域分包
|   +-port: port的实现类，按不同port类型分包
|
+-ddd-client: 对外RPC接口层。按业务域分包，参数和返回值使用包装对象。
|   +-xxx: xxx接口
|
+-rrc-interface: 接口适配层。包括http、rpc实现、mq、定时任务。该层核心职责：参数校验，转换，面向展示的数据组装。
|   +-web: http接口相关
|   +-rpc: rpc接口的实现, Facade对象是对外Port，可以引用applicationService或repository对象
|   +-mq: 消息队列监听
|   +-timedtask: 定时任务
|
+-rrc-configuration: 应用配置。
|
+-rrc-start: 应用启动相关。
|   +-Application: 应用启动类。
|
+-rrc-test: 测试
|   +-archunit: 架构约束
|   +-bdd: BDD测试

```




