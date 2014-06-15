package lv.morozov.worldanalyzer;

import java.util.Iterator;

import lv.morozov.worldanalyzer.service.JDBCScansRepository;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WorldAnalyzerPlugin extends JavaPlugin {
	
	private String
		username_config_section = "scan.db_connection.username",
		password_config_section = "scan.db_connection.password",
		database_config_section = "scan.db_connection.database_name",
		servername_config_section = "scan.db_connection.server_name";

	private void initializeConfig(){
		FileConfiguration config = this.getConfig();
	
		config.addDefault(username_config_section, "morozov");
		config.addDefault(password_config_section, "123456");
		config.addDefault(database_config_section, "test");
		config.addDefault(servername_config_section, "localhost");
	
		config.options().copyDefaults(true);
		this.saveConfig();
	}
	
	@Override
	public void onEnable() {
		
		getLogger().info("starting config initialization");
		initializeConfig();	
	}

	@Override
	public void onDisable() {

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		Player player = (Player) sender;
		World world = player.getWorld();
		FileConfiguration plugin_config = this.getConfig();
		
		if (command.getName().equalsIgnoreCase("scan") && sender instanceof Player){
			
			JDBCScansRepository.Config db_config = new JDBCScansRepository.Config(
					plugin_config.getString(username_config_section)
					, plugin_config.getString(password_config_section)
					, plugin_config.getString(servername_config_section)
					, plugin_config.getString(database_config_section));
			
			JDBCScansRepository repo = new JDBCScansRepository(db_config);
			repo.setCommandSender(player);

			WorldScanner scanner = new WorldScanner(repo);
			scanner.ScanLivingEntities(world);
		}
		
		if (command.getName().equalsIgnoreCase("dump") && sender instanceof Player){
			
			int 
				totalCount = world.getLivingEntities().size(),
				creepersCount = 0,
				zombiesCount = 0,
				skeletonsCount = 0,
				sheepsCount = 0,
				cowsCount = 0,
				chickensCount = 0,
				willagersCount = 0,
				othersCount = 0;
			
			for(Iterator<LivingEntity> i = world.getLivingEntities().iterator(); i.hasNext(); ) {
				LivingEntity item = i.next();
			
				switch (item.getType()){
				case CREEPER : ++creepersCount;
					break;
				case ZOMBIE : ++zombiesCount;
					break;
				case SKELETON : ++skeletonsCount;
					break;
				case SHEEP : ++sheepsCount;
					break;
				case COW : ++cowsCount;
					break;
				case CHICKEN : ++chickensCount;
					break;
				case VILLAGER : ++willagersCount;
					break;
				default : ++othersCount;
				}
			}

			player.sendMessage(String.format("Creatures counts:\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s"
					,"Creepers="+creepersCount
					,"Zombies="+zombiesCount
					,"Skeletones="+skeletonsCount
					,"Sheeps="+sheepsCount
					,"Cows="+cowsCount
					,"Chickens="+chickensCount
					,"Villagers="+willagersCount
					,"Others="+othersCount
					,"Total="+totalCount));
			
			return true;
		}
			
		return false;

	}

}