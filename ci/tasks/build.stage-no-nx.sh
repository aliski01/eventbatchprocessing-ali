#!/bin/sh

set -xe
export NG_CLI_ANALYTICS=ci

cd $APP
mvn clean package

cd ../

ls -ltr

cp -R $APP ./compiled-$APP-app