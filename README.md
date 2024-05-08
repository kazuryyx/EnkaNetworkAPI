# What is this?

This is an API wrapper for https://enka.network in Java.
<br> At the time of writing, we only had the option to use a Wrapper for JS, TS, PY hence why I made this :)

<details>
<summary>Version Change Log</summary>

> Update - 08/05/2024 - Library Version: 4.6 | Honkai: Star Rail Update
> - Added new 2.2 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/e97b3a2b14bfa68df50472121c0920a7554dc69c>)
- New Methods:
  - ``SRUserCharacter#getTreeElements``
  - ``SRUserCharacter#getProps``
  - ``SRUserInformation#getMusicCount``
  - ``SRUserInformation#getRelicCount``
  - ``SRUserInformation#getBookCount``
  - ``SRUserInformation#getPureFictionLevel``
  - ``SRUserInformation#getPureFictionStars``
  - ``SRRelic#getSetId``
  - ``SRLightcone#getId``
  - ``SRLightcone#getGameData``
- New Caches: ``HONKAI_META``, ``HONKAI_LIGHTCONES``

> Update - 24/04/2024 - Library Version: 4.6 | Genshin Impact Update
- Added new 4.6 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/f8f0b4a574c53df32292c5aa45bb872305ef8700>)
- Method deprecations:
    - ``SRCharacterData#getPath``. Use ``SRCharacterData#getCharacterPath`` instead
- New Methods & Changes:
    - ``EnkaCaches#getProfileIcon(Pair)`` -> ``EnkaCaches#getProfileIcon(long)``
    - ``GenshinUserWeapon#getId``
    - ``GenshinArtifact#getAscensionData``
    - ``GenshinUserCharacter#getAscensionData``
    - ``GenshinUserWeapon#getAscensionData``
- Adds support for calculating character, weapon mats & relic experience. See [the docs](<https://enka-docs.kazury.me/api/material-calculation>) for example.
- Adds support for blocking caches. See [the docs](<https://enka-docs.kazury.me/api/getting-started#blocking-specific-caches>) for example.
- Several bug fixes introduced in previous version & during changes

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
  
**Note:** There have been other changes before 4.3, however I will not include them here. I will update this when a new version drops
</details>

## As of version 4.2, all docs have been rewritten. For Installation and examples please refer to http://enka-docs.kazury.me.

### Last words
1. I will keep this library always updated, expect an update within 1-2 days a new patch.
2. If you have any questions about the library you can find me on discord ``kazuryy`` or [this discord server](https://discord.gg/CuXPVGJDhk) under the ``𝖩𝖠𝖵𝖠┃enkanetworkapi`` channel.
3. If there is anything you want me to add here, then please write me a message.
