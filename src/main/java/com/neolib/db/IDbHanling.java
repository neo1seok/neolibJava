package com.neolib.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDbHanling<T> {
	void setdbinfo(String dbname, String address, String id, String passwd);
	void open() throws Exception;
	void close() throws SQLException;
	List<Map<String,Object>>  Query(String sql) throws SQLException;
	void Excute(String sql) throws SQLException;
	IDbTableHandling[] getLISTTABLEs();
	IDbTableHandling getTable(T tableName);
	Map<T, IDbTableHandling> getMAPTABLE();

}
