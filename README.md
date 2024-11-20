# Text Adventure Game

Welcome to the **Text Adventure Game**! This game is an interactive text-based adventure where you can explore different rooms, fight enemies, collect items, and talk to NPCs. Your goal is to explore the cave, defeat enemies, collect treasures, and ultimately reach the treasure room.

## Table of Contents
- [Game Overview](#game-overview)
- [Features](#features)
- [Commands](#commands)
- [How to Play](#how-to-play)
- [Structure](#structure)
- [License](#license)

## Game Overview
The game consists of several rooms with unique descriptions, items, NPCs, and combat elements. Players can navigate between rooms, interact with NPCs, collect items, and engage in combat with enemies. The game has a main objective: to collect the treasure in the Treasure Room.

## Features
- **Multiple Rooms**: Explore the entrance, forest, dungeon, and treasure room.
- **Items to Collect**: Find items like keys, potions, and swords throughout the rooms.
- **Combat System**: Fight enemies with basic attack or run away.
- **NPCs**: Talk to NPCs to get clues and interact with the story.
- **Inventory System**: Collect and use items to aid in your journey.
- **Health System**: Keep track of player health, which can be restored with potions.

## Commands
- **go [direction]**: Move to a different room. Valid directions are `north`, `south`, `east`, and `west`.
- **check inventory**: View the player's inventory.
- **collect [item]**: Collect an item found in the current room.
- **talk**: Talk to the NPC in the room (if available).
- **attack**: Engage in combat with the enemy in the dungeon.
- **run**: Run away from the enemy.
- **use [item]**: Use an item from the inventory (e.g., use a potion).

### Example Commands:
- `go north` – Move to the room to the north.
- `check inventory` – Show the items you have collected.
- `collect key` – Collect a key found in the room.
- `talk` – Speak to the NPC in the room.
- `attack` – Fight the enemy.
- `run` – Escape from the enemy.

## How to Play
1. **Start the game**: You start at the entrance of a mysterious cave.
2. **Explore the rooms**: Use the `go [direction]` command to move between rooms.
3. **Collect items**: Use `collect [item]` to pick up items that may help you on your journey.
4. **Engage with NPCs**: You can talk to NPCs for information.
5. **Combat**: If you encounter an enemy, you can choose to fight or run away.
6. **Use items**: Use collected items to heal or help in combat.
7. **Win the game**: Reach the Treasure Room and collect the treasure to win.

## Structure
The game is divided into several classes to organize the functionality:
- **TextAdventureGame**: Main class that runs the game.
- **Room**: Represents a room with a description, exits, items, and NPCs.
- **Player**: Represents the player, including inventory and health.
- **NPC**: Represents a non-playable character that the player can interact with.
- **Combat**: Handles combat between the player and enemies.

This README gives an overview of the game's mechanics, commands, and structure. 
