import java.util.Scanner;
import java.util.HashMap;

public class TextAdventureGame {
    static class Room {
        String description;
        HashMap<String, Room> exits = new HashMap<>();
        HashMap<String, String> items = new HashMap<>();
        NPC npc = null;

        Room(String description) {
            this.description = description;
        }

        void addExit(String direction, Room room) {
            exits.put(direction, room);
        }

        void addItem(String item, String description) {
            items.put(item, description);
        }

        void setNPC(NPC npc) {
            this.npc = npc;
        }
    }

    static class Player {
        String name;
        int health = 100;
        HashMap<String, String> inventory = new HashMap<>();

        Player(String name) {
            this.name = name;
        }

        void addItemToInventory(String item, String description) {
            inventory.put(item, description);
        }

        void checkInventory() {
            System.out.println("════════════════════════════════════════════════════");
            System.out.println("🧳 Your Inventory:");
            if (inventory.isEmpty()) {
                System.out.println("Your inventory is empty.");
            } else {
                inventory.forEach((item, description) -> System.out.println("🔹 " + item + ": " + description));
            }
            System.out.println("════════════════════════════════════════════════════");
        }
    }

    static class NPC {
        String name;
        String dialogue;

        NPC(String name, String dialogue) {
            this.name = name;
            this.dialogue = dialogue;
        }

        void talk() {
            System.out.println("💬 " + name + ": \"" + dialogue + "\"");
        }
    }

    static class Combat {
        static void fight(Player player) {
            int enemyHealth = 50;
            System.out.println("\n⚔️ A wild enemy appears! Prepare to fight!");
            Scanner scanner = new Scanner(System.in);

            while (player.health > 0 && enemyHealth > 0) {
                System.out.println("\nEnemy Health: ❤️ " + enemyHealth);
                System.out.println("Your Health: ❤️ " + player.health);
                System.out.println("What will you do? (attack/run)");
                String action = scanner.nextLine();

                if (action.equals("attack")) {
                    int damage = (int) (Math.random() * 20 + 1);
                    enemyHealth -= damage;
                    System.out.println("💥 You attacked the enemy for " + damage + " damage!");
                } else if (action.equals("run")) {
                    System.out.println("🏃‍♂️ You ran away from the enemy!");
                    break;
                }

                if (enemyHealth > 0) {
                    int enemyDamage = (int) (Math.random() * 15 + 1);
                    player.health -= enemyDamage;
                    System.out.println("🛡️ The enemy attacked you for " + enemyDamage + " damage!");
                }
            }

            if (player.health <= 0) {
                System.out.println("💀 Game Over! You were defeated.");
            } else if (enemyHealth <= 0) {
                System.out.println("🎉 You defeated the enemy!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("════════════════════════════════════════════════════");
        System.out.println("✨ Welcome to the Adventure Game! ✨");
        System.out.println("════════════════════════════════════════════════════");
        System.out.println("Type 'help' for commands.\n");

        Player player = new Player("Hero");

        // Rooms setup
        Room entrance = new Room("🚪 You are at the entrance of a mysterious cave.");
        Room forest = new Room("🌲 You are in a dense forest. The trees are thick.");
        Room dungeon = new Room("🏰 You have entered a dark and damp dungeon. The air is thick with tension.");
        Room treasureRoom = new Room("💎 You have entered the treasure room. The treasure is within reach!");

        entrance.addExit("north", forest);
        forest.addExit("south", entrance);
        forest.addExit("east", dungeon);
        dungeon.addExit("west", forest);
        dungeon.addExit("north", treasureRoom);

        // Adding items
        entrance.addItem("key", "A rusty key that might open something.");
        forest.addItem("potion", "A health potion that restores 20 health.");
        dungeon.addItem("sword", "A rusty sword, looks useful for fighting.");

        // Adding NPCs
        NPC npc1 = new NPC("Old Man", "Beware, there is a dangerous enemy ahead!");
        forest.setNPC(npc1);

        // Game loop
        Room currentRoom = entrance;

        while (true) {
            System.out.println("\n════════════════════════════════════════════════════");
            System.out.println(currentRoom.description);
            if (!currentRoom.items.isEmpty()) {
                currentRoom.items.forEach((item, description) -> System.out.println("🪙 You see a " + item + ": " + description));
            }
            if (currentRoom.npc != null) {
                System.out.println("👤 There is an NPC here. You can talk to them.");
            }
            System.out.println("➡️ Exits: " + currentRoom.exits.keySet());
            System.out.println("════════════════════════════════════════════════════");

            System.out.print("What would you like to do? ");
            String command = scanner.nextLine();

            if (command.equals("help")) {
                System.out.println("Commands: go [direction], check inventory, collect [item], talk, attack, run, use [item]");
            } else if (command.startsWith("go ")) {
                String direction = command.substring(3);
                if (currentRoom.exits.containsKey(direction)) {
                    currentRoom = currentRoom.exits.get(direction);
                    if (currentRoom == dungeon) {
                        System.out.println("🐾 You hear growling noises... an enemy might be nearby!");
                    }
                } else {
                    System.out.println("⛔ You can't go that way.");
                }
            } else if (command.equals("check inventory")) {
                player.checkInventory();
            } else if (command.startsWith("collect ")) {
                String item = command.substring(8);
                if (currentRoom.items.containsKey(item)) {
                    player.addItemToInventory(item, currentRoom.items.get(item));
                    currentRoom.items.remove(item);
                    System.out.println("✅ You collected the " + item + "!");
                } else {
                    System.out.println("🚫 There is no such item here.");
                }
            } else if (command.equals("talk") && currentRoom.npc != null) {
                currentRoom.npc.talk();
            } else if (command.equals("attack") && currentRoom == dungeon) {
                Combat.fight(player);
                if (player.health <= 0) break;
            } else if (command.equals("run") && currentRoom == dungeon) {
                System.out.println("🏃‍♂️ You ran away from the enemy.");
            } else {
                String[] parts = command.split(" ");
                if (parts[0].equals("use") && parts.length > 1) {
                    String item = parts[1];
                    if (player.inventory.containsKey(item)) {
                        if (item.equals("potion")) {
                            player.health += 20;
                            player.inventory.remove(item);
                            System.out.println("🍹 You used a potion. Your health is now " + player.health);
                        }
                    } else {
                        System.out.println("🚫 You don't have that item.");
                    }
                } else {
                    System.out.println("❓ Unknown command.");
                }
            }

            if (currentRoom == treasureRoom) {
                System.out.println("🎉 You have reached the Treasure Room and collected the treasure! You Win!");
                break;
            }
        }
    }
}
