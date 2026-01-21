@echo off
chcp 65001 >nul
title PapazCore Derleyici
color 0a

echo.
echo  ╔═══════════════════════════════════════════════════════╗
echo  ║         🎮 PAPAZCORE DERLEYICI 🎮                    ║
echo  ╚═══════════════════════════════════════════════════════╝
echo.

echo [1/3] Java kontrol ediliyor...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo  X HATA: Java bulunamadi! https://adoptium.net/
    pause & exit
)
echo       OK Java bulundu!

echo [2/3] Maven kontrol ediliyor...
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo  X HATA: Maven bulunamadi! https://maven.apache.org/
    pause & exit
)
echo       OK Maven bulundu!

echo [3/3] Plugin derleniyor...
call mvn clean package -DskipTests -q

if %errorlevel% equ 0 (
    echo.
    echo  ========================================
    echo     BASARILI! target\PapazCore-1.0.0.jar
    echo  ========================================
) else (
    echo  X DERLEME BASARISIZ!
)
pause
