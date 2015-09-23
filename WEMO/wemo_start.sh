#!/bin/sh
. /etc/profile
nohup java -cp WemoServer.jar mpp.wemo.server.Headless -p 4033 -upnp -run -log >> wemo.log 2>&1 &
