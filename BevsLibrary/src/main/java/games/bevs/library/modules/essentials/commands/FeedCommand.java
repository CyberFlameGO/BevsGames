package games.bevs.library.modules.essentials.commands;

import games.bevs.library.commons.CC;
import games.bevs.library.commons.Rank;
import games.bevs.library.modules.commands.Command;
import games.bevs.library.modules.commands.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Heal a players health
 * 
 * Useages
 * 		/Heal <Target>
 * 			Heal another player
 * 			Target - is a player name 
 * 		/heal 
 * 			Heal yourself
 */
public class FeedCommand
{
	private static final int MAX_FOOD_LEVEL = 20;
	private static final float GOOD_SATURATION = 12.8f; //Saturation from eating a steak

	@Command(name = "heal",
			description = "Heal a player",
			usage = "/heal",
			requireRank = Rank.MOD,
			playerOnly = true
	)
	public void onHeal(CommandArgs args)
	{
		Player player = args.getPlayer();
		Player target = player;
		if(args.length() == 1)
		{
			String targetName = args.getArgs(0);
			target = Bukkit.getPlayer(targetName);
			if(target == null)
			{
				player.sendMessage(CC.red + (targetName + " is not online!"));
				return;
			}
		}

		target.setFoodLevel(MAX_FOOD_LEVEL);
		target.setSaturation(GOOD_SATURATION);

		if(player != target)
			target.sendMessage(CC.green +  ("You have been feed."));
		player.sendMessage(CC.green + (target.getName() + " has been feed."));
	}
}
