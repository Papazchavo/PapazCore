<p align="center">
  <img src="https://i.imgur.com/JkXwBqY.png" alt="PapazCore Logo" width="400"/>
</p>

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
  <img src="https://img.shields.io/badge/Minecraft-1.20+-green?style=for-the-badge&logo=minecraft" alt="Minecraft"/>
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk" alt="Java"/>
  <img src="https://img.shields.io/badge/Spigot-Paper-yellow?style=for-the-badge" alt="Spigot"/>
  <img src="https://img.shields.io/badge/Dil-Türkçe-red?style=for-the-badge" alt="Türkçe"/>
</p>

<p align="center">
  <img src="https://img.shields.io/github/license/Papazchavo/PapazCore?style=flat-square" alt="License"/>
  <img src="https://img.shields.io/github/stars/Papazchavo/PapazCore?style=flat-square" alt="Stars"/>
  <img src="https://img.shields.io/github/forks/Papazchavo/PapazCore?style=flat-square" alt="Forks"/>
  <img src="https://img.shields.io/github/issues/Papazchavo/PapazCore?style=flat-square" alt="Issues"/>
</p>

---

## 📸 Ekran Görüntüleri

<p align="center">
  <img src="https://i.imgur.com/8qYxWvN.png" alt="Scoreboard" width="300"/>
  <img src="https://i.imgur.com/L3mKz9X.png" alt="Market GUI" width="300"/>
</p>

<p align="center">
  <img src="https://i.imgur.com/QHj5CXs.png" alt="Welcome Message" width="600"/>
</p>

---

## ✨ Özellikler

<table>
<tr>
<td width="50%">

### 🏠 Ev Sistemi
- Birden fazla ev kaydetme
- Kolay ışınlanma
- Ev listeleme ve silme

### 📍 Spawn Sistemi  
- Spawn noktası ayarlama
- Gecikmeli ışınlanma
- Hareket kontrolü

### 🔀 TPA Sistemi
- Oyuncuya ışınlanma isteği
- Kabul/Reddet sistemi
- Otomatik zaman aşımı

### 💰 Ekonomi Sistemi
- Başlangıç parası
- Para transferi
- Bakiye kontrolü

</td>
<td width="50%">

### 🎁 Kit Sistemi
- Başlangıç kiti (herkes)
- Savaşçı kiti (VIP)
- Elmas kiti (Admin)
- Bekleme süreleri

### 📈 Seviye Sistemi
- Maden kazarak XP kazan
- Mob öldürerek XP kazan
- Seviye atlama ödülleri

### 🏪 GUI Market
- Kolay alışveriş
- Özelleştirilebilir fiyatlar
- Görsel arayüz

### ⚔️ PvP Sistemi
- PvP aç/kapa
- Öldürme ödülleri
- İstatistikler

</td>
</tr>
</table>

### 🌟 Ekstra Özellikler

| Özellik | Açıklama |
|---------|----------|
| 👋 Hoşgeldin Sistemi | Yeni oyunculara özel karşılama ve başlangıç eşyaları |
| 💬 Sohbet Formatı | VIP/Admin/Mod rozetli özel sohbet formatı |
| 🚫 Reklam Engelleme | Otomatik reklam engelleme sistemi |
| 🛡️ Spawn Koruması | Spawn bölgesinde PvP koruması |
| 📢 Otomatik Mesajlar | Belirli aralıklarla otomatik duyurular |
| 📊 Scoreboard | Anlık bilgi gösteren yan panel |
| ✨ Özel Efektler | Elmas bulma, altın elma yeme efektleri |

---

## 📥 Kurulum

### Hızlı Kurulum

```bash
# 1. Repoyu klonla
git clone https://github.com/Papazchavo/PapazCore.git

# 2. Klasöre gir
cd PapazCore

# 3. Derle (Maven gerekli)
mvn clean package

# 4. JAR dosyasını al
# target/PapazCore-1.0.0.jar
```

### Windows Kullanıcıları İçin

1. **`DERLE.bat`** dosyasına çift tıkla
2. `target/PapazCore-1.0.0.jar` dosyasını al
3. Sunucunun `plugins` klasörüne kopyala
4. Sunucuyu yeniden başlat ✅

### Gereksinimler

| Program | Versiyon | İndirme Linki |
|---------|----------|---------------|
| Java | 17+ | [Adoptium](https://adoptium.net/) |
| Maven | 3.6+ | [Apache Maven](https://maven.apache.org/) |
| Sunucu | Spigot/Paper 1.20+ | [PaperMC](https://papermc.io/) |

---

## 📋 Komutlar

### 👤 Oyuncu Komutları

| Komut | Açıklama | Kullanım |
|-------|----------|----------|
| `/spawn` | Spawn'a ışınlan | `/spawn` |
| `/sethome` | Ev kaydet | `/sethome [isim]` |
| `/home` | Eve ışınlan | `/home [isim]` |
| `/delhome` | Evi sil | `/delhome [isim]` |
| `/homes` | Evlerini listele | `/homes` |
| `/tpa` | Işınlanma isteği | `/tpa <oyuncu>` |
| `/tpkabul` | İsteği kabul et | `/tpkabul` |
| `/tpreddet` | İsteği reddet | `/tpreddet` |
| `/para` | Bakiyeni gör | `/para [oyuncu]` |
| `/paragonder` | Para gönder | `/paragonder <oyuncu> <miktar>` |
| `/kit` | Kit al | `/kit [baslangic/savasci/elmas]` |
| `/seviye` | Seviyeni gör | `/seviye [oyuncu]` |
| `/stats` | İstatistikler | `/stats [oyuncu]` |
| `/pvp` | PvP aç/kapa | `/pvp` |
| `/market` | Marketi aç | `/market` |

### 👑 Admin Komutları

| Komut | Açıklama | Yetki |
|-------|----------|-------|
| `/setspawn` | Spawn ayarla | `papaz.admin` |
| `/fly` | Uçuş modu | `papaz.fly` |
| `/heal` | İyileştir | `papaz.heal` |
| `/feed` | Doyur | `papaz.feed` |
| `/gm` | Gamemode | `papaz.gamemode` |
| `/invsee` | Envanter gör | `papaz.invsee` |
| `/broadcast` | Duyuru | `papaz.broadcast` |

---

## ⚙️ Yapılandırma

### config.yml

```yaml
# Sunucu Bilgileri
sunucu:
  isim: "&6&lPapaz's Server"
  prefix: "&8[&6&l★&8] &e"
  discord: "discord.gg/sunucun"

# Ekonomi
ekonomi:
  para-birimi: "Coin"
  baslangic-parasi: 1000

# Market Fiyatları
market:
  elmas:
    fiyat: 100
  demir:
    fiyat: 25
  # ... daha fazlası config.yml'de
```

### messages.yml

Tüm mesajlar Türkçe ve tamamen özelleştirilebilir!

---

## 🔑 Yetkiler

### Admin Yetkileri

| Yetki | Açıklama |
|-------|----------|
| `papaz.admin` | Tüm admin komutları |
| `papaz.fly` | Uçuş komutu |
| `papaz.heal` | İyileştirme |
| `papaz.feed` | Doyurma |
| `papaz.gamemode` | Gamemode değiştirme |
| `papaz.invsee` | Envanter görme |
| `papaz.broadcast` | Duyuru yapma |

### VIP Yetkileri

| Yetki | Açıklama |
|-------|----------|
| `papaz.kit.vip` | Savaşçı kiti |
| `papaz.vip` | VIP sohbet rozeti |

### Mod Yetkileri

| Yetki | Açıklama |
|-------|----------|
| `papaz.mod` | Mod sohbet rozeti |

---

## 🛠️ Derleme

```bash
# Repoyu klonla
git clone https://github.com/Papazchavo/PapazCore.git
cd PapazCore

# Maven ile derle
mvn clean package

# JAR dosyası burada:
# target/PapazCore-1.0.0.jar
```

---

## 📝 Lisans

Bu proje [MIT Lisansı](LICENSE) altında lisanslanmıştır.

---

## 🤝 Katkıda Bulunma

1. Bu repoyu fork'layın
2. Feature branch oluşturun (`git checkout -b feature/yeniOzellik`)
3. Değişikliklerinizi commit'leyin (`git commit -m 'Yeni özellik eklendi'`)
4. Branch'inizi push'layın (`git push origin feature/yeniOzellik`)
5. Pull Request açın

---

## 📞 İletişim & Destek

- 🐛 **Bug Bildirimi:** [Issues](https://github.com/Papazchavo/PapazCore/issues)
- 💬 **Discord:** discord.gg/sunucun
- ⭐ **Beğendiysen:** Yıldız vermeyi unutma!

---

<p align="center">
  <b>Papaz tarafından ❤️ ile yapıldı</b><br>
  <i>Türk Minecraft topluluğu için</i>
</p>

<p align="center">
  <img src="https://i.imgur.com/mVsBY1Y.png" alt="Footer" width="600"/>
</p>
