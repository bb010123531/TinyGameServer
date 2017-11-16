
IF NOT EXIST "tmp" MD "tmp"

set inputFile=db.xml
set outDir=tmp
set outDirConfig=..\..\tiny-gs\src\main\java\tiny\

::应该不用删除之前创建的文件
::del %JAVA_TARGET_PATH%\*.* /f /s /q

java -Dfile.encoding=utf-8 -jar dbGen.jar %inputFile% %outDir% tiny

::Xcopy "./%tmpDir%" "%outDirServer%" /s /e /d /y
::Xcopy "./%tmpDir%" "%outDirClient%" /s /e /d /y
Xcopy "./%outDir%" "%outDirConfig%" /s /e /d /y

::rd %tmpDir% /s /q

echo 数据库bean生成完毕。

pause