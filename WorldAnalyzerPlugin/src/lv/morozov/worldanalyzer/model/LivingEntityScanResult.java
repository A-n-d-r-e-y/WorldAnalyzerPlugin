package lv.morozov.worldanalyzer.model;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.EntityType;

public class LivingEntityScanResult implements IScanResult {

	private LivingEntity creature;
	
	public LivingEntityScanResult(LivingEntity creature) throws NullPointerException{
		if (creature == null) throw new NullPointerException("creature must not be null");
		
		this.creature = creature;
	}
		
	@Override
	public Location getCoord() {
		return creature.getLocation();
	}

	@Override
	public int getId() {
		return creature.getEntityId();
	}

	@Override
	public EntityType getEntityType() {
		return creature.getType();
	}

}
