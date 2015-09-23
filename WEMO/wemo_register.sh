#!/bin/sh
. /etc/profile
if [ "$1" = "" ];
then echo
echo "no client id specified, for example:  ./wemo_register.sh b0e826a96401f9d0"
echo
exit
else
java -cp WemoServer.jar mpp.wemo.server.Headless -p 4033 -accept $1 -log
fi
