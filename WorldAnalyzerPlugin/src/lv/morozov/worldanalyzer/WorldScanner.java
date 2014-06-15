package lv.morozov.worldanalyzer;

import java.util.Iterator;

import lv.morozov.worldanalyzer.model.LivingEntityScanResult;
import lv.morozov.worldanalyzer.service.IScansRepository;

import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

public class WorldScanner {
	
	private IScansRepository scansRepository;
	
	public WorldScanner(IScansRepository scansRepository) throws NullPointerException{
		
		if (scansRepository == null) throw new NullPointerException("scansRepository must not be null");
		
		this.scansRepository = scansRepository;
	}
	
	public void ScanLivingEntities(World world){
		
		for(Iterator<LivingEntity> i = world.getLivingEntities().iterator(); i.hasNext(); ) {
			LivingEntity item = i.next();
		
			LivingEntityScanResult result = new LivingEntityScanResult(item);
			scansRepository.SaveScanResult(result);
		}
	}
}
