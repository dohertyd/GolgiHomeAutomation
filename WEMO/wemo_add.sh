#!/bin/sh
. /etc/profile
if [ "$1" = "" ];
then echo
echo "no wemo ip address specified, for example:  ./wemo_add.sh 192.168.10.20"
echo
exit
else
java -cp WemoServer.jar mpp.wemo.server.Headless -wemo $1 -log
fi
