#!/bin/bash

for d in `ls`
do
        if [ -f "$d/mvnw" ]
        then
                echo "Building $d"
                chmod +x ./$d/mvnw
                cd ./$d
                rm -rf ./target
                ./mvnw clean package -DskipTests
                cd ..
        fi
done
