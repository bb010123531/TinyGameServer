# TinyGameServer

----
(Dota比较喜欢小小，tiny由此而来:sunglasses:)

### 相关技能

- Maven、Spring
- Java1.8、netty4、protobuf3(syntax2, 习惯用2了, 慢慢学习3)

### maven结构

- parent：tiny.gameserver
- child: 
```
	<module>tiny-gs</module>   // 服务器主逻辑
	<module>tiny-client</module> // 测试用客户端
	<module>tiny-config</module> // 表格、协议等相关配置信息
	<module>tiny-link</module> // link服务器，服务器和客户端的中转
	<module>tiny-net</module> //  网络相关公用类
```

### 项目功能
实现简单的游戏服务器功能，完成与客户端(用项目下的client测试)消息的通信

### 架构思想
gs-link-client

### 使用介绍

- 生成协议 tiny-config/protoGen/gen.bat  生成相关的协议文件
- 依次启动 ServerMain 、LinkServer、ClientMain，ClientMain控制台输入1或者2（C2SMessageInit编写）进行消息测试

### 联系我

- wechat `tiny_9892`
- 欢迎沟通，共同进步
