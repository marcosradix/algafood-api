package br.com.workmade.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	
	@Autowired
	static Environment environment;
	
	public static String currentHostAndPort() {
		String url = "";
	    // Port
	    //environment.getProperty("server.port");
	    try {

	    	String getHostName = InetAddress.getLocalHost().getHostName();
			String getHostAddress = InetAddress.getLocalHost().getHostAddress();
	    	log.info(getHostName);
	    	log.info(getHostAddress);

	    	
		    String getRemoteHostAddress = InetAddress.getLoopbackAddress().getHostAddress();
		    String getRemoteHostName = InetAddress.getLoopbackAddress().getHostName();
	    	log.info(getRemoteHostName);
	    	log.info(getRemoteHostAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			log.debug("currentHostAndPort",ExceptionUtils.unwrapInvocationTargetException(e));
		}
	    
	    return url;

	}

}
