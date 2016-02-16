package com.meetup.engage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
 
 
public class URIMaker {
	URIBuilder uri = new URIBuilder();
	
	public URIMaker(String aScheme, String aHost, String aKey) {
		uri.setScheme(aScheme);
		uri.setHost(aHost);
		uri.setParameter("key", aKey);
	}
	
	public void setPath(String aPath) {
		uri.setPath(aPath);
	}
	
	public void setParam(String aParam, String aValue) {
		uri.setParameter(aParam, aValue);
	}
	
	public void setParam(String[] aParms, String[] aValues) {
		int size = aParms.length;
		if (size == aValues.length) {
			for (int i=0; i<size; i++) {
				uri.setParameter(aParms[i], aValues[i]);
			}
		}
	}
	
	public String getResponse() {
		String resp = "";
		URI request = null;
		
		try {
			request = uri.build();
		} catch (URISyntaxException e) {
			System.err.println("Invalid URI could not be built.");
		}
		
		HttpGet getHttp = new HttpGet(request);
		System.out.println("Request: " + getHttp.getURI());
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = client.execute(getHttp);
			resp = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resp;
	}
}