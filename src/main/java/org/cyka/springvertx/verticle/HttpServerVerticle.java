package org.cyka.springvertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @ClassName HttpServerVerticle @Description TODO @Author long @Date 2020/5/26 15:46 @Version 1.0
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class HttpServerVerticle extends AbstractVerticle {

  private final Handler<RoutingContext> fileUploadHandler;

  private final Handler<RoutingContext> helloHandler;

  @Override
  public void start() throws Exception {

    Router router = Router.router(vertx);

    router.get("/hello").handler(helloHandler);

    router
        .post("/upload")
        .handler(
            BodyHandler.create()
                .setUploadsDirectory("C:\\Users\\Noob\\Desktop")
                .setDeleteUploadedFilesOnEnd(true))
        .handler(fileUploadHandler);

    for (int i = 0; i < 12; i++) {
      vertx.createHttpServer().requestHandler(router).listen(8080);
    }
  }

  public HttpServerVerticle(
      Handler<RoutingContext> fileUploadHandler, Handler<RoutingContext> helloHandler) {
    this.fileUploadHandler = fileUploadHandler;
    this.helloHandler = helloHandler;
  }
}
