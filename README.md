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

## 运行

### 运行须知
- Windows
> 在Windows 10 专业工作站版 64位 22H2 下测试运行通过， 需要额外安装 VC++ 2015-2019 Redistributable工具
- Linux
> 在 Ubuntu 20.04.5 LTS WSL2、Docker镜像 中测试运行通过

### 运行方法
1. 前往 [Releases](https://github.com/wssy001/mirai-graalvm/releases/latest) 选择合适的压缩包、下载并解压
2. 修改 config下的appilication.yml文件

### 最新的默认的appilication.yml
```yml
mirai-graalvm:
  robots:
    - {
      # QQ号
      account: 12345,
      # 密码
      password: "password",
      # 优先级 （数值越小，优先度越高  默认：0）
      priority: 0
    }
```
### 0.0.2-SNAPSHOT 前默认的appilication.yml
```yml
robot:
  account: 12345
  password: "password"
```
3. 启动程序
