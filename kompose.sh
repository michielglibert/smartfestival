#!/bin/bash
for f in `ls *yaml`
do
        if [[ $f != "docker-compose.yaml" ]] && [[ $f =~ "deployment"  ]];
        then
            kubectl apply -f $f
            kubectl expose deployment $name
        fi
done