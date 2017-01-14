package com.neolib.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefDbHanlingMySQL<T> implements IDbHanling<T>{
	
	Connection conn = null;
	//MySqlConnection conn = null;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://{0}:{1,number,#}/{2}";
	//0: address 1:port 2:dbname

	

	Map<T, IDbTableHandling> mapTable = new LinkedHashMap<T, IDbTableHandling>();
	
	protected String dbname;
	protected String address;
	protected String id;
	protected String passwd;

	private int port;

	private Statement stmt;

	//0:tablename 1:field arrays 2:extra primiaray key array
	public DefDbHanlingMySQL()
	{
		this.dbname = "dbname";
		this.address = "address";
		this.id = "id";
		this.passwd = "passwd";
		port = 3306;
	}
	public void setdbinfo(String dbname, String address,int port, String id, String passwd)
	{
		this.dbname = dbname;
		this.address = address;
		this.id = id;
		this.passwd = passwd;
		this.port = port;
	}
	public void open() throws Exception
	{
		Class.forName(JDBC_DRIVER);
		
		String connStr = MessageFormat.format(DB_URL, address, port, dbname);
		
		conn = DriverManager.getConnection(connStr,id,passwd);
		
		stmt = conn.createStatement();

		
	}

	public void close() throws SQLException
	{
		conn.close();
	}
//	public DataTable Query(String sql)
//	{
//
//
//		MySqlDataAdapter MyDA = new MySqlDataAdapter();
//		String sqlSelectAll = "SELECT * from info";
//		MyDA.SelectCommand = new MySqlCommand(sql, conn);
//
//		DataTable table = new DataTable();
//		MyDA.Fill(table);
//
//
//		//try
//		//{
//			
//
//
//
//		//}
//		//catch (MySqlException ex)
//		//{
//		//	Console.WriteLine("Failed to populate database list: " + ex.Message);
//		//}
//
//		//table.WriteXml();
//
//
//		return table;
//	}




	public void Excute(String sql) throws SQLException
	{

		System.out.println(sql);
		stmt.execute(sql);



	}
	
	protected void AddTable(T tableName, IDbTableHandling itable)
	{
		
		
		mapTable.put(tableName  , itable);
		
		itable.SetTableName(tableName.toString());
	}
	
	
	List<Map<String, Object>> ConvertResultSet2ListMap(ResultSet resset) throws SQLException{
		
		List<Map<String, Object>> ret = new LinkedList<Map<String, Object>>();
		
		
		ResultSetMetaData metadata = resset.getMetaData();
		
		
		while(resset.next()){
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			for(int i = 0 ; i < metadata.getColumnCount();i++){
				int colindex = i+1;
				String lablename = metadata.getColumnLabel(colindex);
				
				map.put(lablename, resset.getObject(lablename));
			}
			
			ret.add(map);
			

			
		}
		
		
		
		
		return ret;
		
	}
	
	public List<Map<String, Object>> Query(String sql) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(sql);
		ResultSet resset = stmt.executeQuery(sql);
		
		return ConvertResultSet2ListMap(resset);
	}
	public IDbTableHandling[] getLISTTABLEs() {
		// TODO Auto-generated method stub
		return mapTable.values().toArray(new IDbTableHandling[0]);
	}
	public IDbTableHandling getTable(T tableName) {
		// TODO Auto-generated method stub
		return mapTable.get(tableName);
	}
	public Map<T, IDbTableHandling> getMAPTABLE() {
		// TODO Auto-generated method stub
		return mapTable;
	}
	public void setdbinfo(String dbname, String address, String id,
			String passwd) {
		// TODO Auto-generated method stub
		
	}
	
}
