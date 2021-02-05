@ECHO OFF
SETLOCAL

SET CURRENT_DIR=%~dp0
SET RAMDISK_APP_HOME=R:\develop\eclipse\workspace\volvox\app
MKDIR %RAMDISK_APP_HOME%

REM ----------------------------------------------------------------------
REM app
REM ----------------------------------------------------------------------
SET VOLVOX_APP_HOME=%USERPROFILE%\git\volvox\app
RMDIR /S /Q %VOLVOX_APP_HOME%\target
MKDIR %RAMDISK_APP_HOME%\target
MKLINK /D %VOLVOX_APP_HOME%\target %RAMDISK_APP_HOME%\target

REM ----------------------------------------------------------------------
REM application
REM ----------------------------------------------------------------------
SET VOLVOX_APPLICATION_HOME=%VOLVOX_APP_HOME%\application
RMDIR /S /Q %VOLVOX_APPLICATION_HOME%\target
MKDIR %RAMDISK_APP_HOME%\application\target
MKLINK /D %VOLVOX_APPLICATION_HOME%\target %RAMDISK_APP_HOME%\application\target

REM ----------------------------------------------------------------------
REM domain
REM ----------------------------------------------------------------------
SET VOLVOX_DOMAIN_HOME=%VOLVOX_APP_HOME%\domain
RMDIR /S /Q %VOLVOX_DOMAIN_HOME%\target
MKDIR %RAMDISK_APP_HOME%\domain\target
MKLINK /D %VOLVOX_DOMAIN_HOME%\target %RAMDISK_APP_HOME%\domain\target

REM ----------------------------------------------------------------------
REM infrastructure
REM ----------------------------------------------------------------------
SET VOLVOX_INFRASTRUCTURE_HOME=%VOLVOX_APP_HOME%\infrastructure
RMDIR /S /Q %VOLVOX_INFRASTRUCTURE_HOME%\target
MKDIR %RAMDISK_APP_HOME%\infrastructure\target
MKLINK /D %VOLVOX_INFRASTRUCTURE_HOME%\target %RAMDISK_APP_HOME%\infrastructure\target

ENDLOCAL
PAUSE
