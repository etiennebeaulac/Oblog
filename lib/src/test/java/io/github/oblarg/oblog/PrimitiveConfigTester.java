package io.github.oblarg.oblog;

import edu.wpi.first.networktables.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PrimitiveConfigTester {

  @Test
  public void testIntegerConfig() {
    List<NetworkTableEntry> mockedEntries = new ArrayList<>();

    TestRootContainerInteger rootContainer = new TestRootContainerInteger();

    ShuffleboardMocks mocks = new ShuffleboardMocks(mockedEntries);

    Logger.configureLoggingTest(Logger.LogType.CONFIG, rootContainer, mocks.getMockedShuffleboard(), mocks.getMockedNTInstance());

    verify(mocks.getMockedShuffleboard()).getTab("TestConfigPrimitiveInteger: Config");
    verify(mocks.getMockedContainer()).add("i", 4);
    
    verify(mocks.getMockedNTInstance()).addEntryListener(any(NetworkTableEntry.class), any(), eq(EntryListenerFlags.kUpdate));

    assertEquals(4, rootContainer.test.i);

    mocks.getListenerCallback().accept(new EntryNotification(mocks.getMockedNTInstance(),
        0, 0, "test", mocks.getMockedNTValue(10), EntryListenerFlags.kUpdate));
    Logger.updateEntries();
    assertEquals(10, rootContainer.test.i);

    mocks.getListenerCallback().accept(new EntryNotification(mocks.getMockedNTInstance(),
        0, 0, "test", mocks.getMockedNTValue(11.5), EntryListenerFlags.kUpdate));
    Logger.updateEntries();
    assertEquals(11, rootContainer.test.i);

  }

  @Test
  public void testDoubleConfig() {
    List<NetworkTableEntry> mockedEntries = new ArrayList<>();

    TestRootContainerDouble rootContainer = new TestRootContainerDouble();

    ShuffleboardMocks mocks = new ShuffleboardMocks(mockedEntries);

    Logger.configureLoggingTest(Logger.LogType.CONFIG, rootContainer, mocks.getMockedShuffleboard(), mocks.getMockedNTInstance());

    verify(mocks.getMockedShuffleboard()).getTab("TestConfigPrimitiveDouble: Config");
    verify(mocks.getMockedContainer()).add("j", 3.14d);
    
    verify(mocks.getMockedNTInstance()).addEntryListener(any(NetworkTableEntry.class), any(), eq(EntryListenerFlags.kUpdate));

    assertEquals(3.14, rootContainer.test.j, 1e-7);

    mocks.getListenerCallback().accept(new EntryNotification(mocks.getMockedNTInstance(),
        0, 0, "test", mocks.getMockedNTValue(-4.5), EntryListenerFlags.kUpdate));
    Logger.updateEntries();
    assertEquals(-4.5, rootContainer.test.j, 1e-7);

    mocks.getListenerCallback().accept(new EntryNotification(mocks.getMockedNTInstance(),
        0, 0, "test", mocks.getMockedNTValue(23.1), EntryListenerFlags.kUpdate));
    Logger.updateEntries();
    assertEquals(23.1, rootContainer.test.j, 1e-7);

  }

  @Test
  public void testBooleanConfig() {
    List<NetworkTableEntry> mockedEntries = new ArrayList<>();

    TestRootContainerBoolean rootContainer = new TestRootContainerBoolean();

    ShuffleboardMocks mocks = new ShuffleboardMocks(mockedEntries);

    Logger.configureLoggingTest(Logger.LogType.CONFIG, rootContainer, mocks.getMockedShuffleboard(), mocks.getMockedNTInstance());

    verify(mocks.getMockedShuffleboard()).getTab("TestConfigPrimitiveBoolean: Config");
    verify(mocks.getMockedContainer()).add("isToggled", true);
    
    verify(mocks.getMockedNTInstance()).addEntryListener(any(NetworkTableEntry.class), any(), eq(EntryListenerFlags.kUpdate));

    assertEquals(true, rootContainer.test.isToggled);

    mocks.getListenerCallback().accept(new EntryNotification(mocks.getMockedNTInstance(),
        0, 0, "test", mocks.getMockedNTValue(false), EntryListenerFlags.kUpdate));
    Logger.updateEntries();
    assertEquals(false, rootContainer.test.isToggled);

    mocks.getListenerCallback().accept(new EntryNotification(mocks.getMockedNTInstance(),
        0, 0, "test", mocks.getMockedNTValue(true), EntryListenerFlags.kUpdate));
    Logger.updateEntries();
    assertEquals(true, rootContainer.test.isToggled);

  }

  private class TestRootContainerInteger {

    TestConfigPrimitiveInteger test = new TestConfigPrimitiveInteger();

  }

  private class TestRootContainerDouble {

    TestConfigPrimitiveDouble test = new TestConfigPrimitiveDouble();

  }

  private class TestRootContainerBoolean {

    TestConfigPrimitiveBoolean test = new TestConfigPrimitiveBoolean();

  }

}
