@Configuration
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Bean
    public MyService myService() {
        return new MyService(appName);
    }
}
