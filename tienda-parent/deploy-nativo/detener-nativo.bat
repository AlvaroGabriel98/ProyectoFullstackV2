@echo off
title Detener Microservicios - Modo Nativo
cls
echo ==========================================================
echo DETENIENDO MICROSERVICIOS - MODO NATIVO
echo ==========================================================
echo.
echo ADVERTENCIA: Este comando cerrara todos los procesos java.exe activos.
echo Si tienes otros programas Java abiertos, tambien podrian cerrarse.
echo.
pause
taskkill /F /IM java.exe
echo.
echo Procesos Java finalizados.
pause
