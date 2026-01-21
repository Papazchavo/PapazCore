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
  <a href="#-özellikler">Özellikler</a> •
  <a href="#-kurulum">Kurulum</a> •
  <a href="#-komutlar">Komutlar</a> •
  <a href="#%EF%B8%8F-yapılandırma">Yapılandırma</a> •
  <a href="#-yetkiler">Yetkiler</a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-1.8--1.21-brightgreen?style=for-the-badge&logo=minecraft" alt="Minecraft"/>
  <img src="https://img.shields.io/badge/Java-8+-orange?style=for-the-badge&logo=openjdk" alt="Java"/>
  <img src="https://img.shields.io/badge/Spigot%20%7C%20Paper-Desteklenir-blue?style=for-the-badge" alt="Spigot"/>
  <img src="https://img.shields.io/badge/Dil-Türkçe%20🇹🇷-red?style=for-the-badge" alt="Türkçe"/>
</p>

<p align="center">
  <img src="https://img.shields.io/github/license/Papazchavo/PapazCore?style=flat-square" alt="License"/>
  <img src="https://img.shields.io/github/stars/Papazchavo/PapazCore?style=flat-square" alt="Stars"/>
  <img src="https://img.shields.io/github/forks/Papazchavo/PapazCore?style=flat-square" alt="Forks"/>
  <img src="https://img.shields.io/github/issues/Papazchavo/PapazCore?style=flat-square" alt="Issues"/>
  <img src="https://img.shields.io/github/v/release/Papazchavo/PapazCore?style=flat-square" alt="Release"/>
</p>

---

## 🌟 Neden PapazCore?

```
┌─────────────────────────────────────────────────────────────┐
│  ✅ Tek plugin, tüm temel özellikler                        │
│  ✅ %100 Türkçe mesajlar                                    │
│  ✅ 1.8 - 1.21 tüm sürümlerde çalışır                       │
│  ✅ Kolay kurulum ve yapılandırma                           │
│  ✅ Hafif ve performanslı                                   │
│  ✅ Açık kaynak ve ücretsiz                                 │
└─────────────────────────────────────────────────────────────┘
```

---

## ✨ Özellikler

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

## 📥 Kurulum

### 📋 Gereksinimler

| Program | Versiyon | Link |
|---------|----------|------|
| ☕ Java | 8+ | [Adoptium](https://adoptium.net/) |
| 🔧 Maven | 3.6+ | [Apache Maven](https://maven.apache.org/) |
| 🎮 Sunucu | Spigot/Paper 1.8-1.21 | [PaperMC](https://papermc.io/) |

### 🚀 Hızlı Kurulum

```bash
# 1️⃣ Repoyu klonla
git clone https://github.com/Papazchavo/PapazCore.git

# 2️⃣ Klasöre gir
cd PapazCore

# 3️⃣ Derle
mvn clean package

# 4️⃣ JAR dosyasını al → target/PapazCore-1.0.0.jar
```

### 🪟 Windows Kullanıcıları

1. `DERLE.bat` dosyasına **çift tıkla**
2. `target/PapazCore-1.0.0.jar` dosyasını al
3. Sunucunun `plugins` klasörüne kopyala
4. Sunucuyu yeniden başlat ✅

---

## 📋 Komutlar

### 👤 Oyuncu Komutları

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
| `/seviye` | Seviyeni gör |
| `/stats` | İstatistikler |
| `/pvp` | PvP aç/kapa |
| `/market` | Market GUI |

### 👑 Admin Komutları

| Komut | Yetki | Açıklama |
|-------|-------|----------|
| `/setspawn` | `papaz.admin` | Spawn ayarla |
| `/fly` | `papaz.fly` | Uçuş modu |
| `/heal` | `papaz.heal` | İyileştir |
| `/feed` | `papaz.feed` | Doyur |
| `/gm <0/1/2/3>` | `papaz.gamemode` | Gamemode |
| `/invsee <oyuncu>` | `papaz.invsee` | Envanter gör |
| `/broadcast <mesaj>` | `papaz.broadcast` | Duyuru |

---

## ⚙️ Yapılandırma

### 📄 config.yml

```yaml
# 🏷️ Sunucu Bilgileri
sunucu:
  isim: "&6&lPapaz's Server"
  prefix: "&8[&6&l★&8] &e"
  discord: "discord.gg/sunucun"

# 💰 Ekonomi
ekonomi:
  para-birimi: "Coin"
  baslangic-parasi: 1000

# 🏪 Market Fiyatları
market:
  elmas:
    fiyat: 100
  demir:
    fiyat: 25
  altin:
    fiyat: 50
```

### 📝 messages.yml
> Tüm mesajlar Türkçe ve %100 özelleştirilebilir!

---

## 🔑 Yetkiler

### 👑 Admin

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

### ⭐ VIP

| Yetki | Açıklama |
|-------|----------|
| `papaz.kit.vip` | Savaşçı kiti |
| `papaz.vip` | VIP sohbet rozeti |

### 🛡️ Mod

| Yetki | Açıklama |
|-------|----------|
| `papaz.mod` | Mod sohbet rozeti |

---

## 🔧 Sürüm Uyumluluğu

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

## 🤝 Katkıda Bulunma

```bash
# 1. Fork'la
# 2. Branch oluştur
git checkout -b feature/yeniOzellik

# 3. Commit yap
git commit -m "Yeni özellik eklendi"

# 4. Push et
git push origin feature/yeniOzellik

# 5. Pull Request aç
```

---

## 📞 Destek

| Platform | Link |
|----------|------|
| 🐛 Bug Bildirimi | [Issues](https://github.com/Papazchavo/PapazCore/issues) |
| 💬 Discord | discord.gg/sunucun |
| ⭐ Beğendiysen | Yıldız ver! |

---

## 📜 Lisans

Bu proje [MIT Lisansı](LICENSE) altında lisanslanmıştır.

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
