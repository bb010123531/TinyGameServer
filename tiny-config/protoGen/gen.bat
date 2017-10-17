@echo off

IF NOT EXIST "tmp" MD "tmp"

::协议文件路径, 最后不要跟“\”符号
set SOURCE_FOLDER=.\protocol
::如果文件中引用了别的proto文件，IMP_FOLDER是引用的proto文件的目录
set IMP_FOLDER=.\protocol
::Java编译器路径
set JAVA_COMPILER_PATH=.\protoc.exe
::Java文件生成路径, 最后不要跟“\”符号
set JAVA_TARGET_PATH=.\tmp

:: 服务端路径
set outDirServer=..\..\tiny-gs\src\main\java
:: 客户端路径
set outDirClient=..\..\tiny-client\src\main\java
:: 临时文件夹
set tmpDir=tmp


::删除之前创建的文件
del %JAVA_TARGET_PATH%\*.* /f /s /q


::遍历所有文件
for /f "delims=" %%i in ('dir /b "%SOURCE_FOLDER%\*.proto"') do (
    
    echo %JAVA_COMPILER_PATH% -I=%SOURCE_FOLDER%  --java_out=%JAVA_TARGET_PATH% %SOURCE_FOLDER%\%%i
    %JAVA_COMPILER_PATH% --proto_path=%IMP_FOLDER%  --java_out=%JAVA_TARGET_PATH% %SOURCE_FOLDER%\%%i
    
)

Xcopy "./%tmpDir%" "%outDirServer%" /s /e /d /y
Xcopy "./%tmpDir%" "%outDirClient%" /s /e /d /y

echo 协议生成完毕。

pause