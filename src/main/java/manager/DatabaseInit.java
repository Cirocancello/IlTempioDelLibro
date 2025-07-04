package manager;


import java.io.FileNotFoundException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import util.Config;
import util.ConfigHelper;

@WebListener
public class DatabaseInit implements ServletContextListener {

	private Config config;
	private ConfigHelper cfgHelper;

	public void contextInitialized(ServletContextEvent event) {
		this.cfgHelper = new ConfigHelper(event.getServletContext());
		try {
			this.config = cfgHelper.readConfig();
			DatabaseManager.initInstance(
				this.config.getHost(),
				this.config.getUsername(),
				this.config.getPassword(),
				this.config.getPort(),
				false
			);
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found");
		}
	}
}

