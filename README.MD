## One-time setup
1. `docker volume create --opt type=tmpfs --opt device=tmpfs --opt o=size=1g tmpfs-1g-img-repo`

## Steps for running a dev enviroment

1. `find . -type f -name '*.sample' -exec bash -c 'cp "$1" "$(echo "$1" | sed "s/\.sample//")"' _ {} \;`
2. `cd nginx && chmod +x ./certs.sh && ./certs.sh && cd ..`
3. `Devcontainers: Rebuild and Reopen in Container` in your favorite editor that supports .devcontainers.

## Steps for manually running prod

1. `docker build -t bmon-odds-server_bmon-server:latest ./.devcontainer/Dockerfile` if you haven't created a devcontainer yet. #needed?
2. `minikube start --insecure-registry 192.168.49.1:5000` insecure registry is only needed if building outside minikube
3. `minikube addons enable ingress`
4. `nohup kubectl port-forward --pod-running-timeout=24h -n ingress-nginx service/ingress-nginx-controller 4433:443 &`

### Steps for creating/updating images in minikube 
:warning: These steps are tedious till .devcontainers has support for passing `ssh-agent` through which can then be handled by helm, choose 1 of the following 3:

If you have 1g spare, create a tmpfs mount which will contain our built images.
1. `minikube mount /tmp/tmpfs-1g-img-repo/:/tmp/tmpfs-1g-img-repo`
2. `gradle bootBuildImage --imageName=bmon-odds/server`
3. `docker save bmon-odds/server | gzip > /tmp/tmpfs-1g-img-repo/bmon-odds-server.tar.gz` (inside the `server` workspace)
4. `docker save bmon-odds/gen | gzip > /tmp/tmpfs-1g-img-repo/bmon-odds-gen.tar.gz` (inside the `gen` workspace)
5. `docker save bmon-odds/frontend | gzip > /tmp/tmpfs-1g-img-repo/bmon-odds-frontend.tar.gz` (inside the `frontend` workspace)
6. `docker load < /tmp/tmpfs-1g-img-repo/bmon-odds-frontend.tar.gz` (inside `minikube`)
7. `docker load < /tmp/tmpfs-1g-img-repo/bmon-odds-gen.tar.gz` (inside `minikube`)
8. `docker load < /tmp/tmpfs-1g-img-repo/bmon-odds-server.tar.gz` (inside `minikube`)

Otherwise, switch your docker driver and build inside the minikube container:
1. `eval $(minikube docker-env)` 
2. `gradle bootBuildImage --imageName=bmon-odds/server`
3. TODO: rest of the steps
<!-- TODO: rest of the steps for the other projects -->

Otherwise, set up a docker repository:
1. `docker run -d -p 5000:5000 --name registry registry:2.7`
2. `minikube ssh docker pull 192.168.49.1:5000/bmon-odds/server`
3. `minikube ssh docker tag 192.168.49.1:5000/bmon-odds/server bmon-odds/server:1.0.2`
4. TODO: rest of the steps
<!-- TODO: rest of the steps for the other projects -->

### Secrets

1. `./nginx/certs.sh` If you haven't run this yet.
2. `kubectl create secret generic ingress-tls-certs --from-file=./nginx/certs/nginx-cert.crt --from-file=./nginx/certs/nginx-cert.key`
3. `kubectl create secret generic postgresql-secrets --from-literal='postgres-password=somesecurepassword' --from-literal='password=somesecurepassword1'`

### Running Helm

Finally:
1.  ```
	helm install bmon-odds ./config/helm/bmon-odds \
	--atomic \
	--set "postgresql.primary.name=bmon_db" \
	--set "global.postgresql.auth.username=bmon_user" \
	--set "global.postgresql.auth.database=bmon_db" \
	--set "global.postgresql.auth.url=jdbc:postgresql://bmon-odds-postgresql:5432" \
	--set "bmonodds.server.schema.app=bmon_schema" \
	--set "bmonodds.server.schema.flyway=flyway_schema" \
	--set "bmonodds.gen.apiPrefix=/gen/api" \
	--set "bmonodds.gen.maxLiveGames=1" \
	--set "bmonodds.gen.dotnet.httpPorts=8081" \
	(for prod)
	--set "bmonodds.server.spring.profilesActive=prod" \
	--set "bmonodds.gen.dotnet.enviroment=Production"
	(or for debugging)
	--debug \
	--set "bmonodds.server.spring.profilesActive=dev" \
	--set "bmonodds.server.spring.debug=true" \
	--set "bmonodds.gen.dotnet.enviroment=Development"
	```
2. `echo "$(minikube ip) bmon-odds.local" | sudo tee -a /etc/hosts`
3. `curl --insecure bmon-odds.local/index.html` (should return a html body)
4. `curl --insecure bmon-odds.local/api/sports` (should return a json array)
5. `curl --insecure bmon-odds.local/gen/api/sports` (should return a iso timestamp)
6. If you're working in `.devcontainers` and want the service to be visible on windows make sure to add an entry in your hosts file and forward the port in vscode.

## Todo's
- [x] Add frontend/gen images
- [x] Add secrets
- [ ] Add TLS/Certbot
- [ ] Split front and backend ingress
- [x] Add Frontend project
- [x] Add oddsgen backend
- [ ] Add Github CI/CD 
- [ ] Split up to dev/staging/prod
- [ ] Setup review env's on PR's
- [ ] Switch to HA postgres
- [ ] Add various monitoring tools