package net.jaimetorres.pila.approval.conf;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jolbox.bonecp.BoneCPDataSource;

import net.jaimetorres.pila.approval.core.misc.Constants;
import net.jaimetorres.pila.approval.pojos.ExecutionParameters;
import net.jaimetorres.validaciones.helper.ConfiguracionesHelper;

@Configuration
@ComponentScan("net.jaimetorres.pila.approval.core")
public class ApprovalConfig {

	@Bean
	public ExecutionParameters createExecutionParameters(){
		//XXX ya debe existir esta instancia!
		return ExecutionParameters.getInstance(null);
	}
	
	@Bean(destroyMethod = "close")
	public DataSource interssiDS(ExecutionParameters executionParameters) {
		
		BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(Constants.DEFAULT_DRIVER_DB);
        dataSource.setJdbcUrl(executionParameters.getArgs()[0]);
        dataSource.setUsername(executionParameters.getArgs()[10]);
        dataSource.setPassword(executionParameters.getArgs()[11]);
        
        dataSource.setIdleConnectionTestPeriodInMinutes(1);
        dataSource.setIdleMaxAgeInMinutes(4);
        dataSource.setMaxConnectionsPerPartition(30);
        dataSource.setMinConnectionsPerPartition(1);
        dataSource.setPoolAvailabilityThreshold(5);
        dataSource.setPartitionCount(1);
        dataSource.setAcquireIncrement(3);
        dataSource.setStatementsCacheSize(50);
        dataSource.setConnectionTestStatement("SELECT 1");
        dataSource.setLazyInit(true);

        return dataSource;
	}
	
	@Bean
	public ConfiguracionesHelper interssiCommons(ExecutionParameters executionParameters, DataSource interssiDs){
		
		try(Connection connection = interssiDs.getConnection();){
			
			Map<String,String> parametersMap = new HashMap<>();
			parametersMap.put("url", executionParameters.getArgs()[0]);
			parametersMap.put("user", executionParameters.getArgs()[10]);
			parametersMap.put("password", executionParameters.getArgs()[11]);
			
			ConfiguracionesHelper configuracionesHelper = new ConfiguracionesHelper(connection, parametersMap);
			configuracionesHelper.llenarConfiguraciones();
			return configuracionesHelper;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
