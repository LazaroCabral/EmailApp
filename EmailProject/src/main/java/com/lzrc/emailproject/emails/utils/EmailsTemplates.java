package com.lzrc.emailproject.emails.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirectoryManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailsTemplates {
	
	@Value("${default.emails.path}")
	private String emailsPath;
	
	public String[] getEmailsTemplates() {
		File file=new File(emailsPath);
		String[] files=file.list();
		
		return files;
	}
	
	public void editEmailTemplate(String modelName, String emailMessage) throws IOException {
		Path path=Paths.get(emailsPath+modelName);
		OpenOption[] oo= {};
		Files.writeString(path, emailMessage, oo);

	}
	
	public String getEmailTemplate(String modelName) throws IOException {
		
		Path pathTest=Paths.get(emailsPath+modelName);
		String joinLines="";
		List<String> lines = Files.readAllLines(pathTest);
		
		for (int i=0; i<lines.size() ;i++) {
			//System.out.println(linha);
			String line=lines.get(i);
			
			if(i!=lines.size()-1) {
				joinLines=joinLines+line+"\n";
			}else {
				joinLines=joinLines+line;
			}
		}
		return joinLines;
		
	}
	

}
