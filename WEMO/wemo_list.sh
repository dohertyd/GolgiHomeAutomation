#!/bin/sh
. /etc/profile
java -cp WemoServer.jar mpp.wemo.server.Headless -list -log
