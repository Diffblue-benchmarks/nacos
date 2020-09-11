package com.alibaba.nacos.config.server.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Unit tests for com.alibaba.nacos.config.server.exception.GlobalExceptionHandler
 *
 * @author Diffblue Cover
 */

class GlobalExceptionHandlerTest {

    @Test
    void handleIllegalArgumentException() throws java.io.UnsupportedEncodingException, java.io.IOException {

        // arrange
        MockHttpServletResponse response = new MockHttpServletResponse();
        Exception ex = new Exception();
        ex.setStackTrace(new StackTraceElement[] { });

        // act
        new GlobalExceptionHandler().handleIllegalArgumentException(response, ex);

        // assert
        assertThat(response.getContentAsString(), is("invalid param\n"));
        assertThat(response.getStatus(), is(400));
    }

    @Test
    void handleNacosException1() throws java.io.UnsupportedEncodingException, java.io.IOException {

        // arrange
        MockHttpServletResponse response = new MockHttpServletResponse();
        NacosException ex = new NacosException();
        ex.setErrCode(1);

        // act
        new GlobalExceptionHandler().handleNacosException(response, ex);

        // assert
        assertThat(response.getContentAsString(), is("unknown exception\n"));
        assertThat(response.getStatus(), is(1));
    }

    @Test
    void handleNacosException2() throws java.io.UnsupportedEncodingException, java.io.IOException {

        // arrange
        MockHttpServletResponse response = new MockHttpServletResponse();
        NacosException ex = new NacosException();
        ex.setErrCode(1);
        ex.setErrMsg("bar");

        // act
        new GlobalExceptionHandler().handleNacosException(response, ex);

        // assert
        assertThat(response.getContentAsString(), is("bar\n"));
        assertThat(response.getStatus(), is(1));
    }
}
