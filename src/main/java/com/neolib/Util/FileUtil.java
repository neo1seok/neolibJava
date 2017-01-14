package com.neolib.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

public class FileUtil
{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static bool Append(string file, byte[] data)
	public static boolean Append(String file, byte[] data)
	{
//             FileStream stream = null;
//             bool result = true;
// 
//             try
//             {
//                 stream = new FileStream(file, FileMode.Append, FileAccess.Write, FileShare.Write);
//                 stream.Write(data, 0, data.Length);
//             }
//             catch (Exception)
//             {
//                 result = false;
//             }
//             finally
//             {
//                 if (stream != null)
//                     stream.Close();
//             }

		return SaverBytes(file, data, true);
	}
	public static boolean Append(String file, String org, String enc) throws UnsupportedEncodingException
	{
		if (enc == "")
		{
			enc = "utf-8";
		}
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] buff = enc.GetBytes(org);
		//byte[] buff = enc.GetBytes(org);
		byte[] buff = null;
		buff = org.getBytes(enc);

		return Append(file, buff);
	}
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static bool SaverBytes(string file, byte[] data,bool isAppend)
	public static void WriteBytes(String filename, long fileoffset,byte[] data,int boffset,int blength ) throws IOException
	{
		RandomAccessFile accessor = new RandomAccessFile (new File (filename), "rws");
		
		

		accessor.seek(Math.min(accessor.length(), fileoffset));
		accessor.write(data,boffset,blength);
		
		accessor.close();
	}
	public static void WriteBytes(String filename, long fileoffset,byte[] data ) throws IOException
	{
		WriteBytes(filename, fileoffset,data,0,data.length );
	}
	
	public static boolean SaverBytes(String filename, byte[] data, boolean isAppend)
	{
		
		
		
//		FileOutputStream fop = null;
//		File file;

		try {
			
			WriteBytes(filename,Long.MAX_VALUE ,data);
 
//			file = new File(filename);
//			fop = new FileOutputStream(file,isAppend);
// 
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//				if(isAppend) file.createNewFile();
//			}
// 
//			fop.write(data);
//			fop.flush();
//			fop.close();
 
			//System.out.println("Done");
 
		} catch (IOException e) {
			return false;

		} 
/*		
		FileStream stream = null;
		boolean result = true;

		try
		{
			FileMode fmode = isAppend ? FileMode.Append : FileMode.Create;
			stream = new FileStream(file, fmode, FileAccess.Write, FileShare.Write);
			stream.Write(data, 0, data.length);
		}
		catch (RuntimeException e)
		{
			return false;
		}
		finally
		{
			if (stream != null)
			{
				stream.Close();
			}
		}
*/
		
		return true;

	}
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static byte[] ReadBytes(string file)
	public static byte[] ReadBytes(String file)
	{
		return ReadBytes(file, 0, (new java.io.File(file)).length());
	}

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static byte[] ReadBytes(string file, long offset, long length)
	public static byte[] ReadBytes(String filename, long offset, long length)
	{
		FileInputStream stream = null;
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] result = null;
		byte[] result = null;
		File file;

		try 
		{
			file = new File(filename);
			stream = new FileInputStream(file);
			
			long reallength = Math.min(length, file.length() - offset);
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: result = new byte[reallength];
			result = new byte[(int)reallength];
			stream.skip(offset);
			stream.read(result, 0, (int)result.length);
			stream.close();
		} catch (IOException e) {
			return null;
		}  
		finally {
		}

		return result;
	}

	public static String ReadText(String file)
	{
		return ReadText(file, "utf-8");
	}

	public static String ReadText(String file, String enc)
	{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] bytes = ReadBytes(file);
		byte[] bytes = ReadBytes(file);

		if (bytes == null)
		{
			return "";
		}
		//Charset
		String ret = "";
		try {
			ret = new String(bytes,enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
//		return enc.GetString(bytes);
	}
	public static String GetFilename(String path)
	{
		String[] values = path.split("[\\\\]", -1);

		if (values.length > 0)
		{
			return values[values.length - 1];
		}
		else
		{
			return path;
		}
	}
/*
	public static String GetStrFormFile(String filename, String enc)
	{
		String ret = "";
		if (enc == null)
		{
			enc = Encoding.Default;
		}
		try
		{
			//get InputStream of a file
            InputStream is = new FileInputStream(filename);
            String strContent;
           
            
   
            //Create BufferedReader object
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
            bReader.toString();
            
            StringBuffer sbfFileContents = new StringBuffer();
            String line = null;
           
            //read file line by line
            while( (line = bReader.readLine()) != null){
                    sbfFileContents.append(line);
            }
           
            //finally convert StringBuffer object to String!
            strContent = sbfFileContents.toString();
           
            
            
			InputStream inputStream = new FileInputStream(filename);
			//Reader      reader      = new InputStreamReader(inputStream);
			
			try (InputStreamReader sr = new InputStreamReader(inputStream, enc))
			{
				
				sr.r

				ret = sr.ReadToEnd();
				sr.Close();
			}
		}
		catch (java.lang.Exception e)
		{
		}


		return ret;
	}
	
	public static void SaveStr2File(String filename, String str, boolean isAppend, String enc)
	{
		String ret = "";
		if (enc == null)
		{
			enc = Encoding.Default;
		}
		try (StreamWriter sr = new StreamWriter(filename, isAppend, enc))
		{

			sr.Write(str);
			sr.Close();
		}


	}
*/	
	public static String[] GetStrLineFormFile(String filename, String enc)
	{
		String ret = ReadText(filename,  enc);
		return ret.split("\r\n");

	}
}