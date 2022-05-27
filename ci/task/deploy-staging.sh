#!/bin/sh
echo 'looking in the file'

ls -a
cd compiledFiles #input value
cd $APP

ls -a

echo "!!! logging into cf"
cf login -u $PWS_USER -p $PWS_PWD -a $PWS_API -o $PWS_ORG -s $PWS_SPACE --skip-ssl-validation

cf target -s $PWS_SPACE

cf push active-$APP -b java_buildpack_offline -p ./dist -c "npm run $APP:serve:ssr:pcf"