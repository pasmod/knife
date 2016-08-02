# Knife: Java Extractor API based on QDox
[![license](http://img.shields.io/:license-apache-blue.svg)](https://github.com/pasmod/knife/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/pasmod/knife.svg?branch=master)](https://travis-ci.org/pasmod/knife)

Knife is a web service based on QDox and Spark micro framework to parse and extract information from Java source code.

## Dependencies
- [Docker](https://www.docker.com/)
- [Gradle](https://gradle.org/) (only required for development and testing)

## How to setup the project:
``` bash
  cd knife
  make build
```

## How to start the service:
``` bash
  cd knife
  make start
```

## How create an Eclipse project:
``` bash
  gradle cE e
```

## Features (Routes)
This service provides several routes for extracting information from Java source code. To run the examples first start the web service and then enter the commands in another terminal. Use a tool such as [jq](https://stedolan.github.io/jq/) to pretty print the JSON outputs: ```sudo apt-get install jq``` 

##### /extract (when code is parsable)
Extracts the available methods blocks from a java class
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
curl --data-urlencode "class=`cat examples/example1.java`" 0.0.0.0:4567/extract | jq .
```
Result will be a json as follows:
```json
{
  "state": "OK",
  "classes": [
    {
      "className": "Example",
      "packageName": "",
      "isPublic": true,
      "isPrivate": false,
      "isStatic": false,
      "implementsInterfaces": false,
      "methods": [
        {
          "methodName": "main",
          "isPublic": true,
          "isPrivate": false,
          "isStatic": true,
          "codeBlock": "public static void main(java.lang.String[] args) {\n\n\t\tSystem.out.println(add(2, 3));\n\t}\n",
          "parameters": [
            {
              "parameterName": "args",
              "type": "java.lang.String[]"
            }
          ]
        },
        {
          "methodName": "add",
          "isPublic": true,
          "isPrivate": false,
          "isStatic": true,
          "codeBlock": "public static int add(int a, int b) {\n\n\t\treturn a + b;\n\t}\n",
          "parameters": [
            {
              "parameterName": "a",
              "type": "int"
            },
            {
              "parameterName": "b",
              "type": "int"
            }
          ]
        }
      ],
      "fields": [
        {
          "fieldName": "counter",
          "type": "int",
          "codeBlock": "int counter = 5;\n"
        }
      ]
    },
    {
      "className": "SecondClass",
      "packageName": "",
      "isPublic": true,
      "isPrivate": false,
      "isStatic": false,
      "implementsInterfaces": false,
      "methods": [
        {
          "methodName": "print",
          "isPublic": true,
          "isPrivate": false,
          "isStatic": false,
          "codeBlock": "public void print(int a) {\n\n\t\tSystem.out.println(a);\n\t}\n",
          "parameters": [
            {
              "parameterName": "a",
              "type": "int"
            }
          ]
        }
      ],
      "fields": []
    }
  ]
}
```

##### /extract (when code is not parsable)
In this example a "}" is missing at the end of the class!
``` java
import java.util.Scanner;

public class Diva {

    public static void main(String[] args) {
        int m;
    }
```
``` bash
curl --data-urlencode "class=`cat examples/example2.java`" 0.0.0.0:4567/extract | jq .
```
Result will be a json as follows:
``` json
{
  "state": "PARSE_ERROR"
}
```
