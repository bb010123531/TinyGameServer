set protoDir=./protocol
set outDirServer=../../tiny-gs/src/main/java
set outDirClient=../../tiny-client/src/main/java
set tmpDir=tmp

IF NOT EXIST "tmp" MD "tmp"

protoc.exe --java_out=./%tmpDir% %protoDir%/role.proto

Xcopy "./%tmpDir%" "%outDirServer%" /s /e /d /y
Xcopy "./%tmpDir%" "%outDirClient%" /s /e /d /y

::rd /s /q ./%tmpDir%

pause