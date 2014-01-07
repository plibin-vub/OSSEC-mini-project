#!/bin/bash

CREOLE_FILE=$1
BASE_FILE=`basename $CREOLE_FILE .crl`
HTML=".html"
HTML_FILE="$BASE_FILE$HTML"
txt2tags --target=html --outfile="$BASE_FILE.html" "$CREOLE_FILE"
