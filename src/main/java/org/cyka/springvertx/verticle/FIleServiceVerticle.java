package org.cyka.springvertx.verticle;

import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FIleServiceVertivle @Description TODO @Author long @Date 2020/5/27 11:16 @Version 1.0
 */
@Slf4j
public class FIleServiceVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    vertx.eventBus().consumer("file", message -> message.reply("ok"));
  }
}
