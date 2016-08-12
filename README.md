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
    "classes": [
      {
        "fields": [],
        "methods": [
          {
            "parameters": [
              {
                "type": "URL",
                "parameterName": "url"
              },
              {
                "type": "java.lang.String",
                "parameterName": "name"
              }
            ],
            "codeBlock": "/**\n * Some text\n *\n * @param url\n           some url\n * @param name\n           some name\n * @return the image at the specified URL\n * @see Image\n */\npublic Image getImage(URL url, java.lang.String name) {\n\n\t\ttry {\n\t\t\treturn getImage(new URL(url, name));\n\t\t} catch (MalformedURLException e) {\n\t\t\treturn null;\n\t\t}\n\t}\n",
            "sourceCode": "\n\t\ttry {\n\t\t\treturn getImage(new URL(url, name));\n\t\t} catch (MalformedURLException e) {\n\t\t\treturn null;\n\t\t}\n\t",
            "isStatic": false,
            "isPrivate": false,
            "isPublic": true,
            "methodName": "getImage"
          },
          {
            "parameters": [
              {
                "type": "java.lang.String[]",
                "parameterName": "args"
              }
            ],
            "codeBlock": "public static void main(java.lang.String[] args) {\n\n\t\tSystem.out.println(\"Hello World!\");\n\t}\n",
            "sourceCode": "\n\t\tSystem.out.println(\"Hello World!\");\n\t",
            "isStatic": true,
            "isPrivate": false,
            "isPublic": true,
            "methodName": "main"
          }
        ],
        "implementsInterfaces": false,
        "isStatic": false,
        "isPrivate": false,
        "isPublic": true,
        "packageName": "com.hmkcode",
        "className": "A",
        "comments": [
          {
            "type": "JavadocComment",
            "content": "\n\t * Some text\n\t *\n\t * @param url\n           some url\n\t * @param name\n           some name\n\t * @return the image at the specified URL\n\t * @see Image\n\t ",
            "range": {
              "begin": {
                "line": 3,
                "column": 5
              },
              "end": {
                "line": 12,
                "column": 8
              }
            }
          }
        ]
      },
      {
        "fields": [
          {
            "codeBlock": "private int a;\n",
            "type": "int",
            "fieldName": "a"
          }
        ],
        "methods": [
          {
            "parameters": [
              {
                "type": "java.util.Map",
                "parameterName": "unsortedMap"
              }
            ],
            "codeBlock": "public static java.util.Map sortByKey(java.util.Map unsortedMap) {\n\n\t\tMap sortedMap = new TreeMap(); // This is another comment\n\t\tsortedMap.putAll(unsortedMap); \n\t\t/*\n\t\t * Also a comment\n\t\t */\n\t\treturn sortedMap;\n\t}\n",
            "sourceCode": "\n\t\tMap sortedMap = new TreeMap(); // This is another comment\n\t\tsortedMap.putAll(unsortedMap); \n\t\t/*\n\t\t * Also a comment\n\t\t */\n\t\treturn sortedMap;\n\t",
            "isStatic": true,
            "isPrivate": false,
            "isPublic": true,
            "methodName": "sortByKey"
          }
        ],
        "implementsInterfaces": false,
        "isStatic": false,
        "isPrivate": false,
        "isPublic": true,
        "packageName": "com.hmkcode",
        "className": "B",
        "comments": [
          {
            "type": "LineComment",
            "content": " This is another comment",
            "range": {
              "begin": {
                "line": 7,
                "column": 40
              },
              "end": {
                "line": 7,
                "column": 66
              }
            }
          },
          {
            "type": "BlockComment",
            "content": "\n\t\t * Also a comment\n\t\t ",
            "range": {
              "begin": {
                "line": 9,
                "column": 9
              },
              "end": {
                "line": 11,
                "column": 12
              }
            }
          }
        ]
      }
    ],
    "state": "OK"
  }
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
