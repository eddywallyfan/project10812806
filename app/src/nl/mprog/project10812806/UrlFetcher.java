/* John Wesselingh
 * 10812806
 * Class die een URL omzet tot een string: ontleend aan: Android programming: The Big Nerd Ranch Guide van Bill Phillips en Brian Hardy
 */
package nl.mprog.project10812806;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlFetcher {
	
	// Zet een url om naar een bytearray
	byte[] getUrlBytes(String urlSpec) throws IOException{
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		try{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			
			if(connection.getResponseCode()!= HttpURLConnection.HTTP_OK){
				return null;
			}
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			return out.toByteArray();
		} finally{
			connection.disconnect();
		}
	}	
	
	// Zet de bytearray om naar een String
	public String getUrl(String urlSpec) throws IOException{
		return new String (getUrlBytes(urlSpec));
	}
}
