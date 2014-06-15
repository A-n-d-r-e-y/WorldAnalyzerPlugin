package lv.morozov.worldanalyzer.service;

import lv.morozov.worldanalyzer.FakeCommandSender;
import lv.morozov.worldanalyzer.model.FakeScanResult;

public class JDBCScansRepositoryIntegrationTest {

	public static void main(String[] args) {
		
		JDBCScansRepository.Config config = new JDBCScansRepository.Config(
				"morozov"
				, "123456"
				, "MAIN"
				, "test");
		
		JDBCScansRepository repo = new JDBCScansRepository(config);
		repo.setCommandSender(new FakeCommandSender());
		repo.SaveScanResult(new FakeScanResult());
	}

}
