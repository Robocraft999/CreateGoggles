# CreateGoggles

[![CurseForge](http://cf.way2muchnoise.eu/full_377835_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/create-goggles)
[![Issues](https://img.shields.io/github/issues/Robocraft999/CreateGoggles)](https://github.com/Robocraft999/CreateGoggles/issues)

Adds Goggle Helmets and Armored Backtanks for Create Mod

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### For Players

Download the latest JAR file from GitHub releases or from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/create-goggles) and drop it into your `mods` folder.

### For Modcreators (available from 0.5.1 and upwards)

(You need to install CreateGoggles to use the features of course)

#### Viewing goggle information for your custom helmets
You can use tags for your helmets to display the goggleinformation (in your resources folder, create the file `goggle.json` at `resources/data/creategoggles/tags/items`)
Here is an example:

```json
{
  "replace": false,
  "values": [
    "yourmodid:your_helmet_id"
  ]
}
```

#### Custom goggle helmet recipe

If you have a normal modded helmet e.g. copper and want to have a goggle helmet of it too

File: your_helmet_id.json (at `resources/data/yourmodid/recipes`):
```json
{
	"type":"creategoggles:crafting_nbt",
	"pattern":
	[
		"HG"
	],
	"key":
	{
		"H": {"item": "yourmodid:your_plain_helmet_id"},
		"G": {"item": "create:goggles"}
	},
	"result":{"item":"yourmodid:your_goggle_helmet_id"}
}
```