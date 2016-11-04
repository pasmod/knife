# Knife: Java Extractor API based on QDox and JavaParser
[![license](http://img.shields.io/:license-apache-blue.svg)](https://github.com/pasmod/knife/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/pasmod/knife.svg?branch=master)](https://travis-ci.org/pasmod/knife)

Knife is a web service based on [QDox](https://github.com/paul-hammant/qdox), [JavaParser](https://github.com/javaparser/javaparser) and Spark micro framework to parse and extract information from Java source code.

## Dependencies
- [Docker](https://www.docker.com/)
- [Gradle](https://gradle.org/) (only required for development and testing)

## How to setup the project:
``` bash
  cd knife
  make build
```

## How to start the web service:
To start the web service run the following command. The web service will be started on port 4567.
``` bash
  cd knife
  make start
```

## How create an Eclipse project:
If you want to develope new features, first clone the project and then run the following command. This will setup the project as an eclipse project and will install all required dependencies.
``` bash
  cd knife
  gradle cE e
```

## Routes
Curently there exist only one route. Use a tool such as [jq](https://stedolan.github.io/jq/) to pretty print the JSON outputs: ```sudo apt-get install jq```.

##### /extract (when request consists of one parsable class)
Extracts the available methods blocks from a java class
``` java
package de.hhu.knife;

/*
 * This is an example for a file that consists of one
 * class and does not have any syntax errors.
 */
public class example1 {
    int counter = 5;

    /**
     * Main method of the example
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(add(2, 3));
        System.out.println(add(2, 3));
        System.out.println(add(2, 3));
    }

    public static int add(final int a, final int b) {
        /*
         * Block Comment Line 2
         */
        return a + b;
    }

    public double divide(double a, double b) {
        return 1.0 * a / b; // Line Comment 1
    }

}
```
``` bash
curl --data-urlencode "class=`cat examples/example1.java`" 0.0.0.0:4567/extract | jq .
```
Result will be a json as follows:
```json
{
  "imports": [
    "static spark.Spark.port",
    "static spark.Spark.post",
    "java.io.StringReader",
    "java.util.List",
    "java.util.stream.Collectors",
    "org.slf4j.Logger",
    "org.slf4j.LoggerFactory",
    "com.thoughtworks.qdox.JavaProjectBuilder",
    "com.thoughtworks.qdox.parser.ParseException",
    "de.hhu.knife.beans.KJavaClass",
    "de.hhu.knife.beans.Segment",
    "de.hhu.knife.beans.State",
    "de.hhu.knife.mappers.Mapper",
    "de.hhu.knife.transformers.JsonTransformer"
  ],
  "state": "OK",
  "classes": [
    {
      "isPublic": true,
      "isPrivate": false,
      "isStatic": false,
      "comments": [
        {
          "range": {
            "end": {
              "column": 8,
              "line": 9
            },
            "begin": {
              "column": 5,
              "line": 5
            }
          },
          "content": "\n\t * Main method of the example\n\t *\n\t * @param args\n\t ",
          "type": "JavadocComment"
        },
        {
          "range": {
            "end": {
              "column": 12,
              "line": 21
            },
            "begin": {
              "column": 9,
              "line": 19
            }
          },
          "content": "\n         * Block Comment Line 2\n         ",
          "type": "BlockComment"
        },
        {
          "range": {
            "end": {
              "column": 46,
              "line": 27
            },
            "begin": {
              "column": 29,
              "line": 27
            }
          },
          "content": " Line Comment 1",
          "type": "LineComment"
        }
      ],
      "methods": [
        {
          "isPublic": true,
          "variables": [],
          "isPrivate": false,
          "isStatic": true,
          "name": "main",
          "sourceCode": "\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n    ",
          "codeBlock": "/**\n * Main method of the example\n *\n * @param args\n */\npublic static void main(java.lang.String[] args) {\n\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n    }\n",
          "parameters": [
            {
              "name": "args",
              "type": "String"
            }
          ]
        },
        {
          "isPublic": true,
          "variables": [],
          "isPrivate": false,
          "isStatic": true,
          "name": "add",
          "sourceCode": "\n        /*\n         * Block Comment Line 2\n         */\n        return a + b;\n    ",
          "codeBlock": "public static int add(int a, int b) {\n\n        /*\n         * Block Comment Line 2\n         */\n        return a + b;\n    }\n",
          "parameters": [
            {
              "name": "a",
              "type": "int"
            },
            {
              "name": "b",
              "type": "int"
            }
          ]
        },
        {
          "isPublic": true,
          "variables": [],
          "isPrivate": false,
          "isStatic": false,
          "name": "divide",
          "sourceCode": "\n        return 1.0 * a / b; // Line Comment 1\n    ",
          "codeBlock": "public double divide(double a, double b) {\n\n        return 1.0 * a / b; // Line Comment 1\n    }\n",
          "parameters": [
            {
              "name": "a",
              "type": "double"
            },
            {
              "name": "b",
              "type": "double"
            }
          ]
        }
      ],
      "name": "example1",
      "packageName": "de.hhu.knife",
      "fields": [
        {
          "name": "counter",
          "type": "int",
          "codeBlock": "int counter = 5;\n"
        }
      ]
    }
  ]
}


```

##### /extract (when request consists of one class with syntax errors)
In this example a "}" is missing at the end of the class!
``` java
package de.hhu.knife;

/*
 * This is an example of a file that consists of
 * one class and has a syntax error.
 */
public class Diva {

    public static void main(String[] args) {
        System.out.print("Hello World!");
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

##### /extract (when request consists of multiple parsable classes)
This request consists of two classes where both are parsable.
``` java
package de.hhu.knife;

/*
 * This is an example of a file that consists of two
 * classes and none of the classes have a syntax error.
 */
public class Example2 {
    int counter = 5;

    public static void main(String[] args) {
        /*
         * Block Comment 1 2 2
         */
        System.out.println(2);
        System.out.println(3);
    }
}

public class SecondClass {

    public void print(int a) {
        System.out.println(a);
        // Line Comment 2
    }

    public static void main(String[] args) {
        System.out.println(2);
    }
}
```
``` bash
curl --data-urlencode "class=`cat examples/example3.java`" 0.0.0.0:4567/extract | jq .
```
Result will be a json as follows:
``` json
{
  "imports": [],
  "state": "OK",
  "classes": [
    {
      "isPublic": true,
      "isPrivate": false,
      "isStatic": false,
      "comments": [
        {
          "range": {
            "end": {
              "column": 8,
              "line": 9
            },
            "begin": {
              "column": 5,
              "line": 5
            }
          },
          "content": "\n\t * Main method of the example\n\t *\n\t * @param args\n\t ",
          "type": "JavadocComment"
        },
        {
          "range": {
            "end": {
              "column": 12,
              "line": 21
            },
            "begin": {
              "column": 9,
              "line": 18
            }
          },
          "content": "\n         * Block Comment\n         * Line 2\n         ",
          "type": "BlockComment"
        }
      ],
      "methods": [
        {
          "isPublic": true,
          "variables": [],
          "isPrivate": false,
          "isStatic": true,
          "name": "main",
          "sourceCode": "\n        System.out.println(2);\n        System.out.println(3);\n    ",
          "codeBlock": "/**\n * Main method of the example\n *\n * @param args\n */\npublic static void main(java.lang.String[] args) {\n\n        System.out.println(2);\n        System.out.println(3);\n    }\n",
          "parameters": [
            {
              "name": "args",
              "type": "String"
            }
          ]
        },
        {
          "isPublic": true,
          "variables": [],
          "isPrivate": false,
          "isStatic": false,
          "name": "add",
          "sourceCode": "\n        /*\n         * Block Comment\n         * Line 2\n         */\n        return a + b;\n    ",
          "codeBlock": "public int add(int a, int b) {\n\n        /*\n         * Block Comment\n         * Line 2\n         */\n        return a + b;\n    }\n",
          "parameters": [
            {
              "name": "a",
              "type": "int"
            },
            {
              "name": "b",
              "type": "int"
            }
          ]
        }
      ],
      "name": "Example3",
      "packageName": "de.hhu.knife",
      "fields": [
        {
          "name": "counter",
          "type": "int",
          "codeBlock": "int counter = 5;\n"
        }
      ]
    },
    {
      "isPublic": true,
      "isPrivate": false,
      "isStatic": false,
      "comments": [
        {
          "range": {
            "end": {
              "column": 26,
              "line": 6
            },
            "begin": {
              "column": 9,
              "line": 6
            }
          },
          "content": " Line Comment 2",
          "type": "LineComment"
        },
        {
          "range": {
            "end": {
              "column": 8,
              "line": 13
            },
            "begin": {
              "column": 5,
              "line": 9
            }
          },
          "content": "\n\t * Main method of the example\n\t *\n\t * @param args\n\t ",
          "type": "JavadocComment"
        }
      ],
      "methods": [
        {
          "isPublic": true,
          "variables": [],
          "isPrivate": false,
          "isStatic": false,
          "name": "print",
          "sourceCode": "\n        System.out.println(a);\n        // Line Comment 2\n    ",
          "codeBlock": "public void print(int a) {\n\n        System.out.println(a);\n        // Line Comment 2\n    }\n",
          "parameters": [
            {
              "name": "a",
              "type": "int"
            }
          ]
        },
        {
          "isPublic": true,
          "variables": [],
          "isPrivate": false,
          "isStatic": true,
          "name": "main",
          "sourceCode": "\n        System.out.println(2);\n    ",
          "codeBlock": "/**\n * Main method of the example\n *\n * @param args\n */\npublic static void main(java.lang.String[] args) {\n\n        System.out.println(2);\n    }\n",
          "parameters": [
            {
              "name": "args",
              "type": "String"
            }
          ]
        }
      ],
      "name": "SecondClass",
      "packageName": "de.hhu.knife",
      "fields": []
    }
  ]
}


```
##### /extract (when request consists of multiple parsable and non-parsable classes)
This request consists of two clases where the second one has a syntax eror.
``` java
package de.hhu.knife;

/*
 * This is an example of a file consisting of two
 * classes and the second class has a syntax error.
 */
public class Example2 {
    int counter = 5;

    public static void main(String[] args) {
        /*
         * Block Comment 1 2 2
         */
        System.out.println(2);
        System.out.println(3);
    }
}

public class SecondClass {

    public void print(int a) {
        System.out.println(a);
        // Line Comment 2

    public static void main(String[] args) {
        System.out.println(2);
    }
}

```
``` bash
curl --data-urlencode "class=`cat examples/example4.java`" 0.0.0.0:4567/extract | jq .
```
Result will be a json as follows:
``` json
{
  "state": "PARSE_ERROR"
}
```
