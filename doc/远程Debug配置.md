若非docker
idea remote配置
ip:5005
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
jar启动配置
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005


若为docker
idea启动端口映射配置 accessPort:innerPort 例14020:5005
idea remote配置
ip:14020
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=14020
jar启动配置
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005

