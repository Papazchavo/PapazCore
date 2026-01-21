@echo off
chcp 65001 >nul
title BaranPlugin Kolay Kurulum
color 0b

echo.
echo  ╔═══════════════════════════════════════════════════════╗
echo  ║         🎮 BARANPLUGIN KOLAY KURULUM 🎮              ║
echo  ╚═══════════════════════════════════════════════════════╝
echo.
echo  Bu script sana adim adim yardim edecek.
echo.
echo  ═══════════════════════════════════════════════════════
echo  GEREKLI PROGRAMLAR:
echo  ═══════════════════════════════════════════════════════
echo.
echo  [1] Java 17+    - https://adoptium.net/
echo  [2] Maven       - https://maven.apache.org/download.cgi
echo.
echo  ═══════════════════════════════════════════════════════
echo.

set /p choice="Programlar yuklu mu? (E/H): "

if /i "%choice%"=="E" goto derle
if /i "%choice%"=="e" goto derle

echo.
echo  Lutfen once programlari yukle:
echo.
echo  1. Java 17 indir: https://adoptium.net/
echo     - "Temurin 17" sec ve kur
echo.
echo  2. Maven indir: https://maven.apache.org/download.cgi
echo     - "Binary zip archive" indir
echo     - C:\maven klasorune cikart
echo     - Sistem degiskenlerine C:\maven\bin ekle
echo.
echo  Yukleme bittikten sonra bu scripti tekrar calistir.
echo.
pause
exit

:derle
echo.
echo  [*] Derleme basliyor...
echo.

call mvn clean package -DskipTests

if %errorlevel% equ 0 (
    echo.
    echo  ✅ BASARILI! Plugin derlendi.
    echo.
    
    if exist "target\BaranPlugin-1.0.0.jar" (
        echo  JAR dosyasi: target\BaranPlugin-1.0.0.jar
        echo.
        set /p sunucu="Sunucu plugins klasorunu gir (ornek: C:\sunucu\plugins): "
        
        if not "%sunucu%"=="" (
            copy "target\BaranPlugin-1.0.0.jar" "%sunucu%\" >nul 2>&1
            if %errorlevel% equ 0 (
                echo.
                echo  ✅ Plugin sunucuya kopyalandi!
                echo  Simdi sunucuyu yeniden baslat.
            ) else (
                echo.
                echo  ❌ Kopyalama basarisiz. Manuel olarak kopyala:
                echo  target\BaranPlugin-1.0.0.jar → %sunucu%\
            )
        )
    )
) else (
    echo.
    echo  ❌ Derleme basarisiz!
    echo  Java 17 ve Maven yuklu oldugundan emin ol.
)

echo.
pause

