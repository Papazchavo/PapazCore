```
    ____                        _____              
   / __ \____ _____  ____ _____/ ___/___  ________ 
  / /_/ / __ `/ __ \/ __ `/_  / /   / _ \/ ___/ _ \
 / ____/ /_/ / /_/ / /_/ / / / /___/ ___/ /  /  __/
/_/    \__,_/ .___/\__,_/ /__\____/\___/_/   \___/ 
           /_/                                      
```

<h1 align="center">🎮 PapazCore</h1>

<p align="center">
  <b>Türkçe Minecraft Sunucu Plugini</b><br>
  <i>Tüm temel özellikler tek pakette!</i>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-1.8--1.21-brightgreen?style=for-the-badge&logo=minecraft" alt="Minecraft"/>
  <img src="https://img.shields.io/badge/Java-8+-orange?style=for-the-badge&logo=openjdk" alt="Java"/>
  <img src="https://img.shields.io/badge/Spigot%20%7C%20Paper-Desteklenir-blue?style=for-the-badge" alt="Spigot"/>
  <img src="https://img.shields.io/badge/Dil-Türkçe%20🇹🇷-red?style=for-the-badge" alt="Türkçe"/>
</p>

---

# 📥 KURULUM REHBERİ

## 🔧 Adım 1: Gerekli Programları Yükle

### Java 8+ Kurulumu

1. **https://adoptium.net/** adresine git
2. **Temurin 8** veya **Temurin 17** indir
3. Kurulumu tamamla
4. Test et:
```bash
java -version
```

### Maven Kurulumu (Derleme için gerekli)

1. **https://maven.apache.org/download.cgi** adresine git
2. **Binary zip archive** indir
3. ZIP dosyasını çıkart (örnek: `C:\maven`)
4. **Sistem Değişkenlerine** `C:\maven\bin` ekle:
   - Windows: `Sistem Özellikleri > Ortam Değişkenleri > Path > Düzenle > Yeni`
5. CMD'yi yeniden aç ve test et:
```bash
mvn -version
```

---

## 📦 Adım 2: Plugini İndir

### Yöntem A: Git ile (Önerilen)
```bash
git clone https://github.com/Papazchavo/PapazCore.git
cd PapazCore
```

### Yöntem B: ZIP olarak
1. https://github.com/Papazchavo/PapazCore adresine git
2. Yeşil **Code** butonuna tıkla
3. **Download ZIP** seç
4. ZIP'i çıkart

---

## 🔨 Adım 3: Plugini Derle

### Windows için (Kolay)

1. `PapazCore` klasörüne git
2. **`DERLE.bat`** dosyasına çift tıkla
3. Bekle...
4. `target/PapazCore-1.0.0.jar` dosyası oluşacak ✅

### Komut Satırı ile

```bash
cd PapazCore
mvn clean package
```

Başarılı çıktı:
```
[INFO] BUILD SUCCESS
[INFO] --------------------------------
```

JAR dosyası: `target/PapazCore-1.0.0.jar`

---

## 🚀 Adım 4: Sunucuya Kur

1. `target/PapazCore-1.0.0.jar` dosyasını kopyala
2. Minecraft sunucunun `plugins` klasörüne yapıştır:
```
sunucu/
├── plugins/
│   └── PapazCore-1.0.0.jar  <-- BURAYA
├── server.jar
└── ...
```
3. Sunucuyu **yeniden başlat**
4. Konsola bak:
```
[PapazCore] ========================================
[PapazCore]   PapazCore v1.0.0 Aktif!
[PapazCore]   Gelistirici: Papaz
[PapazCore] ========================================
```

---

## ⚙️ Adım 5: Ayarları Özelleştir

Plugin kurulduktan sonra `plugins/PapazCore/` klasörü oluşur:

```
plugins/PapazCore/
├── config.yml      <-- Ana ayarlar
├── messages.yml    <-- Tüm mesajlar
├── data.yml        <-- Spawn ve oyuncu verileri
├── economy.yml     <-- Para verileri
├── homes.yml       <-- Ev verileri
└── levels.yml      <-- Seviye verileri
```

### config.yml Düzenleme

```yaml
# Sunucu Bilgileri - BUNLARI DEĞİŞTİR!
sunucu:
  isim: "&6&lSunucunun Adi"
  prefix: "&8[&6&l★&8] &e"
  discord: "discord.gg/senin-sunucun"
  website: "www.senin-siten.com"

# Başlangıç Parası
ekonomi:
  para-birimi: "Coin"
  baslangic-parasi: 1000

# Market Fiyatları
market:
  elmas:
    fiyat: 100
  demir:
    fiyat: 25
```

---

## 🎮 Adım 6: Oyunda Test Et

1. Sunucuya gir
2. Şu komutları dene:

| Komut | Beklenen Sonuç |
|-------|----------------|
| `/para` | Bakiyeni gösterir (1000 Coin) |
| `/kit` | Kit listesini gösterir |
| `/kit baslangic` | Başlangıç kitini verir |
| `/market` | Market GUI açılır |
| `/seviye` | Seviyeni gösterir |

3. Admin olarak test et (OP ver):
```
/op SenınNickın
```

| Admin Komut | Sonuç |
|-------------|-------|
| `/setspawn` | Spawn noktası ayarlar |
| `/fly` | Uçuş modu açar |
| `/heal` | Can ve açlık doldurur |
| `/gm 1` | Creative mod |

---

## ❓ Sorun Giderme

### "mvn komutu bulunamadı"
→ Maven'i PATH'e eklemedin. Adım 1'e dön.

### "Java bulunamadı"
→ Java 8+ yükle: https://adoptium.net/

### "Plugin yüklenmiyor"
→ Sunucu sürümünü kontrol et (Spigot/Paper 1.8-1.21 olmalı)

### "Derleme hatası"
→ İnternet bağlantını kontrol et, Maven bağımlılıkları indirmesi gerekiyor.

### "Komutlar çalışmıyor"
→ `/plugins` yaz, PapazCore yeşil mi kontrol et.

---

# ✨ ÖZELLİKLER

<table>
<tr>
<td width="50%">

### 🏠 Ev Sistemi
```
/sethome [isim]  → Ev kaydet
/home [isim]     → Eve ışınlan
/delhome [isim]  → Evi sil
/homes           → Evleri listele
```

### 📍 Spawn Sistemi  
```
/spawn           → Spawn'a ışınlan
/setspawn        → Spawn ayarla (Admin)
```

### 🔀 TPA Sistemi
```
/tpa <oyuncu>    → Işınlanma isteği
/tpkabul         → Kabul et
/tpreddet        → Reddet
```

### 💰 Ekonomi Sistemi
```
/para            → Bakiyeni gör
/paragonder      → Para transfer et
💵 Başlangıç: 1000 Coin
```

</td>
<td width="50%">

### 🎁 Kit Sistemi
```
/kit baslangic   → Herkes (1 saat)
/kit savasci     → VIP (6 saat)
/kit elmas       → Admin
```

### 📈 Seviye Sistemi
```
⛏️ Maden kaz    → +5 XP
⚔️ Mob öldür    → +10 XP
🎉 Seviye atla  → Para ödülü!
```

### 🏪 GUI Market
```
/market          → Dükkânı aç
💎 Elmas, Demir, Altın
🍎 Altın Elma, Tanrı Elması
```

### ⚔️ PvP Sistemi
```
/pvp             → Aç/Kapa
/stats           → İstatistikler
🏆 Öldürme ödülü: 50 Coin
```

</td>
</tr>
</table>

---

## 🎨 Ekstra Özellikler

| Özellik | Açıklama |
|:-------:|:---------|
| 👋 | **Hoşgeldin Sistemi** - Yeni oyunculara özel karşılama + başlangıç eşyaları |
| 💬 | **Sohbet Formatı** - `[Admin]`, `[VIP]`, `[Mod]`, `[Üye]` rozetleri |
| 🚫 | **Reklam Engelleme** - `.com`, `.net`, `discord` otomatik engellenir |
| 🛡️ | **Spawn Koruması** - Spawn çevresinde PvP koruması |
| 📢 | **Otomatik Mesajlar** - 5 dakikada bir rastgele duyuru |
| 📊 | **Scoreboard** - Anlık para, seviye, online sayısı |
| ✨ | **Özel Efektler** - Elmas bulunca, altın elma yiyince efektler |

---

# 📋 TÜM KOMUTLAR

## 👤 Oyuncu Komutları

| Komut | Açıklama |
|-------|----------|
| `/spawn` | Spawn'a ışınlan |
| `/sethome [isim]` | Ev kaydet |
| `/home [isim]` | Eve ışınlan |
| `/delhome [isim]` | Evi sil |
| `/homes` | Evlerini listele |
| `/tpa <oyuncu>` | Işınlanma isteği |
| `/tpkabul` | Kabul et |
| `/tpreddet` | Reddet |
| `/para` | Bakiyeni gör |
| `/paragonder <oyuncu> <miktar>` | Para gönder |
| `/kit` | Kit listesi |
| `/kit baslangic` | Başlangıç kiti |
| `/kit savasci` | Savaşçı kiti (VIP) |
| `/seviye` | Seviyeni gör |
| `/stats` | İstatistikler |
| `/pvp` | PvP aç/kapa |
| `/market` | Market GUI |

## 👑 Admin Komutları

| Komut | Yetki | Açıklama |
|-------|-------|----------|
| `/setspawn` | `papaz.admin` | Spawn ayarla |
| `/fly` | `papaz.fly` | Uçuş modu |
| `/heal` | `papaz.heal` | İyileştir |
| `/feed` | `papaz.feed` | Doyur |
| `/gm <0/1/2/3>` | `papaz.gamemode` | Gamemode |
| `/invsee <oyuncu>` | `papaz.invsee` | Envanter gör |
| `/broadcast <mesaj>` | `papaz.broadcast` | Duyuru |
| `/kit elmas` | `papaz.kit.admin` | Elmas kiti |

---

# 🔑 YETKİLER

## 👑 Admin Yetkileri

| Yetki | Açıklama |
|-------|----------|
| `papaz.admin` | Tüm admin komutları |
| `papaz.fly` | Uçuş |
| `papaz.heal` | İyileştirme |
| `papaz.feed` | Doyurma |
| `papaz.gamemode` | Gamemode |
| `papaz.invsee` | Envanter görme |
| `papaz.broadcast` | Duyuru |
| `papaz.kit.admin` | Admin kiti |

## ⭐ VIP Yetkileri

| Yetki | Açıklama |
|-------|----------|
| `papaz.kit.vip` | Savaşçı kiti |
| `papaz.vip` | VIP sohbet rozeti |

## 🛡️ Mod Yetkileri

| Yetki | Açıklama |
|-------|----------|
| `papaz.mod` | Mod sohbet rozeti |

### LuckPerms ile Yetki Verme

```bash
# VIP yetkisi ver
/lp user OyuncuAdı permission set papaz.vip true
/lp user OyuncuAdı permission set papaz.kit.vip true

# Mod yetkisi ver
/lp user OyuncuAdı permission set papaz.mod true

# Admin yetkisi ver
/lp user OyuncuAdı permission set papaz.admin true
```

---

# 🎮 SÜRÜM UYUMLULUĞU

```
┌────────────────────────────────────────┐
│  ✅ 1.8.x   - Tam Uyumlu               │
│  ✅ 1.9.x   - Tam Uyumlu               │
│  ✅ 1.10.x  - Tam Uyumlu               │
│  ✅ 1.11.x  - Tam Uyumlu               │
│  ✅ 1.12.x  - Tam Uyumlu               │
│  ✅ 1.13.x  - Tam Uyumlu               │
│  ✅ 1.14.x  - Tam Uyumlu               │
│  ✅ 1.15.x  - Tam Uyumlu               │
│  ✅ 1.16.x  - Tam Uyumlu               │
│  ✅ 1.17.x  - Tam Uyumlu               │
│  ✅ 1.18.x  - Tam Uyumlu               │
│  ✅ 1.19.x  - Tam Uyumlu               │
│  ✅ 1.20.x  - Tam Uyumlu               │
│  ✅ 1.21.x  - Tam Uyumlu               │
└────────────────────────────────────────┘
```

---

# 📞 DESTEK

| Platform | Link |
|----------|------|
| 🐛 Bug Bildirimi | [Issues](https://github.com/Papazchavo/PapazCore/issues) |
| 💬 Discord | discord.gg/devcode |
| ⭐ Beğendiysen | Yıldız ver! |

---

# 📜 LİSANS

Bu proje [MIT Lisansı](LICENSE) altında lisanslanmıştır.
Özgürce kullanabilir, değiştirebilir ve dağıtabilirsin.

---

<p align="center">
  <b>Papaz tarafından ❤️ ile yapıldı</b><br>
  <i>Türk Minecraft topluluğu için</i>
</p>

```
═══════════════════════════════════════════════════════════════
     ⭐ Beğendiysen yıldız vermeyi unutma! ⭐
═══════════════════════════════════════════════════════════════
```
