package lv.morozov.worldanalyzer.model;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public interface IScanResult {
	public Location getCoord();
	public int getId();
	public EntityType getEntityType();
}
