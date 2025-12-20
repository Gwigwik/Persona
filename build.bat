@echo off

set VERSION=1.0

REM Nettoyage des anciens builds
echo Suppression des anciens JAR et installateurs...
del /f /q build\Persona.jar 2>nul
del /f /q dist\Persona-*.msi 2>nul

REM Compilation du projet depuis Eclipse (bin -> jar)
echo Creation du JAR...
if not exist build mkdir build
"C:\Program Files\Java\jdk-25\bin\jar.exe" --create --file build\Persona.jar -C bin .

REM Generation de l'installateur MSI
echo Creation de l'installateur MSI...
if not exist dist mkdir dist
"C:\Program Files\Java\jdk-25\bin\jpackage.exe" ^
--name Persona ^
--input build ^
--main-jar Persona.jar ^
--main-class app.Main ^
--type msi ^
--dest dist ^
--app-version %VERSION% ^
--java-options "--module-path D:\javafx\openjfx-25.0.1_windows-x64_bin-sdk\javafx-sdk-25.0.1\lib --add-modules javafx.controls,javafx.fxml"