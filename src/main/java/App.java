import utils.EnvironmentVariableReader;
import utils.PropertiesReader;
import server.ServerSettings;
import server.BasicServer;
import server.handlers.BasicHandlerBuilder;

public class App {

    public static void main(String[] args) throws Exception {
        String environment = EnvironmentVariableReader.getSystemEnvironment();
        System.out.println(String.format("Starting server in '%s' environment.", environment));

        ServerSettings settings = new ServerSettings(new PropertiesReader(environment));

        BasicServer server = new BasicServer(settings);
        server.withContext(new BasicHandlerBuilder().withMainServlet().withReadyServlet().withStatusServlet().build());
        server.start();
    }
}
