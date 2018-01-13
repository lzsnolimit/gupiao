package com.stocks.service.read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReadTodayDetailsFromFiles {
	private static final Logger LOGGER = Logger.getLogger(ReadTodayDetailsFromFiles.class);

	public List<String> litAllFilesUnderPath(String path){
		List<String> paths=new ArrayList<>();
		if (path==null||path.isEmpty()) {
			return paths;
		}
		File basicFolder=new File(path);
		if (!basicFolder.exists()) {
			return paths;
		}
		
		return paths;
	}
	
}
