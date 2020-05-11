@ECHO OFF
cd CoolPool/ && ..\gradlew.bat clean shadowJar javadoc && java -jar build/libs/PoolProject.jar && cd ../