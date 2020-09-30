package com.alibaba.nacos.client.config.http;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.alibaba.nacos.client.config.impl.HttpSimpleClient;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit tests for com.alibaba.nacos.client.config.http.MetricsHttpAgent
 *
 * @author Diffblue Cover
 */

class MetricsHttpAgentTest {

    @Test
    void start() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        new MetricsHttpAgent(httpAgent).start();
        Mockito.verify(httpAgent).start();
    }

    @Test
    void httpGetHeadersIsEmptyAndParamValuesIsEmptyAndReadTimeoutMsIsOne() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        when(httpAgent.httpGet(Mockito.<String>any(), Mockito.<java.util.List<String>>any(), Mockito.<java.util.List<String>>any(), Mockito.<String>any(), anyLong()))
            .thenReturn(new HttpSimpleClient.HttpResult(1, "/some/path.html"));
        // pojo HttpSimpleClient.HttpResult
    }

    @Test
    void httpPostHeadersIsEmptyAndParamValuesIsEmptyAndReadTimeoutMsIsOne() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        when(httpAgent.httpPost(Mockito.<String>any(), Mockito.<java.util.List<String>>any(), Mockito.<java.util.List<String>>any(), Mockito.<String>any(), anyLong()))
            .thenReturn(new HttpSimpleClient.HttpResult(1, "/some/path.html"));
        // pojo HttpSimpleClient.HttpResult
    }

    @Test
    void httpDeleteHeadersIsEmptyAndParamValuesIsEmptyAndReadTimeoutMsIsOne() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        when(httpAgent.httpDelete(Mockito.<String>any(), Mockito.<java.util.List<String>>any(), Mockito.<java.util.List<String>>any(), Mockito.<String>any(), anyLong()))
            .thenReturn(new HttpSimpleClient.HttpResult(1, "/some/path.html"));
        // pojo HttpSimpleClient.HttpResult
    }

    @Test
    void getName() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        when(httpAgent.getName())
            .thenReturn("foo=bar");
        assertThat(new MetricsHttpAgent(httpAgent).getName(), is("foo=bar"));
    }

    @Test
    void getNamespace() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        when(httpAgent.getNamespace())
            .thenReturn("foo=bar");
        assertThat(new MetricsHttpAgent(httpAgent).getNamespace(), is("foo=bar"));
    }

    @Test
    void getTenant() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        when(httpAgent.getTenant())
            .thenReturn("foo=bar");
        assertThat(new MetricsHttpAgent(httpAgent).getTenant(), is("foo=bar"));
    }

    @Test
    void getEncode() throws java.io.IOException, com.alibaba.nacos.api.exception.NacosException {
        HttpAgent httpAgent = mock(HttpAgent.class);
        when(httpAgent.getEncode())
            .thenReturn("foo=bar");
        assertThat(new MetricsHttpAgent(httpAgent).getEncode(), is("foo=bar"));
    }
}
