# !/bin/bash
source_war=build/libs/calendar-0.0.1-SNAPSHOT-plain.war
target_war=../../webapps/calendar-api.war

echo "start deploy"
./gradlew war
rm -v $target_war
cp -v $source_war $target_war
./../../bin/shutdown.sh
./../../bin/startup.sh
echo "finish deploy"