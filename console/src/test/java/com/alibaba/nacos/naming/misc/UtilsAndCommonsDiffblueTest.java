package com.alibaba.nacos.naming.misc;

import static org.junit.Assert.assertEquals;
import com.alibaba.nacos.naming.exception.NacosException;
import java.util.Map;
import org.junit.Test;

public class UtilsAndCommonsDiffblueTest {
  @Test
  public void testAssembleFullServiceName() {
    // Arrange, Act and Assert
    assertEquals("123##,", UtilsAndCommons.assembleFullServiceName("123", ","));
  }

  @Test
  public void testGetAllExceptionMsg() {
    // Arrange, Act and Assert
    assertEquals("caused: foo;", UtilsAndCommons.getAllExceptionMsg(new Throwable("foo")));
  }

  @Test
  public void testGetAllExceptionMsg2() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act and Assert
    assertEquals("", UtilsAndCommons.getAllExceptionMsg(throwable));
    assertEquals("java.lang.Throwable", throwable.toString());
  }

  @Test
  public void testGetAllExceptionMsg3() {
    // Arrange, Act and Assert
    assertEquals("", UtilsAndCommons.getAllExceptionMsg(null));
  }

  @Test
  public void testGetSwitchDomainKey() {
    // Arrange, Act and Assert
    assertEquals("com.alibaba.nacos.naming.domains.meta.00-00---000" + "-NACOS_SWITCH_DOMAIN-000---00-00",
        UtilsAndCommons.getSwitchDomainKey());
  }

  @Test
  public void testParseMetadata() throws NacosException {
    // Arrange
    Map<String, String> parseMetadataResult = UtilsAndCommons.parseMetadata(",");

    // Act
    Map<String, String> actualParseMetadataResult = UtilsAndCommons.parseMetadata("");

    // Assert
    assertEquals(0, parseMetadataResult.size());
    assertEquals(0, actualParseMetadataResult.size());
  }

  @Test
  public void testShakeUp() {
    // Arrange
    int actualShakeUpResult = UtilsAndCommons.shakeUp(",", 1);

    // Act and Assert
    assertEquals(0, actualShakeUpResult);
    assertEquals(0, UtilsAndCommons.shakeUp(null, 1));
  }
}

