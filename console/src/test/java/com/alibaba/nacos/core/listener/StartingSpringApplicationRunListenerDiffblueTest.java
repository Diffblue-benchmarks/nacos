package com.alibaba.nacos.core.listener;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;
import org.springframework.core.env.Environment;

public class StartingSpringApplicationRunListenerDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange
    Class<?> forNameResult = Object.class;
    Class<?> forNameResult1 = Object.class;
    String[] stringArray = new String[]{"foo", "foo", "foo"};

    // Act
    new StartingSpringApplicationRunListener(new SpringApplication(forNameResult, forNameResult1, Object.class),
        stringArray);

    // Assert
    assertEquals(3, stringArray.length);
    assertArrayEquals(new String[]{"foo", "foo", "foo"}, stringArray);
  }

  @Test
  public void testContextPrepared() {
    // Arrange
    Class<?> forNameResult = Object.class;
    Class<?> forNameResult1 = Object.class;
    StartingSpringApplicationRunListener startingSpringApplicationRunListener = new StartingSpringApplicationRunListener(
        new SpringApplication(forNameResult, forNameResult1, Object.class), new String[]{"foo", "foo", "foo"});
    AnnotationConfigReactiveWebApplicationContext annotationConfigReactiveWebApplicationContext = new AnnotationConfigReactiveWebApplicationContext();

    // Act
    startingSpringApplicationRunListener.contextPrepared(annotationConfigReactiveWebApplicationContext);

    // Assert
    Environment environment = annotationConfigReactiveWebApplicationContext.getEnvironment();
    String actualToStringResult = ((StandardReactiveWebEnvironment) environment).getPropertySources().toString();
    assertTrue(((StandardReactiveWebEnvironment) environment)
        .getConversionService() instanceof org.springframework.core.convert.support.DefaultConversionService);
    assertEquals(1, environment.getDefaultProfiles().length);
    assertEquals("[MapPropertySource {name='systemProperties'}," + " SystemEnvironmentPropertySource {name="
        + "'systemEnvironment'}]", actualToStringResult);
  }

  @Test
  public void testGetOrder() {
    // Arrange
    Class<?> forNameResult = Object.class;
    Class<?> forNameResult1 = Object.class;

    // Act and Assert
    assertEquals(-2147483648,
        (new StartingSpringApplicationRunListener(new SpringApplication(forNameResult, forNameResult1, Object.class),
            new String[]{"foo", "foo", "foo"})).getOrder());
  }
}

