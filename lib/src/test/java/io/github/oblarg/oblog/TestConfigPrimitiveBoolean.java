package io.github.oblarg.oblog;

import io.github.oblarg.oblog.annotations.Config;

public class TestConfigPrimitiveBoolean implements Loggable {

  @Config.ToggleButton
  boolean isToggled = true;

}
