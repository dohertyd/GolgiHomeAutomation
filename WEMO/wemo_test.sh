#!/bin/sh
. /etc/profile
java -cp WemoServer.jar mpp.wemo.server.Headless -p 4033 -upnp -run -log -trace WARNING
