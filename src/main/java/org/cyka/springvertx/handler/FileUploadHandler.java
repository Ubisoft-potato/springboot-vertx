package org.cyka.springvertx.handler;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName FileUploadHandler @Description TODO @Author long @Date 2020/5/26 16:16 @Version 1.0
 */
@Component
@Slf4j
public class FileUploadHandler implements Handler<RoutingContext> {

  @Override
  public void handle(RoutingContext context) {
    JsonArray jsonArray = new JsonArray();

    context
        .fileUploads()
        .forEach(fileUpload -> jsonArray.add("{fileName:" + fileUpload.uploadedFileName() + "}"));

    context
        .vertx()
        .eventBus()
        .request(
            "file",
            jsonArray,
            res -> {
              if (res.succeeded()) {
                log.info("res: {}", res.result().body());
                context.response().end("upload finish!");
              }
            });
  }
}
