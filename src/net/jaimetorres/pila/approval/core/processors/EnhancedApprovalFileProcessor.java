package net.jaimetorres.pila.approval.core.processors;

import java.sql.Connection;

public abstract class EnhancedApprovalFileProcessor {

	private Connection interssiConnnection;
	
	public void setInterssiConnection(Connection interssiConnnection){
		this.interssiConnnection=interssiConnnection;
	}
	
	public Connection getInterssiConnection(){
		return interssiConnnection;
	}
}
