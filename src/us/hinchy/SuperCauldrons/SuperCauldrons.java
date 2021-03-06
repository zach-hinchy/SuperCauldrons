/*
 *  Copyright 2011 Zach Hinchy.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package us.hinchy.SuperCauldrons;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperCauldrons extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {	
        getServer().getPluginManager().registerEvents(new SuperCauldronsListener(this), this);
		
		this.getConfig();
		
		this.saveDefaultConfig();
		processDefaultConfig();

		log.info("[SuperCauldrons] Version "+this.getDescription().getVersion()+" by Zach Hinchy (http://hinchy.us/) has been enabled.");
	}

	private void processDefaultConfig() {
		final Map<String, Object> defParams = new HashMap<String, Object>();
		this.getConfig().options().copyDefaults(true);

		defParams.put("supercauldrons.infinite", true);
		defParams.put("supercauldrons.flow-fill", true);
		defParams.put("supercauldrons.redstone", true);

		for (final Entry<String, Object> e : defParams.entrySet())
			if (!this.getConfig().contains(e.getKey()))
				this.getConfig().set(e.getKey(), e.getValue());

		this.saveConfig();
		this.reloadConfig();
	}
	 
	public void onDisable() { 
		log.info("[SuperCauldrons] SuperCauldrons has been disabled.");
	}	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("screload")) {
			this.reloadConfig();
			sender.sendMessage("SuperCauldrons configuration reloaded.");
			return true;
		}
		return false; 
	}
	
}
