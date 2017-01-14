package com.neolib.tangible;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 *@autor neo1seok
 * @since 2014.01.15
 *
 */
public class Util {

	public static int sizeOf(Object o)
	{
	try {
		  // Get a ByteArrayOutputStream to catch the output of the
		  //   ObjectOutputStream without going to disk
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();

		  // Get an ObjectOutputStream so we can dump the entire
		  //   object at one shot
		  ObjectOutputStream oos = new ObjectOutputStream(baos);

		  // Write the object
		  oos.writeObject(o);

		  // Close the stream
		  oos.close();

		  // Query the ByteArrayOutputStream for its size
		  return baos.size();
		} catch (IOException e) {
		  // Something went wrong.  Print the stack trace.
		  e.printStackTrace();

		  // Return -1 so the caller knows we failed
		  return -1;
		}
	}
	
	public static Object ToClass(Object o)
	{
		try {
			  // Get a ByteArrayOutputStream to catch the output of the
			  //   ObjectOutputStream without going to disk
		//	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 ByteArrayInputStream bais = new ByteArrayInputStream((byte[]) o);
			  // Get an ObjectOutputStream so we can dump the entire
			  //   object at one shot
		;//	  ObjectOutputStream oos = new ObjectOutputStream(baos);
			 ObjectInputStream ois = new ObjectInputStream(bais);
			  // Write the object
			  ois.read((byte[]) o);

			  // Close the stream
			  ois.close();

			  // Query the ByteArrayOutputStream for its size
			  try {
				return ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} catch (IOException e) {
			  // Something went wrong.  Print the stack trace.
			  e.printStackTrace();

			  // Return -1 so the caller knows we failed
			  return null;
			}
		return null;
	}
	
	public static byte[] ToByteArray(Object o)
	{
	try {
		  // Get a ByteArrayOutputStream to catch the output of the
		  //   ObjectOutputStream without going to disk
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();

		  // Get an ObjectOutputStream so we can dump the entire
		  //   object at one shot
		  ObjectOutputStream oos = new ObjectOutputStream(baos);

		  // Write the object
		  oos.writeObject(o);

		  // Close the stream
		  oos.close();

		  // Query the ByteArrayOutputStream for its size
		  return baos.toByteArray();
		} catch (IOException e) {
		  // Something went wrong.  Print the stack trace.
		  e.printStackTrace();

		  // Return -1 so the caller knows we failed
		  return null;
		}
	}
}
