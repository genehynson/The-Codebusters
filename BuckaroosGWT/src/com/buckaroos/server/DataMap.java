package com.buckaroos.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Add buttons, etc to hear so they can be referenced via JSP
 * @author Gene Hynson
 *
 */
public class DataMap {
	
	private static HashMap<String, Object> map;
	private static List<String> objectNames;
	
	public DataMap() {
		map = new HashMap<String, Object>();
		objectNames = new ArrayList<String>();
	}
	
	/**
	 * Add object to hashmap
	 * @param objectName is title of object
	 * @param o is the object
	 */
	public void add(String objectName, Object o) {
		map.put(objectName, o);
	}
	
	/**
	 * Returns the object when given its name
	 * @param objectName
	 * @return
	 */
	public Object getObject(String objectName) {
		return map.get(objectName);
	}
	
	/**
	 * Returns list of all keys/objectNames
	 * @return
	 */
	public List<String> getAllObjectNames() {
		return objectNames;
	}
	
	

}
