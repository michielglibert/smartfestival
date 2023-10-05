#!/bin/bash

for d in `ls`
do
        if [ -f "$d/mvnw" ]
        then
                echo "Cleaning $d"
                chmod +x ./$d/mvnw
                cd ./$d
                rm -rf ./target
                ./mvnw clean
                cd ..
        fi
done
