package com.neolib.db;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDbTableHandling {
	
	int Insert(Map<String, String> lineinfo) throws SQLException;
	void Update(String uid, Map<String, String> lineinfo) throws SQLException;
	void Delete(String uid) throws SQLException;
	
	String getTableName();
	public void SetTableName(String tablename);
	List<Map<String,Object>> select(String colnames, String where,Object ... args) throws SQLException;
	Map<String,Object> selectSingle(String colname, String value)throws SQLException;
	Map<String,Object> selectSingleWhere(String colnames, String where, Object ... args)throws SQLException;
	Map<String,Object> selectSingle(int seq)throws SQLException;
	Map<String,Object> selectSingle(String uid)throws SQLException;
	String getuid(int seq)throws SQLException;

}
