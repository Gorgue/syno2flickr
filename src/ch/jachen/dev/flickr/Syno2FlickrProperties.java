package ch.jachen.dev.flickr;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Get Flickr properties (sharedKey, api key, ...) Singleton with an enum.
 * 
 * @author jbrek
 * 
 */
public enum Syno2FlickrProperties {

	INSTANCE; // Unique instance

	private static String propertyFile = "syno2flickr.properties"; /// Properties file name
	
	private static String apiKey;  /// API Key provided by Flickr.com
	private static String sharedSecret; /// Shared secret for this app
	private static String folderSync; /// Folder synchronized with your Flickr account
	private static String archiveFolder; /// Path to archive folder
	private static boolean gotProperties = false; // Flag to know if we already read property file

	/**
	 * Read properties from file
	 * 
	 * @throws Syno2FlickrException
	 *             For any error.
	 */
	private static void readProperties() throws Syno2FlickrException {

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(propertyFile));
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
					"Properties file '" + propertyFile
							+ "' loaded successfully.");
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(
					"Properties file '" + propertyFile + "' not found. Exit.");
			throw new Syno2FlickrException(
					"Cannot read Syno2Flickr properties file. "
							+ "The properties file name must be '"
							+ propertyFile
							+ "' and be placed in the same folder as the program.");
		}
		
		// Read each property
		apiKey = prop.getProperty("apiKey");
		sharedSecret = prop.getProperty("sharedSecret");
		folderSync = prop.getProperty("syncFolder");
		archiveFolder = prop.getProperty("archiveFolder");
		
		// Set flag OK
		gotProperties = true;
	}

	
	public static String getApiKey() throws Syno2FlickrException {
		if (!gotProperties)
			readProperties();
		return apiKey;
	}

	public static String getSharedSecret() throws Syno2FlickrException {
		if (!gotProperties)
			readProperties();
		return sharedSecret;
	}

	public static String getFolderToSync() throws Syno2FlickrException {
		if (!gotProperties)
			readProperties();
		return folderSync;
	}


	public static String getArchiveFolder() {
		return archiveFolder;
	}


	public static String getPropertyFile() {
		return propertyFile;
	}


	public static void setPropertyFile(String propertyFile) {
		Syno2FlickrProperties.propertyFile = propertyFile;
	}

}