package red.amfam.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	private Properties properties;
	private String defaultPath = "configuration/config.properties";

	public Configuration(String path) {
		if (path == null) {
			loadProperty(defaultPath);
		} else {
			loadProperty(path);
		}
	}

	private void loadProperty(String path) {
		properties = new Properties();
		try {
			InputStream iStream = new FileInputStream(path);
			properties.load(iStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getConfig(String key) {
		if (key != null) {
			return properties.getProperty(key);
		} else {
			return null;
		}
	}
}
