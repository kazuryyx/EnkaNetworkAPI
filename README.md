# What is this?

This is an API wrapper for https://enka.network in Java.
<br> At the time of writing, we only had the option to use a Wrapper for JS, TS, PY hence why I made this :)

<details>
<summary>Version Change Log</summary>

> Update - 19/06/2024 - Library Version: 4.7 | Honkai: Star Rail Update
- Added new 2.3 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/ebb72fdf8b6dc762c7be512054a4a85e62fb00c8>)
- New Methods:
  - ``EnkaNetworkAPI#getSRLightconeData``
  - ``EnkaNetworkAPI#getAllSRLightcones``
  - ``SRLightconeData#getName``

> Update - 05/06/2024 - Library Version: 4.7 | Genshin Impact Update, Honkai: Star Rail Improvements
- Added new 4.7 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/f30b722bb6567d9101390f8bdd3bf8a66ccb5556>)
- New Methods:
  - ``SRUserInformation#getProfilePictureIdentifier``
  - ``EnkaNetworkAPI#fetchProfileData``
- Fixes a bug if relics were empty
- Adds missing Aventurine, Gallagher

> Update - 08/05/2024 - Library Version: 4.6 | Honkai: Star Rail Update
- Added new 2.2 [version data](<https://github.com/kazuryyx/EnkaNetworkAPI/commit/e97b3a2b14bfa68df50472121c0920a7554dc69c>)
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

**Note:** There have been other changes before 4.3, however I will not include them here. I will update this when a new version drops
</details>

## As of version 4.2, all docs have been rewritten. For Installation and examples please refer to http://enka-docs.kazury.me.

### Last words
1. I will keep this library always updated, expect an update within 1-2 days a new patch.
2. If you have any questions about the library you can find me on discord ``kazuryy`` or [this discord server](https://discord.gg/CuXPVGJDhk) under the ``𝖩𝖠𝖵𝖠┃enkanetworkapi`` channel.
3. If there is anything you want me to add here, then please write me a message.
