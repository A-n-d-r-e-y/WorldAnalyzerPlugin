package lv.morozov.worldanalyzer.model;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class FakeScanResult implements IScanResult {

	@Override
	public Location getCoord() {
		// TODO Auto-generated method stub
		return new Location(null, 0, 0, 0);
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return EntityType.UNKNOWN;
	}

}
