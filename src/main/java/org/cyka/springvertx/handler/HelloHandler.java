package org.cyka.springvertx.handler;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** @ClassName HelloHandler @Description TODO @Author long @Date 2020/5/27 8:53 @Version 1.0 */
@Component
@Slf4j
public class HelloHandler implements Handler<RoutingContext> {

  @Override
  public void handle(RoutingContext context) {
    log.info("thread: {}", Thread.currentThread().getName());
//    try {
//      TimeUnit.SECONDS.sleep(3);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    context.response().putHeader("content-type", "text/html").end("hello");
  }
}
