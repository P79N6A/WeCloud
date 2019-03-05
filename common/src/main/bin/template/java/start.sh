#!/bin/bash
cd `dirname $0`      　      #这个命令写在脚本文件里才有作用，他返回这个脚本文件放置的目录，并可以根据这个目录来定位所要运行程序的相对位置（绝对位置除外）。
BIN_DIR=`pwd`        　　　　 #得到当前的路径，即：项目根路径下的bin目录
cd ..               　　　　       #返回到项目的根目录
DEPLOY_DIR=`pwd`     　　　　　　　　 　　#将根目录保存下来
CONF_DIR=$DEPLOY_DIR/conf              #将配置文件的目录保存下来
SERVER_NAME=`basename $DEPLOY_DIR`      #获取到当前目录的名称，basename 命令会将路径截取根路径，比如：basename /data/had/hadoop 得到的结果是：hadoop,由此作为项目的名称
PIDS=`ps -ef | grep java | grep "$CONF_DIR" |awk '{print $2}'`     #查找项目是否已经启动，得到PID,先打印出所有的进程，然互过滤出java的进程，再找是否有带有本项目路径的进程，如果有，则截取出PID
if [ -n "$PIDS" ]; then                                         #判断字符串PIDS是否为空，如果不为空，则说明进程已经存在
    echo "ERROR: The $SERVER_NAME already started!"            #弹出进程存在的提示信息
    echo "PID: $PIDS"                                          #打印出PID的值
    exit 1                                                      #脚本执行结束
fi
LOGS_DIR=$DEPLOY_DIR/logs                                 #设置日志文件的输出目录
if [ ! -d $LOGS_DIR ]; then                               #如果目录不存在，就创建目录
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log                        #创建标准日志的输出文件
LIB_DIR=$DEPLOY_DIR/lib                                #得到java项目依赖jar包的存放目录
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`   #遍历整个目录的jar包，将其添加进来
MAIN_CLASS_JAR=`ls $DEPLOY_DIR|grep .jar|awk '{print "'$DEPLOY_DIR'/"$0}'|tr "\n" ":"`     #得到要部署的jar包
JAVA_OPTS=" -Djava.net.preferIPv4Stack=true -Dlog.home=$LOGS_DIR"      #设置java的启动参数
JAVA_MEM_OPTS=""                 #设置java的JVM参数
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xms2g -Xmx2g -XX:PermSize=128m -XX:MaxPermSize=128m -XX:SurvivorRatio=6 -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=dump.hprof "
else
    JAVA_MEM_OPTS=" -server -Xms1g -Xmx1g -XX:PermSize=128m -XX:MaxPermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi
echo -e "Starting the $SERVER_NAME ...\c"   #打印启动日志
nohup java $JAVA_OPTS $JAVA_MEM_OPTS -classpath $CONF_DIR:$LIB_JARS:$MAIN_CLASS_JAR  com.juanpi.lux.trace.ws.LuxTraceWsApplication > $STDOUT_FILE 2>&1 &   #启动java项目，注意要设置classpath,并且给出项目的main class ，并将输出重定向
COUNT=0
while [ $COUNT -lt 1 ]; do                 #定时检测是否成功
    echo -e ".\c"                           #打点
    sleep 1                                  #等待一秒
    COUNT=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
    if [ $COUNT -gt 0 ]; then
        break
    fi
done
echo "OK!"
PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`   #获取启动后的PID
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"