package com.przemyslawlusnia.vocabularycreator.core.clock;

public class TestMyClock extends MyClock {

  public static final String TEST_DATE = "1989-07-12";

  @Override
  public String getTime() {
    return TEST_DATE;
  }

}
