#!/bin/sh
. /etc/profile
if [ "$1" = "" ];
then echo
echo "no wemo udn string specified, for example:  ./wemo_remove.sh Socket-1_0-221219K01007AB"
echo
exit
else
java -cp WemoServer.jar mpp.wemo.server.Headless -udn $1 -log
fi
