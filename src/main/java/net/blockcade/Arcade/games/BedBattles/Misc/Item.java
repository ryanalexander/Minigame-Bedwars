package net.blockcade.Arcade.games.BedBattles.Misc;

import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.games.BedBattles.Inventories.Assets.*;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamUpgrades;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

import static net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge;
import static org.bukkit.Material.*;

public enum Item {

    PLACEHOLDER(GRAY_STAINED_GLASS, "&r",new String[]{""}, player -> {}),

    // Shop Menu
    CATEGORY_BLOCKS(OAK_PLANKS,"&6Blocks", new String[]{""}, player -> {
        player.openInventory(blocks.getShop(Main.game,player));
    }),
    CATEGORY_WEAPONS(DIAMOND_SWORD, "&6Weapons", new String[]{""}, player -> {
        player.openInventory(weapons.getShop(Main.game,player));
    }),
    CATEGORY_ARMOR(IRON_CHESTPLATE, "&6Armor", new String[]{""}, player -> {
        player.openInventory(armor.getShop(Main.game,player));
    }),
    CATEGORY_TOOLS(GOLDEN_PICKAXE, "&6Tools", new String[]{""}, player -> {
       player.openInventory(tools.getShop(Main.game,player));
    }),
    CATEGORY_SPECIAL(FIRE_CHARGE, "&6Specials", new String[]{""}, player -> {
        player.openInventory(specials.getShop(Main.game,player));
    }),


    // Blocks Menu
    BLOCK_WOOL_RED(RED_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.RED_WOOL,16));
    },16),
    BLOCK_WOOL_BLUE(BLUE_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.BLUE_WOOL,16));
    },16),
    BLOCK_WOOL_AQUA(CYAN_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.CYAN_WOOL,16));
    },16),
    BLOCK_WOOL_YELLOW(YELLOW_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.YELLOW_WOOL,16));
    },16),
    BLOCK_WOOL_WHITE(WHITE_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.WHITE_WOOL,16));
    },16),
    BLOCK_WOOL_GRAY(GRAY_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.GRAY_WOOL,16));
    },16),
    BLOCK_WOOL_PINK(PINK_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.PINK_WOOL,16));
    },16),
    BLOCK_WOOL_GREEN(LIME_WOOL, "&bWool", new String[]{"&r","&7Cost: &f4 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.LIME_WOOL,16));
    },16),

    BLOCK_WOODEN_PLANKS(OAK_PLANKS, "&bWooden Planks", new String[]{"&r","&7Cost: &65 Gold"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.GOLD_INGOT, 5))
            player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS,8));
    },8),

    BLOCK_CLAY_RED(RED_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.RED_TERRACOTTA,8));
    },12),
    BLOCK_CLAY_BLUE(BLUE_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.BLUE_TERRACOTTA,8));
    },12),
    BLOCK_CLAY_AQUA(CYAN_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.CYAN_TERRACOTTA,8));
    },12),
    BLOCK_CLAY_YELLOW(YELLOW_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.YELLOW_TERRACOTTA,8));
    },12),
    BLOCK_CLAY_WHITE(WHITE_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.WHITE_TERRACOTTA,8));
    },12),
    BLOCK_CLAY_GRAY(GRAY_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.GRAY_TERRACOTTA,8));
    },12),
    BLOCK_CLAY_PINK(PINK_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.PINK_TERRACOTTA,8));
    },12),
    BLOCK_CLAY_GREEN(GREEN_TERRACOTTA, "&bHardened Clay", new String[]{"&r","&7Cost: &f12 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.GREEN_TERRACOTTA,8));
    },12),

    BLOCK_GLASS_RED(RED_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.RED_STAINED_GLASS,4));
    },4),
    BLOCK_GLASS_BLUE(BLUE_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.BLUE_STAINED_GLASS,4));
    },4),
    BLOCK_GLASS_AQUA(CYAN_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.CYAN_STAINED_GLASS,4));
    },4),
    BLOCK_GLASS_YELLOW(YELLOW_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.YELLOW_STAINED_GLASS,4));
    },4),
    BLOCK_GLASS_WHITE(WHITE_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.WHITE_STAINED_GLASS,4));
    },4),
    BLOCK_GLASS_GRAY(GRAY_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.GRAY_STAINED_GLASS,4));
    },4),
    BLOCK_GLASS_PINK(PINK_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.PINK_STAINED_GLASS,4));
    },4),
    BLOCK_GLASS_GREEN(GREEN_STAINED_GLASS, "&bGlass", new String[]{"&r","&7Cost: &f12 Iron","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 12))
            player.getInventory().addItem(new ItemStack(Material.GREEN_STAINED_GLASS,4));
    },4),

    BLOCK_END_STONE(END_STONE, "&bEnd Stone", new String[]{"&r","&7Cost: &f24 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, Material.IRON_INGOT, 24))
            player.getInventory().addItem(new ItemStack(Material.END_STONE,12));
    },12),
    BLOCK_OBSIDIAN(OBSIDIAN, "&bObsidian", new String[]{"&r","&7Cost: &a4 Emeralds","&r","&dBlast Proof"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, EMERALD, 4))
            player.getInventory().addItem(new ItemStack(OBSIDIAN,12));
    },12),


    // Weapons Menu
    WEAPON_STONE_SWORD(STONE_SWORD, "&bStone Sword", new String[]{"&r","&7Cost: &f20 Iron"}, player -> {
        if (doCharge(player, Material.IRON_INGOT, 20)) {
            weapons.removeSword(player);
            ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
            if (Main.teams.get(Main.game.TeamManager().getTeam(player)).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            player.getInventory().addItem(sword);
        }
    }),
    WEAPON_IRON_SWORD(IRON_SWORD, "&bIron Sword", new String[]{"&r","&7Cost: &67 Gold"}, player -> {
        if (doCharge(player, Material.GOLD_INGOT, 7)) {
            weapons.removeSword(player);
            ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
            if (Main.teams.get(Main.game.TeamManager().getTeam(player)).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            player.getInventory().addItem(sword);
        }
    }),
    WEAPON_DIAMOND_SWORD(DIAMOND_SWORD,"&bDiamond Sword",new String[]{"&r","&7Cost: &a4 Emeralds"}, player -> {
        if (doCharge(player, Material.EMERALD, 4)) {
            weapons.removeSword(player);
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            if (Main.teams.get(Main.game.TeamManager().getTeam(player)).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            player.getInventory().addItem(sword);
        }
    }),
    WEAPON_BOW(BOW, "&bBow", new String[]{"&r","&7Cost: &614 Gold"}, player -> {
        if (doCharge(player, Material.GOLD_INGOT, 14))
            player.getInventory().addItem(new ItemStack(Material.BOW, 1));
    }),
    WEAPON_BOW_SUPER(BOW, "&dSuper Bow",new String[]{"&r","&7Cost: &a6 Emeralds"}, player -> {
        if (doCharge(player, Material.EMERALD, 6)) {
            ItemStack _bowItemStack = new ItemStack(Material.BOW);
            ItemMeta _bowItemMeta = _bowItemStack.getItemMeta();
            assert _bowItemMeta != null;
            _bowItemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
            _bowItemMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            _bowItemMeta.setDisplayName(Text.format("&d" + player.getName() + "'s Super bow"));
            _bowItemStack.setItemMeta(_bowItemMeta);
            player.getInventory().addItem(_bowItemStack);
        }
    }),
    WEAPON_ARROW(ARROW, "&bArrow",new String[]{"&r","&7Cost: &62 Gold"}, player -> {
        if (doCharge(player, Material.GOLD_INGOT, 2))
            player.getInventory().addItem(new ItemStack(Material.ARROW, 8));
    },8),


    // Armor Menu
    ARMOR_CHAINMAIL(CHAINMAIL_CHESTPLATE,"&7Chainmail Armor", new String[]{"&r","&7Cost: &f40 Iron"}, player -> {
        if(Objects.requireNonNull(player.getInventory().getBoots()).getType()==(DIAMOND_BOOTS)
                ||Objects.requireNonNull(player.getInventory().getBoots()).getType()==(IRON_BOOTS)
                ||Objects.requireNonNull(player.getInventory().getBoots()).getType()==(CHAINMAIL_BOOTS)
        ){
            player.sendMessage(Text.format("&cYou already own this item, or something greater."));
            return;
        }
        if (doCharge(player, IRON_INGOT, 40)) {
            ItemStack armor_leggings = new ItemStack(CHAINMAIL_LEGGINGS);
            ItemStack armor_boots = new ItemStack(CHAINMAIL_BOOTS);
            player.getInventory().setArmorContents(new ItemStack[]{armor_boots, armor_leggings, player.getInventory().getChestplate(), player.getInventory().getHelmet()});
        }
    }),
    ARMOR_IRON(IRON_CHESTPLATE, "&bIron Armor", new String[]{"&r","&7Cost: &612 Gold"}, player -> {
        if(Objects.requireNonNull(player.getInventory().getBoots()).getType()==(DIAMOND_BOOTS)
                ||Objects.requireNonNull(player.getInventory().getBoots()).getType()==(IRON_BOOTS)
        ){
            player.sendMessage(Text.format("&cYou already own this item, or something greater."));
            return;
        }
        if (doCharge(player, GOLD_INGOT, 12)) {
            ItemStack armor_leggings = new ItemStack(IRON_LEGGINGS);
            ItemStack armor_boots = new ItemStack(IRON_BOOTS);
            player.getInventory().setArmorContents(new ItemStack[]{armor_boots, armor_leggings, player.getInventory().getChestplate(), player.getInventory().getHelmet()});
        }
    }),
    ARMOR_DIAMOND(DIAMOND_CHESTPLATE, "&bDiamond Chestplate", new String[]{"&r","&7Cost: &a6 Emeralds"}, player -> {
        if(Objects.requireNonNull(player.getInventory().getBoots()).getType()==(DIAMOND_BOOTS)){
            player.sendMessage(Text.format("&cYou already own this item, or something greater."));
            return;
        }
        if (doCharge(player, EMERALD, 6)) {
            ItemStack armor_leggings = new ItemStack(DIAMOND_LEGGINGS);
            ItemStack armor_boots = new ItemStack(DIAMOND_BOOTS);
            player.getInventory().setArmorContents(new ItemStack[]{armor_boots, armor_leggings, player.getInventory().getChestplate(), player.getInventory().getHelmet()});
        }
    }),


    // Specials Menu
    SPECIAL_TNT(TNT, "&bTNT", new String[]{"&r","&7Cost: &64 Gold"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, GOLD_INGOT, 4))
            player.getInventory().addItem(new ItemStack(Material.TNT, 1));
    }),
    SPECIAL_ENDER_PEARL(ENDER_PEARL, "&bEnder Pearl", new String[]{"&r","&7Cost: &a4 Emeralds"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, EMERALD, 4))
            player.getInventory().addItem(new ItemStack(ENDER_PEARL, 1));
    }),
    SPECIAL_WATER(WATER_BUCKET, "&bWater", new String[]{"&r","&7Cost: &64 Gold"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, GOLD_INGOT, 4))
            player.getInventory().addItem(new ItemStack(WATER_BUCKET, 1));
    }),
    SPECIAL_FIREBALL(FIRE_CHARGE, "&bFireball", new String[]{"&r", "&7Cost: &f40 Iron"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, IRON_INGOT, 40))
            player.getInventory().addItem(new ItemStack(FIRE_CHARGE, 1));
    }),
    SPECIAL_BRIDGE_EGG(EGG, "&bBridge Egg", new String[]{"&r","&7Cost: &a3 Emeralds"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, EMERALD, 3))
            player.getInventory().addItem(new ItemStack(EGG, 1));
    }),
    SPECIAL_GOLDEN_APPLE(GOLDEN_APPLE, "&bGolden Apple", new String[]{"&r","&7Cost: &63 Gold"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, GOLD_INGOT, 3))
            player.getInventory().addItem(new ItemStack(GOLDEN_APPLE, 1));
    }),
    SPECIAL_ENDER_CHEST(ENDER_CHEST, "&dPortable Enderchest", new String[]{"&r","&7Cost: &624 Gold","&r","&c&lONE USE"}, player -> {
        if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(player, GOLD_INGOT, 24))
            player.getInventory().addItem(new ItemStack(ENDER_CHEST, 1));
    }),

    // TODO Potions (Somehow)

    DISABLED(BARRIER,"&cItem Disabled",new String[]{"&r","&fThis is has been disabled","&fin some cases this may be","&ftemporary, or due to technical issues.","&r","&6Check again later."}, player -> {
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1l,1l);
    })
    ;

    Material material;
    String name;
    String[] lore;
    net.blockcade.Arcade.Utils.Formatting.Item.click click;
    int amount = 1;

    Item(Material material, String name, String[] lore, net.blockcade.Arcade.Utils.Formatting.Item.click click){
        this.material=material;
        this.name=name;
        this.lore=lore;
        this.click=click;
    }
    Item(Material material, String name, String[] lore, net.blockcade.Arcade.Utils.Formatting.Item.click click, int amount){
        this.material=material;
        this.name=name;
        this.lore=lore;
        this.click=click;
        this.amount=amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ItemStack spigot() {
        return getItem().spigot();
    }

    public net.blockcade.Arcade.Utils.Formatting.Item getItem() {
        net.blockcade.Arcade.Utils.Formatting.Item item = new net.blockcade.Arcade.Utils.Formatting.Item(material,name);
        item.setOnClick(click);
        item.setLore(lore);
        item.setAmount(amount);
        return item;
    }
}
