# Mirai Graalvm
一个 [Mirai QQ机器人](https://github.com/mamoe/mirai) + [GraalVM](https://www.graalvm.org/) + [SpringBoot3](https://spring.io/projects/spring-boot) 项目脚手架

## 项目介绍
- [BiliBili](https://www.bilibili.com/read/cv21372235)

## 项目地址
- [Github](https://github.com/wssy001/mirai-graalvm)
- [Gitee](https://gitee.com/wssy001/mirai-graalvm)

## 项目结构
```
.
├── README.md
├── pom.xml
└── src
    └──  main
      ├── java
      │ └── com
      │     └── example
      │         └── miraigraalvm
      │             ├── MiraiGraalvmApplication.java    主启动类
      │             ├── config
      │             │ └── RobotConfig.java              机器人配置类 
      │             ├── handler
      │             │ └── FriendMessageHandler.java     好友消息处理类
      │             ├── service
      │             │ ├── PictureService.java           发送图片消息的服务类
      │             │ ├── RobotService.java             机器人主服务类
      │             │ └── TextMessageService.java       发送文本消息的服务类
      │             └── test
      │                 └── AutoTestService.java        自动测试服务类
      └── resources
          ├── META-INF
          │ └── native-image
          │     └── native-image.properties             native-image打包工具的额外参数配置文件
          ├── application.yml
          └── photo.png
    
```
