package chanceCubes.listeners;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import chanceCubes.CCubesCore;
import chanceCubes.registry.ChanceCubeRegistry;
import chanceCubes.rewards.defaultRewards.CustomUserReward;

public class PlayerConnectListener
{
	@SubscribeEvent
	public void onPlayerLogin(final PlayerLoggedInEvent event)
	{
		if(event.player.world.isRemote)
			return;

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				new CustomUserReward(event.player.getName(), event.player.getUniqueID());
			}

		}).start();
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent event)
	{
		if(event.player.world.isRemote)
			return;

		ChanceCubeRegistry.INSTANCE.unregisterReward(CCubesCore.MODID + ":CR_" + event.player.getCommandSenderEntity().getName());
	}
}