
@SpringBootApplication(scanBasePackages = {})
  public class BootstrapApplication {

    public static void main(String[] args) {
      SpringApplication.run(SpringApplication.run(BootstrapApplication.class));
    }
  }
