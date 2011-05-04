Introduction
============
Logman is a tool for logger configuration manipulation of java application at runtime.
	
License
=======
[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

Features
========
Logman can access local running java processes by PID and manipulate their logging configuration if they using Logback for logging.

Manipulation in include these actions:

* Reloading initial config file.
* Setting a level for a logger
* Loading a new config file

Future
======
* Logman should support other common java logging libraries (such as log4j or commons logging).
* It should also can access remote java processes through jmx host:port syntax