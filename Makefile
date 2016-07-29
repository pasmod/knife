name = knife
registry = hub.docker.com

.PHONY: build

 
build:
	docker build -t $(registry)/$(name) $(BUILD_OPTS) .
 
run: stop
	docker run -it -p 4567:4567 --name=$(name) $(registry)/$(name) bash -l ./bin/knife
 
start: stop
	docker run -d -p 4567:4567 --name=$(name) $(registry)/$(name)
 
stop:
	if docker inspect $(name) >/dev/null 2>&1; then docker stop $(name); docker rm $(name); fi
