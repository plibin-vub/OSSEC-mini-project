#!/bin/bash

#get this scripts directory location
#add reference to stackoverflow article
DIR="$(cd "$(dirname "$0")" && pwd )"

#pull from 'content's git repository
cd ~/wiki-pages
git pull

#convert all files from 'creole' to HTML
find . -name '*.crl' -exec $DIR/creole2html.sh {} \;
