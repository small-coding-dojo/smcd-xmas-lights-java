## Christmas Light Kata

cf. https://kata-log.rocks/christmas-lights-kata

## run everything from the command line

### get a junit and hamcrest library

e.g. use

- http://www.java2s.com/Code/JarDownload/junit/junit-4.11.jar.zip
- http://www.java2s.com/Code/JarDownload/hamcrest/hamcrest-core-1.3.jar.zip
  Unzip the files e.g. a new subdirectory ```lib```

### compile all main sources

```bash
    javac -d out/main src/main/java/**/*.java
```

### compile all test sources

```bash
    javac -d out/test -cp lib/junit-4.11.jar src/test/java/**/*.java
```

### run a tests

To run the test class ```com.github.smallCodingDojo.AppTest``` use the following command line:

```bash
    java -cp lib/junit-4.11.jar:lib/hamcrest-core-1.3.jar::out/main:out/test/  org.junit.runner.JUnitCore com.github.smallCodingDojo.AppTest
```
