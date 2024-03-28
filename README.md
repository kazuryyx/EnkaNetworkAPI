# What is this?

This is an API wrapper for https://enka.network in Java.
<br> At the time of writing, we only had the option to use a Wrapper for JS, TS, PY hence why I made this :)

<details>
<summary>Version Change Log</summary>

> Update - 27/03/2024 - Library Version: 4.5 | Honkai: Star Rail Update
- Added new 2.1 [version data](https://github.com/kazuryyx/EnkaNetworkAPI/commit/c6a6bacaddc7fcd81d46f155f44c7671e5fb59e3)
- Fix simulated universe, platform for non-existing accounts
- New Methods & Changes:
    - **getValueType()** (in ``SRAppendProp``)
    - **getAvatarCutinFront()** -> **getSplashArt()** (in ``SRCharacterData``)
    - **getAvatarRoundIcon()** (in ``SRCharacterData``)
    - **getCharacterId()** (in ``SRCharacterData``)

> Update - 13/03/2024 - Library Version: 4.5 | Genshin Impact Update
- Added new 4.5 [version data](https://github.com/kazuryyx/EnkaNetworkAPI/commit/140f868f003e8588f1403098242e2db336547010)
- Fixed element-less traveler
- Method changes:
    - ``getNamecardUrl`` in ``GenshinNamecard`` is not ``Nullable`` anymore
    - ``getName`` in ``SRNameable``, ``GenshinNameable`` is not ``Nullable`` anymore. Not existing keys will return ``""``

> Update - 31/01/2024 - Library Version: 4.4 | Genshin Impact, Honkai: Star Rail Update
- Added new 4.4 [version data](https://github.com/kazuryyx/EnkaNetworkAPI/commit/e088e086634b432877935f7c94d9e8f1e26ec1e9)
- Added new 2.0 [version data](https://github.com/kazuryyx/EnkaNetworkAPI/commit/9ce1d36cdaf31de9522ba8d347d68c39e11b8e2c)
- New Methods:
    - **getMaxLevel()** (in ``GenshinUserCharacter``)

> Update - 20/12/2024 - Library Version: 4.3 | Genshin Impact Update
- Added new 4.3 [version data](https://github.com/kazuryyx/EnkaNetworkAPI/commit/2bc5b32acbe8a75025e46cd3654afdc70acec410)
- New Methods:
    - **getCharacterId()** (in ``GenshinCharacterData``)
    - **getRankLevel()** (in ``GenshinArtifact``)
    - **getMaxLevel()** (in ``GenshinArtifact``)
    - **getSetName()** (in ``GenshinArtifact``)

**Note:** There have been other changes before 4.3, however I will not include them here. I will update this when a new version drops
</details>

## As of version 4.2, all docs have been rewritten. For Installation and examples please refer to http://enka-docs.kazury.me.

### Last words
1. I will keep this library always updated, expect an update within 1-2 days a new patch.
2. If you have any questions about the library you can find me on discord ``kazuryy`` or [this discord server](https://discord.gg/CuXPVGJDhk) under the ``𝖩𝖠𝖵𝖠┃enkanetworkapi`` channel.
3. If there is anything you want me to add here, then please write me a message.
