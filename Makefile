name = knife
registry = pasmod
tag = 0.0.1

.PHONY: build

 
build:
	docker build -t $(registry)/$(name) .
	docker tag $(registry)/$(name) $(registry)/$(name):$(tag)
 
run: stop
	docker run -it -p 4567:4567 --name=$(name) $(registry)/$(name) bash -l
 
start: stop
	docker run -d -p 4567:4567 --name=$(name) $(registry)/$(name)
 
stop:
	if docker inspect $(name) >/dev/null 2>&1; then docker stop $(name); docker rm $(name); fi

push:
	docker push $(docker_registry)/$(name)
