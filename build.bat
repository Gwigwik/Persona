@echo off
setlocal

set JDK=C:\Program Files\Java\jdk-25
set JAVAFX=D:\javafx\openjfx-25.0.1_windows-x64_bin-sdk
set BUILD_DIR=%~dp0build
set DIST_DIR=%~dp0dist
set RUNTIME_DIR=%~dp0runtime-persona
set MODULE_NAME=Persona
set VERSION=0.2

echo Creation du JAR modulaire...
cd build
"%JDK%\bin\jar.exe" --create --file Persona.jar -C ..\bin .
cd ..

REM Nettoyage
rd /s /q "%DIST_DIR%" 2>nul
rd /s /q "%RUNTIME_DIR%" 2>nul
mkdir "%DIST_DIR%"

REM Création du runtime minimal
"%JDK%\bin\jlink.exe" ^
--module-path "%JDK%\jmods;%JAVAFX%\javafx-jmods-25.0.2;%BUILD_DIR%" ^
--add-modules Persona,javafx.controls,javafx.fxml ^
--output "%RUNTIME_DIR%" ^
--compress=2 ^
--strip-debug ^
--no-header-files ^
--no-man-pages

REM Création du MSI autonome
"%JDK%\bin\jpackage.exe" ^
--name %MODULE_NAME% ^
--input "%BUILD_DIR%" ^
--main-jar %MODULE_NAME%.jar ^
--main-class app.Main ^
--type msi ^
--dest "%DIST_DIR%" ^
--app-version %VERSION% ^
--runtime-image "%RUNTIME_DIR%"