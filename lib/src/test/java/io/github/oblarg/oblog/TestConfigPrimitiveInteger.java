package io.github.oblarg.oblog;

import io.github.oblarg.oblog.annotations.Config;

public class TestConfigPrimitiveInteger implements Loggable {

  @Config.NumberSlider
  int i = 4;

}
