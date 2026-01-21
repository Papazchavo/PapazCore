@echo off
title BaranPlugin Builder
color 0a

echo.
echo  ╔═══════════════════════════════════════════════════════╗
echo  ║          BaranPlugin Derleniyor...                    ║
echo  ╚═══════════════════════════════════════════════════════╝
echo.

:: Maven kontrolü
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo [!] HATA: Maven bulunamadi!
    echo [*] Maven'i yukle: https://maven.apache.org/download.cgi
    pause
    exit
)

echo [*] Maven bulundu, derleme basliyor...
echo.

mvn clean package -DskipTests

if %errorlevel% equ 0 (
    echo.
    echo [+] Derleme basarili!
    echo [+] JAR dosyasi: target\BaranPlugin-1.0.0.jar
    echo.
    echo [*] Bu dosyayi sunucunun plugins klasorune kopyala.
) else (
    echo.
    echo [!] Derleme basarisiz! Hatalari kontrol et.
)

echo.
pause

