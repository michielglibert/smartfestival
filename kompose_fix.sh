#!/bin/bash
kompose convert
for f in `ls *yaml`
do
        if [[ $f != "docker-compose.yaml" ]] && [[ $f =~ "deployment"  ]];
        then
                name=${f%-*}
                echo "Fixing $name"
                sed -i 's/apiVersion: extensions\/v1beta1/apiVersion: apps\/v1/g' $f
                sed -i "0,/spec:/s//spec:\n  selector:\n    matchLabels:\n      io.kompose.service: $name/" $f
                if [[ ! $f =~ "postgres" ]] && [[ ! $f =~ "mongodb" ]] && [[ ! $f =~ "zookeeper"  ]] && [[ ! $f =~ "kafka" ]];
                then
                        echo "Removing volume claim $f"
                        head -n -8 $f > temp
                        echo "      restartPolicy: Always" >> temp
                        mv temp $f
                fi
                kubectl apply -f $f
                kubectl expose deployment $name

        fi
done