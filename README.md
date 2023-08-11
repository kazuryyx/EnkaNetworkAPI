# What is this?

This is an API wrapper for https://enka.network in Java.
<br> Currently, we only have the option to use this API for JS, TS, PY hence why I made this :)

## Table of Contents
- [Requirements](https://github.com/kazuryyx/EnkaNetworkAPI/tree/main#requirements)
- [Installation](https://github.com/kazuryyx/EnkaNetworkAPI/tree/main#installation)
- [Base Usage](https://github.com/kazuryyx/EnkaNetworkAPI/tree/main#base-usage)
- [EnkaNetworkAPI class methods](https://github.com/kazuryyx/EnkaNetworkAPI/tree/main#class-methods-for-enkanetworkapi)
- [Retrieving user data](https://github.com/kazuryyx/EnkaNetworkAPI/tree/main#retrieving-user-data)
- [Some last words](https://github.com/kazuryyx/EnkaNetworkAPI/tree/main#last-words)

## Requirements
The API was written in Java 17, therefore you need [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
<br>
Java Knowledge is also required to understand how lambdas etc work.

## Installation
To install the API, you must use something like Maven or Gradle.
### Maven 
So, in your project path you should find a ``pom.xml`` file, simply add this to the ``<repositories>`` Tab.
```xml
<repository>
  <id>kazury-snapshots</id>
  <url>https://repo.kazury.me/snapshots</url>
</repository>
```
Once the repository is added, you also want to add the dependency.
```xml
<dependency>
  <groupId>me.kazury</groupId>
  <artifactId>EnkaNetworkAPI</artifactId>
  <version>3.8-SNAPSHOT</version>
</dependency>
```
For the version, you will always want to use ``currentGenshinVersion-SNAPSHOT``, when you use an old library version and try to fetch new data, it will not work and most likely throw you an exception.

## Base Usage
So, go ahead and create a new class in your project (if you have not already)
<br>After you have created a class, just add the following to your class. 
```java
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;

public class CreatedClass {
    private EnkaNetworkAPI api; // This field can also be final if you plan on not static abusing.

    public CreatedClass() {
        this.api = new EnkaNetworkAPI();
    }

    public static void main(String[] args) {
        // we do not want to static abuse here, so just create another instance, or perhaps you already 
        // have this in a class which is not static.
        new CreatedClass(); 
    }
}
```
### Adding a localization for default actions.
Note that English is the default localization, and that you do not need this if you're planning on using the API for English. 
<br>Otherwise, you can juse use the enum if you want a different language.
```java
public CreatedClass() {
    this.api = new EnkaNetworkAPI();
    this.api.setDefaultLocalization(GenshinLocalization.ENGLISH);
}
```

### Adding a user agent for HTTP requests
Currently, there is only one HTTP request for retrieving data by someones UID.
<br>It is adviced to call ``setUserAgent(String)`` on the created API class.
<br>As per [quote](https://api.enka.network/#/api?id=before-you-request): *Please set a custom User-Agent header with your requests so I can track them better and help you if needed.*
```java
public CreatedClass() {
    this.api = new EnkaNetworkAPI();
    this.api.setDefaultLocalization(GenshinLocalization.ENGLISH);
    this.api.setUserAgent("---"); // This could for example be your name, or anything else to get tracked well.
}
```

### Base URL for assets
You can also specify the path for assets, or use the default (enka.network/ui/IDENTIFIER.png).
```java
public CreatedClass() {
    this.api = new EnkaNetworkAPI();
    this.api.setDefaultLocalization(GenshinLocalization.ENGLISH);
    this.api.setUserAgent("---"); // This could for example be your name, or anything else to get tracked well.
    this.api.setDefaultUIPath("---"); // The path, this must link to a website which has the same object such as: "domain/IDENTIFIER.png"
}
```

### EnkaNetworkBuilder
You can also use a builder to build an instance of the api, if you don't want the steps above.
```java
public CreatedClass() {
    this.api = new EnkaNetworkBuilder()
        .setUserAgent("---")
        .setDefaultLocalization(GenshinLocalization.ENGLISH)
        .setDefaultUIPath("---")
        .build();
 }
```

## Class Methods for EnkaNetworkAPI 
| Method name                                              | Description                                                                                                                                                      | Version |
|----------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| ``fetchUser(long uid, Consumer<EnkaUserInformation>)``   | Fetches a user from the API and does the consumer action in some time later, caching is handled here and will cached until ttl expires.                          | 3.8     |
| ``fetchUser(String uid, Consumer<EnkaUserInformation>)`` | Same as above, but converts the string to a long so you don't have to.                                                                                           | 3.8     |
| ``getNamecard(long id)``                                 | Fetches a namecard information by the **namecard id**, the namecard id is provided where you need it (*As provided in the docs*)                                 | 3.8     |
| ``getCharacterData(String id)``                          | Fetches Character Data by a string, this is also provided where the id is needed.                                                                                | 3.8     |
| ``getCharacterData(long id)``                            | Same as the above method, just converts long to string so you don't have to.                                                                                     | 3.8     |
| ``getArtifactTotal(GenshinUserCharacter character)``     | Fetches the artifact sets a character has, this is map of ``<ArtifactName, Amount>``                                                                             | 3.8     |
| ``getIcon(String key)``                                  | Fetches an icon image for the key, the key also provided where you need it and some docs explain if you have to parse it yourself, or if the icon is just there. | 3.8     | 
| ``getAllCharacters()``                                   | Gets a list of all active genshin characters, which you can get their name from, data, etc                                                                       | 3.8     |
| ``getMaterial(String id)``                               | Get a material by the id, the id is provided where you need it.                                                                                                  | 3.8     |
| ``getMaterial(long id)``                                 | Same as the above method, just converts long to string so you don't have to.                                                                                     | 3.8     |

## Retrieving user data
So, with all this out of the way, I will now explain how to fetch data.
<br>To do this, we simply use the method ``fetchUser(id, ...)``:
```java
public CreatedClass() {
   this.api = new EnkaNetworkAPI();
   this.api.setDefaultLocalization(GenshinLocalization.ENGLISH);
   this.api.setUserAgent("---");

   this.api.fetchUser(722777337, (user) -> {
       final GenshinUserInformation info = GenshinUserInformation.fromEnkaUser(user);
   });
}
```
So, you may be wondering why we are calling ``fromEnkaUser``, the explanation on this is: ``EnkaUserInformation`` is a class with a lot of nullability and you will not be able to parse data here unless you take some time with this (and honestly who wants to do this lol)
<br>It would also require you to null check everything in this class
<br>``GenshinUserInformation`` is a class that is being parsed to include nullability, and handle it (so you don't have to).

### Retrieving character data
For this example, I will be using the normal API initializer, but you also use the Builder here.
```java
public CreatedClass() {
   this.api = new EnkaNetworkAPI();
   this.api.setDefaultLocalization(GenshinLocalization.ENGLISH);
   this.api.setUserAgent("---");

   this.api.fetchUser(722777337, (user) -> {
       final GenshinUserInformation info = GenshinUserInformation.fromEnkaUser(user);

       for (GenshinUserCharacter character : info.getCharacters()) {
          // This is a list of characters the user currently has in their showcase.

          // This list will be empty if the user does not have any characters in their showcase.
          // => This loop will not run.
       }
   });
}
```

#### Methods:
| Method name                | Description                                                                                                                                                  | Version |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| ``getEquippedWeapon()``    | Gets an object of this character's weapon, this includes info as level, ascension, refinement, stats, and the name of the weapon.                            | 3.8     |
| ``getArtifacts()``         | Gets a list of this character's artifacts, with the type (such as sands, goblet etc), level, main and sub stats, and the icon which you will need to parse.  | 3.8     |
| ``getConstellation()``     | Constellation from 0-6                                                                                                                                       | 3.8     |
| ``getFightProperties()``   | Fight properties, such as base atk, base hp, crit rate etc. Everything that is visible in genshin when viewing a character.                                  | 3.8     |
| ``getGameData()``          | The game data, such as the element of the character, skill order, constumes, side icon, name                                                                 | 3.8     |
| ``getCurrentExperience()`` | Gets the experience this character has for the next level, if none can be collected, then this will be -1.                                                   | 3.8     |
| ``getCurrentAscension()``  | Gets the current ascension of this character, each ascension is basically one "star" in the client menu.                                                     | 3.8     |
| ``getCurrentLevel()``      | Gets the current level of this character, from 0 to 90                                                                                                       | 3.8     |
| ``getFriendshipLevel()``   | Gets the current friendship level from 0 to 10                                                                                                               | 3.8     |

### Last words
1. I will keep this library always updated, expect an update within 1-2 days a new patch.
2. If you have any questions about the library you can find me on discord ``kazuryy``
3. If there is anything you want me to add here, then please write me a message.
