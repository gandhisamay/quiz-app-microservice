#!/usr/bin/env bash
JAR_PATH="/data/releases/$app/$app*.jar"
JAVA_OPTS=(-XX:+HeapDumpOnOutOfMemoryError
          -XX:HeapDumpPath=/logs/$app
          -XX:+UseParNewGC
          -XX:+UseConcMarkSweepGC
          -XX:+PrintGCDetails
          -XX:+PrintGCDateStamps
          -XX:+PrintTenuringDistribution
          -Xloggc:/logs/$app/gc.log
          -XX:+UseGCLogFileRotation
          -XX:NumberOfGCLogFiles=5
          -XX:GCLogFileSize=16M
          -Xmx512M
          -XX:MinHeapFreeRatio=10
          -XX:MaxHeapFreeRatio=30
          -DenvironmentName=$environmentName
          -Duser.name=$app
          )
JMX_OPTS=(-Dcom.sun.management.jmxremote.port=63129
          -Dcom.sun.management.jmxremote
          -Dcom.sun.management.jmxremote.local.only=false
          -Dcom.sun.management.jmxremote.authenticate=false
          -Dcom.sun.management.jmxremote.ssl=false
          )
java ${JAVA_OPTS[@]} ${JMX_OPTS[@]} -jar $JAR_PATH