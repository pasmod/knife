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
  "classes": [
    {
      "fields": [
        {
          "codeBlock": "int counter = 5;\n",
          "type": "int",
          "name": "counter"
        }
      ],
      "packageName": "de.hhu.knife",
      "name": "example1",
      "methods": [
        {
          "parameters": [
            {
              "type": "String",
              "name": "args"
            }
          ],
          "codeBlock": "/**\n * Main method of the example\n *\n * @param args\n */\npublic static void main(java.lang.String[] args) {\n\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n    }\n",
          "sourceCode": "\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n        System.out.println(add(2, 3));\n    ",
          "name": "main",
          "isStatic": true,
          "isPrivate": false,
          "isPublic": true
        },
        {
          "parameters": [
            {
              "type": "int",
              "name": "a"
            },
            {
              "type": "int",
              "name": "b"
            }
          ],
          "codeBlock": "public static int add(int a, int b) {\n\n        /*\n         * Block Comment\n         * Line 2\n         */\n        return a + b;\n    }\n",
          "sourceCode": "\n        /*\n         * Block Comment\n         * Line 2\n         */\n        return a + b;\n    ",
          "name": "add",
          "isStatic": true,
          "isPrivate": false,
          "isPublic": true
        },
        {
          "parameters": [
            {
              "type": "double",
              "name": "a"
            },
            {
              "type": "double",
              "name": "b"
            }
          ],
          "codeBlock": "public double divide(double a, double b) {\n\n        return 1.0 * a / b; // Line Comment 1\n    }\n",
          "sourceCode": "\n        return 1.0 * a / b; // Line Comment 1\n    ",
          "name": "divide",
          "isStatic": false,
          "isPrivate": false,
          "isPublic": true
        }
      ],
      "comments": [
        {
          "type": "JavadocComment",
          "content": "\n\t * Main method of the example\n\t *\n\t * @param args\n\t ",
          "range": {
            "begin": {
              "line": 5,
              "column": 5
            },
            "end": {
              "line": 9,
              "column": 8
            }
          }
        },
        {
          "type": "BlockComment",
          "content": "\n         * Block Comment\n         * Line 2\n         ",
          "range": {
            "begin": {
              "line": 19,
              "column": 9
            },
            "end": {
              "line": 22,
              "column": 12
            }
          }
        },
        {
          "type": "LineComment",
          "content": " Line Comment 1",
          "range": {
            "begin": {
              "line": 28,
              "column": 29
            },
            "end": {
              "line": 28,
              "column": 46
            }
          }
        }
      ],
      "isStatic": false,
      "isPrivate": false,
      "isPublic": true
    }
  ],
  "state": "OK"
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
curl --data-urlencode "class=`cat examples/example2.java`" 0.0.0.0:4567/extract | jq .
```
Result will be a json as follows:
``` json
{
  {
  "classes": [
    {
      "fields": [
        {
          "codeBlock": "int counter = 5;\n",
          "type": "int",
          "name": "counter"
        }
      ],
      "packageName": "de.hhu.knife",
      "name": "Example2",
      "methods": [
        {
          "parameters": [
            {
              "type": "String",
              "name": "args"
            }
          ],
          "codeBlock": "/**\n * Main method of the example\n *\n * @param args\n */\npublic static void main(java.lang.String[] args) {\n\n        System.out.println(2);\n        System.out.println(3);\n    }\n",
          "sourceCode": "\n        System.out.println(2);\n        System.out.println(3);\n    ",
          "name": "main",
          "isStatic": true,
          "isPrivate": false,
          "isPublic": true
        },
        {
          "parameters": [
            {
              "type": "int",
              "name": "a"
            },
            {
              "type": "int",
              "name": "b"
            }
          ],
          "codeBlock": "public int add(int a, int b) {\n\n        /*\n         * Block Comment\n         * Line 2\n         */\n        return a + b;\n    }\n",
          "sourceCode": "\n        /*\n         * Block Comment\n         * Line 2\n         */\n        return a + b;\n    ",
          "name": "add",
          "isStatic": false,
          "isPrivate": false,
          "isPublic": true
        }
      ],
      "comments": [
        {
          "type": "JavadocComment",
          "content": "\n\t * Main method of the example\n\t *\n\t * @param args\n\t ",
          "range": {
            "begin": {
              "line": 5,
              "column": 5
            },
            "end": {
              "line": 9,
              "column": 8
            }
          }
        },
        {
          "type": "BlockComment",
          "content": "\n         * Block Comment\n         * Line 2\n         ",
          "range": {
            "begin": {
              "line": 18,
              "column": 9
            },
            "end": {
              "line": 21,
              "column": 12
            }
          }
        }
      ],
      "isStatic": false,
      "isPrivate": false,
      "isPublic": true
    },
    {
      "fields": [],
      "packageName": "de.hhu.knife",
      "name": "SecondClass",
      "methods": [
        {
          "parameters": [
            {
              "type": "int",
              "name": "a"
            }
          ],
          "codeBlock": "public void print(int a) {\n\n        System.out.println(a);\n        // Line Comment 2\n    }\n",
          "sourceCode": "\n        System.out.println(a);\n        // Line Comment 2\n    ",
          "name": "print",
          "isStatic": false,
          "isPrivate": false,
          "isPublic": true
        },
        {
          "parameters": [
            {
              "type": "String",
              "name": "args"
            }
          ],
          "codeBlock": "/**\n * Main method of the example\n *\n * @param args\n */\npublic static void main(java.lang.String[] args) {\n\n        System.out.println(2);\n    }\n",
          "sourceCode": "\n        System.out.println(2);\n    ",
          "name": "main",
          "isStatic": true,
          "isPrivate": false,
          "isPublic": true
        }
      ],
      "comments": [
        {
          "type": "LineComment",
          "content": " Line Comment 2",
          "range": {
            "begin": {
              "line": 6,
              "column": 9
            },
            "end": {
              "line": 6,
              "column": 26
            }
          }
        },
        {
          "type": "JavadocComment",
          "content": "\n\t * Main method of the example\n\t *\n\t * @param args\n\t ",
          "range": {
            "begin": {
              "line": 9,
              "column": 5
            },
            "end": {
              "line": 13,
              "column": 8
            }
          }
        }
      ],
      "isStatic": false,
      "isPrivate": false,
      "isPublic": true
    }
  ],
  "state": "OK"
}
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
curl --data-urlencode "class=`cat examples/example2.java`" 0.0.0.0:4567/extract | jq .
```
Result will be a json as follows:
``` json
{
  "state": "PARSE_ERROR"
}
```
