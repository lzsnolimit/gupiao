package com.stocks.service.read;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ReadTodayFromFiles {
	private static final Logger LOGGER = Logger.getLogger(ReadTodayFromFiles.class);
	public static Map<String, String> listAllFilesUnderPath(String path,String type,String date){
		Map<String, String> paths=new HashMap<>();
		if (path==null||path.isEmpty()) {
			return paths;
		}
		File basicFolder=new File(path);
		if (!basicFolder.exists()||!basicFolder.isDirectory()) {
			return paths;
		}
		File[] subFolders=basicFolder.listFiles();
		if (subFolders==null) {
			return paths;
		}
		for (File file : subFolders) {
			String symbol=file.getName();
			File targetFile=new File(file.getAbsolutePath()+"/"+type+"_"+date+".txt");
			//System.out.println(targetFile.getAbsolutePath());
			if (targetFile!=null&&targetFile.exists()) {
				paths.put(symbol, targetFile.getAbsolutePath());
			}
		}
		//LOGGER.info("Read "+paths.size()+" files for type "+type+" date"+date);
		return paths;
	}
}