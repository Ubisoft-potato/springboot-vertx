package org.cyka.springvertx;

import com.chibchasoft.vertx.spring.ApplicationContextProvider;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.cyka.springvertx.verticle.FIleServiceVerticle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringVertxApplication implements CommandLineRunner {

  private final ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(SpringVertxApplication.class, args);
  }

  @Override
  public void run(String... args) {
    Vertx vertx = Vertx.vertx();
    ApplicationContextProvider.setApplicationContext(applicationContext);
    vertx.deployVerticle(
        "spring:httpServerVerticle",
        new DeploymentOptions()
            .setWorkerPoolName("blocking-worker-pool")
            .setInstances(Runtime.getRuntime().availableProcessors() * 2),
        res -> {
          if (res.succeeded()) {
            log.info("Http DeploymentId: {}", res.result());
          }
        });
    vertx.deployVerticle(
        FIleServiceVerticle.class,
        new DeploymentOptions().setWorker(true).setInstances(6),
        res -> {
          if (res.succeeded()) {
            log.info("File DeploymentId: {}", res.result());
          }
        });
  }

  public SpringVertxApplication(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }
}
