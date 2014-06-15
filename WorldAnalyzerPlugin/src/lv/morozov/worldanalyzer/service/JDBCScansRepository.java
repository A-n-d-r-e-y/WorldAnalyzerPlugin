package lv.morozov.worldanalyzer.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import org.bukkit.command.CommandSender;

import lv.morozov.worldanalyzer.model.IScanResult;

public class JDBCScansRepository implements IScansRepository {
	
	public static class Config {
		public String userName;
		public String password;
		public String serverName;
		public String databaseName;
		
		public Config(String userName, String password, String serverName, String databaseName){
			
			this.userName = userName;
			this.password = password;
			this.serverName = serverName;
			this.databaseName = databaseName;
		}
	}

	private JDBCScansRepository.Config config;
	private CommandSender commandSender;
	
	public void setCommandSender(CommandSender value) throws NullPointerException{
		
		if (value == null) throw new NullPointerException("value must not be null");
		this.commandSender = value;
	}
	
	public JDBCScansRepository(JDBCScansRepository.Config config) throws NullPointerException{
		
		if (config == null) throw new NullPointerException("config must not be null");	
		this.config = config;
		
	}
	
	@Override
	public void SaveScanResult(IScanResult scanResult) {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String userName = config.userName;
            String password = config.password;
            String url = String.format("jdbc:sqlserver://%s:1433;databaseName=%s"
            		, config.serverName
            		, config.databaseName);

            Connection con = DriverManager.getConnection(url, userName, password);
     
            String statement = "{call dbo.test(?,?,?,?,?)}";
            CallableStatement callableStatement = con.prepareCall(statement);
            
            callableStatement.setInt(1, scanResult.getId());
            callableStatement.setDouble(2, scanResult.getCoord().getX());
            callableStatement.setDouble(3, scanResult.getCoord().getY());
            callableStatement.setDouble(4, scanResult.getCoord().getZ());
            callableStatement.setNString(5, scanResult.getEntityType().name());

            callableStatement.execute();
            
        } catch (Exception e)
        {
            if (commandSender != null) commandSender.sendMessage("Exception: "+e.getMessage());
        }
	}

}
