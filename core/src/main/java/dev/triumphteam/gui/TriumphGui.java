/**
 * MIT License
 *
 * Copyright (c) 2021 TriumphTeam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.triumphteam.gui;

import dev.triumphteam.gui.components.InventoryProvider;
import dev.triumphteam.gui.components.util.Legacy;
import dev.triumphteam.gui.guis.BaseGui;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class TriumphGui {

    // The plugin instance for registering the event and for the close delay.
    private static Plugin PLUGIN = null;
    private static InventoryProvider.Chest CHEST_INVENTORY_PROVIDER =
        (title, owner, size) -> Bukkit.createInventory(owner, size, Legacy.SERIALIZER.serialize(title));
    private static InventoryProvider.Typed TYPED_INVENTORY_PROVIDER =
        (title, owner, type) -> Bukkit.createInventory(owner, type, Legacy.SERIALIZER.serialize(title));

    private TriumphGui() {}

    public static void init(final @NotNull Plugin plugin) {
        PLUGIN = plugin;
    }

    public static void inventoryProviders(
        final @NotNull InventoryProvider.Chest chestInventoryProvider,
        final @NotNull InventoryProvider.Typed typedInventoryProvider
    ) {
        CHEST_INVENTORY_PROVIDER = chestInventoryProvider;
        TYPED_INVENTORY_PROVIDER = typedInventoryProvider;
    }

    public static @NotNull Plugin getPlugin() {
        if (PLUGIN == null) init(JavaPlugin.getProvidingPlugin(BaseGui.class));
        return PLUGIN;
    }

    public static @NotNull InventoryProvider.Chest getChestInventoryProvider() {
        return CHEST_INVENTORY_PROVIDER;
    }

    public static @NotNull InventoryProvider.Typed getTypedInventoryProvider() {
        return TYPED_INVENTORY_PROVIDER;
    }
}
