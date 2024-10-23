# What is this?

This is an API wrapper for https://enka.network in Java.
<br> At the time of writing, we only had the option to use a Wrapper for JS, TS, PY hence why I made this :)

<details>
<summary>Version Change Log</summary>

> Update - 10/10/2024 - Library Version: 5.1 | Honkai: Star Rail Update
- Added new 2.6 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/f1bdbcfc76623b0a4369f5366d39c6e26c5acb38>)
- Fixed GitLab Link for HSR

> Update - 10/10/2024 - Library Version: 5.1 | Honkai: Star Rail, Genshin Impact Update
- Added new 5.1 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/99751230d8d400706053d9c70a2d65d3e8fe2762>)
- Added new 2.5 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/c8c43bcad4d493b4dc94eb44f84b01b0a3e55254>) (I forgor last month)

> Update - 28/08/2024 - Library Version: 5.0 | Genshin Impact Update
- Added new 5.0 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/fa903eed179cd204db51a7cad7b0983a54219373>)
- Adds the Option to calculate Talent materials for a given character, this is on the ``GenshinCalculator`` object.
- **BREAKING CHANGE**: Every ``new EnkaNetworkAPI()`` call now requires ``build`` at the end (see docs for example). This will load caches once instead of previously loading them twice (not needed on EnkaNetworkBuilder class)
- New Methods:
  - ``GenshinUserInformation#getTheaterActs``
  - ``GenshinUserInformation#getTheaterMode``
  - ``GenshinUserInformation#getTheaterStars``
  - ``GenshinUserInformation#isShowAvatarTalent``
  - ``GenshinUserInformation#getFriendshipCharacters``
  - ``GenshinUserInformation#getAbyssStars``

**Note:** There have been other changes before these, only the latest 3 will show.
</details>

## As of version 4.2, all docs have been rewritten. For Installation and examples please refer to http://enka-docs.kazury.me.

### Last words
1. I will keep this library always updated, expect an update within 1-2 days a new patch.
2. If you have any questions about the library you can find me on discord ``kazuryy`` or [this discord server](https://discord.gg/CuXPVGJDhk) under the ``𝖩𝖠𝖵𝖠┃enkanetworkapi`` channel.
3. If there is anything you want me to add here, then please write me a message.
