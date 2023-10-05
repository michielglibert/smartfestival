echo "Alles wordt verwijderd!!!!"
echo "Geef 1 als argument mee als je dit zeker weet"

if [[ $1 == "1" ]];
then
        kubectl delete --all deployments
        kubectl delete --all svc
        kubectl delete --all pods
        kubectl delete --all persistentVolumeClaim
fi
