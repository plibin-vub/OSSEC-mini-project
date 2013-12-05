#/bin/bash

#args: host_name, host_state

echo $1 >> /media/sf_ossec_vm_share/test.txt
echo $2 >> /media/sf_ossec_vm_share/test.txt

#switch on the severity of the host state (second argument of this script)
#we only want to reboot if the system is actually down!
case "$2" in
UP)
	#do nothing: the node is up
	;;
UNREACHABLE)
	#no reboot necessary, maybe there is a glitch in the network
	#in a real scenario, it would be interesting to send an e-mail to 
	#the system admins on this event
	;;
DOWN)
	#create a file crash in the correct node directory (first argument to
	#the script is the host_name)
	touch /media/sf_ossec_vm_share/nodes/$1/crash
	;;
esac
