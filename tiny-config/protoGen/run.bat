set protoDir=.\protocol
set outDirServer=..\..\tiny-gs\src\main\java
set outDirClient=..\..\tiny-client\src\main\java
set tmpDir=tmp

IF NOT EXIST "tmp" MD "tmp"

::protoc.exe --java_out=./%tmpDir% *.proto
for /r %protoDir% %%i in (*.proto) do ( 
	protoc.exe -I=%protoDir% --proto_path =%protoDir% --java_out=.\%tmpDir% %%i
)
::Xcopy "./%tmpDir%" "%outDirServer%" /s /e /d /y
::Xcopy "./%tmpDir%" "%outDirClient%" /s /e /d /y

::rd /s /q ./%tmpDir%

pause