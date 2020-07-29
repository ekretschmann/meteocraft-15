package ai.scintillia.events;

import ai.scintillia.MeteoCraft;
import ai.scintillia.commands.HelloCommand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = MeteoCraft.MODID)
        public class EventHandler {


    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event) {
        HelloCommand.register(event.getCommandDispatcher());
    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {

    }

}
