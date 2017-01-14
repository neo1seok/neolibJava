package com.neolib.db;

import java.sql.SQLException;


public class NeoSqlException extends SQLException {
	String lastSql;

	public NeoSqlException(String lastSql,SQLException innter) {
		super();
		this.lastSql = lastSql;
		this.setStackTrace(innter.getStackTrace());
		
		
	}
	

}
