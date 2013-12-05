#!/bin/bash

function item_added_to_folder() {
    	FOLDER=$1
    	ITEM=$2
    	CRASH_FILE=$FOLDER/crash
    	if [ -f $CRASH_FILE ]
	then
		VBoxManage startvm ossec-ubuntu-node2
	fi
}

function folder_opened() {
        #Bash function cannot be empty
        echo "Folder opened"
}

function folder_closed() {
        #Bash function cannot be empty
        echo "Folder closed"
}

function item_removed_from_folder() {
        #Bash function cannot be empty
        echo "Item removed from folder"
}
