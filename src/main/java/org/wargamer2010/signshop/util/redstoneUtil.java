package org.wargamer2010.signshop.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.type.Switch;
import com.bergerkiller.bukkit.common.utils.BlockUtil;
public class redstoneUtil {
    public static void refreshLever(Block bLever){

        //refresh blocks.  workaround
        Block attached = BlockUtil.getAttachedBlock(bLever);
        BlockUtil.applyPhysics(attached, Material.LEVER);

        if (BlockUtil.getAttachedFace(bLever) == BlockFace.DOWN || BlockUtil.getAttachedFace(bLever) == BlockFace.UP ){
            BlockUtil.applyPhysics(bLever.getRelative(BlockFace.EAST), Material.LEVER);
            BlockUtil.applyPhysics(bLever.getRelative(BlockFace.WEST), Material.LEVER);
            BlockUtil.applyPhysics(bLever.getRelative(BlockFace.NORTH), Material.LEVER);
            BlockUtil.applyPhysics(bLever.getRelative(BlockFace.SOUTH), Material.LEVER);
            BlockUtil.applyPhysics(bLever.getRelative(BlockFace.DOWN), Material.LEVER);
            BlockUtil.applyPhysics(bLever.getRelative(BlockFace.UP), Material.LEVER);
        }
        /*
        Block wall = bLever.getRelative(((Switch) bLever.getBlockData()).getFacing().getOppositeFace());
        BlockData walldata = wall.getBlockData();

        Switch lever = (Switch)bLever.getBlockData();

        if(wall.getType().isOccluding()) {
            if (wall.getType() != Material.getMaterial("BARRIER"))
                wall.setType(Material.getMaterial("BARRIER"));
            else
                wall.setType(Material.getMaterial("STONE"));
            wall.setBlockData(walldata);

            if (walldata.getMaterial() == Material.REDSTONE_LAMP) {
                if (lever.isPowered()) {
                    ((Lightable) walldata).setLit(true);
                    wall.setBlockData(walldata);
                } else {
                    BlockData leverdata = bLever.getBlockData();
                    bLever.setType(Material.getMaterial("GLASS"));
                    bLever.setBlockData(leverdata);
                }
            }
        }*/

    }
}
