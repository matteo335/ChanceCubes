package chanceCubes.rewards.defaultRewards;

import chanceCubes.CCubesCore;
import chanceCubes.util.Scheduler;
import chanceCubes.util.Task;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class WitherReward implements IChanceCubeReward
{
	@Override
	public void trigger(World world, BlockPos pos, EntityPlayer player)
	{
		final EntityWither wither = new EntityWither(world);
		wither.setLocationAndAngles(pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 1.5D, 90.0F, 0.0F);
		wither.renderYawOffset = 90.0F;
		wither.ignite();
		wither.setCustomNameTag("Kiwi");
		world.spawnEntityInWorld(wither);
		
		player.addChatMessage(new TextComponentString("\"You've got to ask yourself one question: 'Do I feel lucky?' Well, do ya, punk?\""));

		Task task = new Task("Wither Reward", 180)
		{
			@Override
			public void callback()
			{
				removeEnts(wither);
			}
		};

		Scheduler.scheduleTask(task);
	}

	private void removeEnts(Entity ent)
	{
		if(ent.worldObj.rand.nextInt(5) != 1)
			ent.setDead();
	}

	@Override
	public int getChanceValue()
	{
		return -100;
	}

	@Override
	public String getName()
	{
		return CCubesCore.MODID + ":Wither";
	}

}
