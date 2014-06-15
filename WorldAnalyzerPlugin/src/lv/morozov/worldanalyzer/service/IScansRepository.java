package lv.morozov.worldanalyzer.service;

import lv.morozov.worldanalyzer.model.IScanResult;

public interface IScansRepository {
	public void SaveScanResult(IScanResult scanResult);
}
