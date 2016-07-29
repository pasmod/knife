# Knife: Java Extractor API based on QDox
[![license](http://img.shields.io/:license-apache-blue.svg)](https://github.com/pasmod/knife/blob/master/LICENSE)

Knife is a web service based on QDox and Spark micro framework to parse and extract information from Java source code.

## How to setup the project:
``` bash
  cd knife
  make build
```

## How to start the service:
``` bash
  cd knife
  make run
  ./bin/knife
```
# Features (Routes)
This service provides several routes for extracting information from Java source code. To run the examples first start the web service and then enter the commands in another terminal.

##### /method/blocks
Extracts the available methods blocks from a java class
Example:
``` java
public class Example {
	public static void main(String[] args) {
		System.out.println(add(2, 3));
	}
	public static int add(final int a, final int b) {
		return a + b;
	}
}
```
``` bash
curl --data-urlencode "class=`cat examples/example1.java`" 0.0.0.0:4567/method/blocks
```
Result will be an array consisting of two methods:
``` java
[public static void main(java.lang.String[] args) {
		System.out.println(add(2, 3));
 },
 public static int add(int a, int b) {
		return a + b;
 }]
``` 
