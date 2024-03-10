## Steps for running a dev enviroment

1. `find . -type f -name '*.sample' -exec bash -c 'cp "$1" "$(echo "$1" | sed "s/\.sample//")"' _ {} \;`
2. `cd nginx && chmod +x ./certs.sh && ./certs.sh && cd ..`
3. `Devcontainers: Rebuild and Reopen in Container` in Visual studio code.

## Steps for manually running prod

1. `docker build -t bmon-odds-server_bmon-server:latest ./.devcontainer/Dockerfile` if you haven't created a devcontainer yet. #needed?
2. `minikube start --insecure-registry 192.168.49.1:5000` insecure registry is only needed if building outside minikube
3. `minikube addons enable ingress`
4. `nohup kubectl port-forward --pod-running-timeout=24h -n ingress-nginx service/ingress-nginx-controller :80 &`

If possible, build inside the minikube container:
1. `eval $(minikube docker-env)` # TODO: evalling can lets you skip running a registry later
2. `gradle bootBuildImage --imageName=bmon-odds/server` # TODO: build the zip inside minikube and send it over instead

Otherwise set up a docker repository:
1. `docker run -d -p 5000:5000 --name registry registry:2.7`
2. `minikube ssh docker pull 192.168.49.1:5000/bmon-odds/server`
3. `minikube ssh docker tag 192.168.49.1:5000/bmon-odds/server bmon-odds/server:1.0.2`

Finally:
1.  ```
	helm install bmon-odds ./config/helm/bmon-odds \
	--atomic \
	--set "postgresql.auth.postgresPassword=somesecurepassword" \
	--set "postgresql.primary.name=bmon_db" \
	--set "global.postgresql.auth.username=bmon_user" \
	--set "global.postgresql.auth.password=somesecurepassword1" \
	--set "global.postgresql.auth.database=bmon_db" \
	--set "global.postgresql.auth.url=jdbc:postgresql://bmon-odds-postgresql:5432" \
	--set "bmonodds.server.schema.app=bmon_schema" \
	--set "bmonodds.server.schema.flyway=flyway_schema" \
	(for prod)
	--set "bmonodds.server.spring.profilesActive=prod"
	(or for debugging)
	--debug \
	--set "bmonodds.server.spring.profilesActive=dev" \
	--set "bmonodds.server.spring.debug=true"
	```
2. `echo "$(minikube ip) bmon-odds.local" | sudo tee -a /etc/hosts`
3. `curl bmon-odds.local/api/sports`

## Todo's
- [	] Add secrets
- [ ] Add TLS/Certbot
- [ ] Split front and backend ingress
- [ ] Add Frontend project
- [ ] Add oddsgen backend
- [ ] Add Github CI/CD 
- [ ] Split up to dev/staging/prod
- [ ] Setup review env's on PR's
- [ ] Switch to HA postgres
- [ ] Add various monitoring tools