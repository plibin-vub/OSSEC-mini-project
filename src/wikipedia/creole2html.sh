#!/bin/bash

CREOLE_FILE=$1
BASE_FILE=`basename $CREOLE_FILE .crl`
HTML=".html"
HTML_FILE="$BASE_FILE$HTML"
txt2tags --target=html --outfile="$BASE_FILE.html" "$CREOLE_FILE"

#txt2tags does not handle internal links properly:
#I replace them here with this quick regexp hack, however,
#this is not production code, since it does not handle special characters such
#as spaces properly
sed --in-place -e 's/\[\[\([^]]*\)\]\]/\\<a href="\1">\1<\a>/g'
