# LobbyFly

[![Github All Releases](https://img.shields.io/github/downloads/NLDev/LobbyFly/total.svg)](https://github.com/NLDev/LobbyFly/) [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/NLDev/LobbyFly/master/LICENSE) [![Travis](https://img.shields.io/travis/rust-lang/rust.svg)](https://github.com/NLDev/LobbyFly) [![Release](https://img.shields.io/github/release/NLDev/LobbyFly.svg)](https://github.com/NLDev/LobbyFly/releases)

###A Spigot Plugin which enables fly for a player as long as he holds an item 

This plugin is tested on 1.8.X. It will enable fly for a player as long as he holds 
an specific item which is set in the config (Default: Netherstar)

If the Item ID is invalid the plugin uses 399 (NetherStar) instead without changing the config.
Also users with permission `lobbyfly.admin` and OPs get a notification that the ID is invalid

---
###Features:
- Specify item in config
- prevent errors on invalid items
- command support for `/lfly` and `/lobbyfly`
- help menu on `/lfly` and `/lfly help`
- option to change the prefix of the plugin (with color support)
- command to deny users (`/lfly deny (username)`)
- command to undeny users (`/lfly undeny (username)`)
- command to list denied users (`/lfly denylist`)
- command to see if a user is denied (`/lfly status (username)`)

-> SpigotMC Link here <- 

---
Note: this plugin was developed for the EpticMC.com server but I choosed to publish it 
