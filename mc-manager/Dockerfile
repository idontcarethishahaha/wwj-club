# 1 指定构建镜像的基础镜像（周老师的阿里云私有镜像）
FROM registry.cn-hangzhou.aliyuncs.com/joezhou/jdk:17
# 2 声明维护镜像的管理员信息
MAINTAINER WuWenJin
# 3 将宿主机当前目录下的xx.jar包复制到镜像路径的根目录
ADD mc-manager.jar mc-manager.jar
# 4 指定要暴露的虚拟机的端口
EXPOSE 23101
# 5 指定启动容器时（docker run/start）执行的java指令，&表示将容器放在后台进行
CMD ["nohup","java","-jar","mc-manager.jar","&"]