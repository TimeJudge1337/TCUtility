package com.troblecodings.tcutility.client;

import com.troblecodings.tcutility.fluids.TCFluidBlock;
import com.troblecodings.tcutility.fluids.TCFluids;
import com.troblecodings.tcutility.init.TCFluidsInit;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TCFluidsClient {

	public static void init() {
        MinecraftForge.EVENT_BUS.register(TCFluidsClient.class);
    }

    @SubscribeEvent
    public static void onTextureStitchPre(TextureStitchEvent.Pre event) {
        for (Block block : TCFluidsInit.blocksToRegister) {
            if (block instanceof TCFluidBlock) {
                Fluid base = ((TCFluidBlock) block).getFluid();

                System.out.println("[TCUtility] Fluid: " + base.getName()
                    + " still=" + base.getStill()
                    + " flow=" + base.getFlowing());

                if (base.getStill() != null) {
                    event.getMap().registerSprite(base.getStill());
                }
                if (base.getFlowing() != null) {
                    event.getMap().registerSprite(base.getFlowing());
                }

                if (base instanceof TCFluids) {
                    TCFluids fluid = (TCFluids) base;
                    if (fluid.getOverlay() != null) {
                        event.getMap().registerSprite(fluid.getOverlay());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        for (Block block : TCFluidsInit.blocksToRegister) {
            if (block instanceof TCFluidBlock) {
                // LEVEL ignorieren → alle States benutzen dasselbe Model
                ModelLoader.setCustomStateMapper(block,
                        (new StateMap.Builder()).ignore(BlockFluidClassic.LEVEL).build());
            }
        }
    }
}
