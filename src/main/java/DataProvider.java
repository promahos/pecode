import com.typesafe.config.Config;

import java.io.File;

import static com.typesafe.config.ConfigFactory.load;
import static com.typesafe.config.ConfigFactory.parseFile;
import static java.lang.System.getenv;

public interface DataProvider {
    String VALID_LOGIN = readCredentials().getString("login");
    String VALID_PASSWORD = readCredentials().getString("password");

    static Config readCredentials() {
        String path = getenv("CREDENTIALS");
        Config config;

        if (path == null) {
            config = load("credentials.conf"); // local usage, file should be at resources directory
        } else {
            File configFile = new File(path);
            Config fileConfig = parseFile(configFile);
            config = load(fileConfig);
        }
        return config;
    }
}
