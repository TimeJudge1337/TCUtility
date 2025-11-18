package com.troblecodings.tcutility.proxy;

import com.troblecodings.tcutility.client.TCFluidsClient;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preinit(final FMLPreInitializationEvent event) {
        super.preinit(event);
        
        TCFluidsClient.init();
    }

    @Override
    public void init(final FMLInitializationEvent event) {
        super.init(event);
  
    }

    @Override
    public void postinit(final FMLPostInitializationEvent event) {
        super.postinit(event);
    }
}
