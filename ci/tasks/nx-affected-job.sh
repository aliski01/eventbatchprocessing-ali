#!/bin/sh

set -xe

cd $APP
export NG_CLI_ANALYTICS=ci
npm ci
echo 'finding affected apps' > ../apps-to-build/apps-to-build.txt

# Below command will return 0 if the $APP is found within the list of affected apps
npm run affected:apps -- --base=$GIT_BRANCH~1 --head=$GIT_BRANCH | grep $APP

# $? used to find the return value of the last executed command.
# -eq checks numeric equallity 
if [ $? -eq 0 ] # In this case we are see if $? is equal to 0
then
	exit 0 # Exit status of 0 is a success
else
	exit 1
fi