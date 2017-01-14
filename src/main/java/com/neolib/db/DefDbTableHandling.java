package com.neolib.db;





import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DefDbTableHandling implements IDbTableHandling{
	protected IDbHanling<?> idbHandling;
	protected String lastSQL;
	protected String tablename = "";
	protected String tableprefix = "";

	protected String SQLINSERT_FORM ="INSERT INTO {0} (seq,{1}_uid,{2},reg_date,updt_date) select COALESCE(max(seq),0)+1, concat('{1}_',COALESCE(max(seq),0)+1),{3},now(),now() from {0}";
	//{0}:tablename , {1}:uuid prefix ,{2}:col name array {3} values {4} where 


	protected String SQLLASTUID_FORM = "SELECT uid FROM {0} order by  seq desc LIMIT 1";
	//{0}:tablle

	protected String SQLUPDATE_FORM ="UPDATE {0} SET {1},updt_date = now() WHERE {3}_uid = '{2}'";
	//{0}:tablename , {1}: col value array {2} uid {3}: tableprefix
	
	protected String SQLDELETE_FORM ="DELETE FROM {0} WHERE {2}_uid = '{1}'";
	//{0}:tablename , {1}: uid {2}: tableprefix


	protected String SQLSELECT_FORM = "SELECT {0} FROM {1} {2}";//{0} : colnames, {1} tablename {2} where

	protected String FMT2TAG = "@@#DIR#$@";


	protected String FMTCREATETABLE =
			"CREATE TABLE `{0}` (	`seq` int(11) NOT NULL COMMENT '���',	`uid` varchar(20) NOT NULL COMMENT '���� ���̵�',	{1}PRIMARY KEY (`seq`,`uid`{2})) ENGINE=MyISAM DEFAULT CHARSET=utf8;";
	
	
	
	

	public DefDbTableHandling(IDbHanling<?> idbHandling, String tablename, String tableprefix)
	{
		this.idbHandling = idbHandling;
		this.tablename = tablename;
		this.tableprefix = tableprefix;

	}
	public DefDbTableHandling(IDbHanling<?> idbHandling, String tableprefix)
	{
		this.idbHandling = idbHandling;
		this.tableprefix = tableprefix;

	}
	
	public void SetTableName(String tablename)
	{
		this.tablename = tablename;
	}


	public String getTableName()
	{
		return tablename.toUpperCase();
	}

	public int Insert(Map<String, String> lineinfo) throws SQLException
	{
		lastSQL = "select COALESCE(max(seq),0) +1 as maxseq FROM " + tablename;

		List<Map<String,Object>> table = idbHandling.Query(lastSQL);
		
		
		table.get(0).get("maxseq");
		long lastseq = Long.parseLong(table.get(0).get("maxseq").toString());
		
		
		

		//long lastseq = (long)table.Rows[0]["maxseq"];

		lastSQL = MakeInsertSQL(lineinfo);
		idbHandling.Excute(lastSQL);
		return (int)lastseq;
	}
	public void Update(String uid, Map<String, String> lineinfo) throws SQLException
	{
		lastSQL = MakeUpdateSQL(uid, lineinfo);

		idbHandling.Excute(lastSQL);

	}
	
	public void Delete(String uid) throws SQLException {
		// TODO Auto-generated method stub
		lastSQL = SingleQutoFormat(SQLDELETE_FORM, tablename,uid,tableprefix);
		idbHandling.Excute(lastSQL);
	}


	public List<Map<String,Object>> select(String colnames,String where,Object ... args) throws SQLException

	{
		lastSQL = SingleQutoFormat(SQLSELECT_FORM,colnames,tablename,SingleQutoFormat(where,args));
		return idbHandling.Query(lastSQL);

	}

	public Map<String,Object> selectSingleWhere(String colnames, String where, Object... args) throws SQLException
	{
		
		List<Map<String, Object>> datatable = select(colnames,  where, args);

		
		if (datatable.size() == 0) return null;

		return datatable.get(0);


	}
	public static String SingleQutoFormat(String fmt, Object... args) {

		fmt = fmt.replace("'", "''");

		return MessageFormat.format(fmt, args);
	}
	public Map<String,Object> selectSingle(String colname, String value) throws SQLException
	{
		//Util.SingleQutoFormat("where {0}='{1}'", colname, value);
		List<Map<String, Object>> datatable = select("*", SingleQutoFormat("where {0}='{1}'", colname, value));
		
		if (datatable.size() == 0) return null;

		return datatable.get(0);


	}
	public Map<String,Object> selectSingle(int seq) throws SQLException
	{
		
		return selectSingle("seq",Integer.toString(seq));
	}
	public String getuid(int seq) throws SQLException
	{
		Map<String, Object> datarow = selectSingle(seq);

		return datarow.get(tableprefix + "_uid").toString();
		
	}
	String mergefrmstringarray(String[] strarray, char ch)
	{
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (String tmp : strarray)
		{
			if (count != 0) sb.append(",");
			if (ch != 0) sb.append(ch);
			sb.append(tmp);
			if (ch != 0) sb.append(ch);
			count++;

		}
		return sb.toString();

	}

	protected String mergefrmstringarray(String[] strarray, String format, char... chendtrim)
	{

		return mergefrmstringarray(strarray, format, chendtrim);
	}
	protected String mergefrmstringarray(String[] strarray, String format, String format2, char...chendtrim)
	{
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (String tmp : strarray)
		{
			String fmt = (!tmp.startsWith(FMT2TAG)) ? format : format2;

			sb.append(SingleQutoFormat(fmt, tmp.replace(FMT2TAG, "")));
			count++;

		}
		String ret = sb.toString();
		String trims = new String(chendtrim);
		String regex = "["+trims+"]$";
		String rtrim = ret.replaceAll(regex,"");
		
		
	

		return rtrim;//Ʈ�� ���� �ʿ�

	}
	protected String mergefrmstringarray(Map<String, String> dic, String format, String format2, char... chendtrim)
	{
		StringBuilder sb = new StringBuilder();
		int count = 0;
		String[] repstr = new String[] { "\\", "'", "\"" };
		for (Entry<String, String> tmp : dic.entrySet())
		{
			String value =tmp.getValue();
			
			if(value == null) value =""; 
					

	
			String fmt = (!value.startsWith(FMT2TAG)) ? format : format2;
			String inputvalue = tmp.getValue();//.Replace("\\","\\\\");
			
			for(String n : repstr){
				inputvalue = inputvalue.replace(n, "\\" + n);
			}
			
		
			
			sb.append(SingleQutoFormat(fmt, tmp.getKey(), inputvalue.replace(FMT2TAG, "")));
			count++;

		}
		
		String ret = sb.toString();
		String trims = new String(chendtrim);
		String regex = "["+trims+"]$";
		String rtrim = ret.replaceAll(regex,"");
		

		return rtrim;//Ʈ�� ����.Trim(chendtrim);

	}
	protected String MakeCreateTables(Map<String,Object> dt)
	{
		String fieldval = "";
		for (String tmp : dt.keySet())
		{
			if (tmp.toString() == "uid") continue;
			fieldval += SingleQutoFormat("`{0}` text DEFAULT NULL COMMENT '{0}',\r\n", tmp.toString());

		}



		return SingleQutoFormat(FMTCREATETABLE, tablename, fieldval, "");
	}

	protected String MakeInsertSQL(Map<String, String> dic)
	{
		String keys = mergefrmstringarray(dic.keySet().toArray(new String[0]), '\0');
		String values = mergefrmstringarray(dic, "'{1}',", "{1},", ',');

		return SingleQutoFormat(SQLINSERT_FORM, tablename, tableprefix, keys, values);

	}

	protected String MakeUpdateSQL(String uid, Map<String, String> dic)
	{
		String keyvalues = mergefrmstringarray(dic, "{0} = \'{1}\',", "{0} = {1},", ',');

		return SingleQutoFormat(SQLUPDATE_FORM, tablename, keyvalues, uid,tableprefix);

	}
	
	public Map<String, Object> selectSingle(String uid) throws SQLException {
		// TODO Auto-generated method stub
		return selectSingle(tableprefix+"_uid",uid);
	}

}
