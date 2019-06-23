package org.wargamer2010.signshop.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.type.Switch;
import org.bukkit.material.Lever;
import org.bukkit.material.MaterialData;
import org.wargamer2010.signshop.Seller;
import org.wargamer2010.signshop.configuration.SignShopConfig;
import org.wargamer2010.signshop.configuration.Storage;

public class lagSetter implements Runnable{
    private final Block blockToChange;

    public lagSetter(Block blockToChange){
        this.blockToChange = blockToChange.getWorld().getBlockAt(blockToChange.getX(), blockToChange.getY(), blockToChange.getZ());
    }

    @Override
    public void run(){
        if(blockToChange.getType() == Material.getMaterial("LEVER")) {
            // Best effort, load 2 chunks around the block in the hope it's enough
            itemUtil.loadChunkByBlock(blockToChange, SignShopConfig.getChunkLoadRadius());
            Switch lever = (Switch) blockToChange.getBlockData();
            lever.setPowered(false);
            blockToChange.setBlockData(lever);
            redstoneUtil.refreshLever(blockToChange);
            for(Seller seller : Storage.get().getShopsByBlock(blockToChange))
                seller.reloadBlocks();
        }
    }
}
