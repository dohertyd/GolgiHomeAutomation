#!/bin/sh
. /etc/profile
nohup java -jar WemoServer.jar -p 4033 -run -log >> wemo.log 2>&1 &
