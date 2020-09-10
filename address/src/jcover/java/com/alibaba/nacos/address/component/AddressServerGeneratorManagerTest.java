package com.alibaba.nacos.address.component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.IsCloseTo.closeTo;

import com.alibaba.nacos.naming.core.Instance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for com.alibaba.nacos.address.component.AddressServerGeneratorManager
 *
 * @author Diffblue Cover
 */

@ContextConfiguration(classes = {AddressServerGeneratorManager.class})
@ExtendWith(SpringExtension.class)
class AddressServerGeneratorManagerTest {

    @Autowired
    private AddressServerGeneratorManager service;

    @Test
    void generateInstancesByIps() {
        assertThat(service.generateInstancesByIps("280 Broadway", "280 Broadway", "John Smith", new String[] { }), empty());
        assertThat(service.generateInstancesByIps("280 Broadway", "280 Broadway", "John Smith", null), empty());
    }

    @Test
    void generateInstancesByIpsClusterNameIsEmpty() {
        String[] ipArray = new String[] { "280 Broadway" };
        assertThat(service.generateInstancesByIps("280 Broadway", "280 Broadway", "", ipArray), empty());
    }

    @Test
    void generateInstancesByIpsServiceNameIsBar() {

        // arrange
        String[] ipArray = new String[] { "280 Broadway" };

        // act
        List<Instance> result =
             service.generateInstancesByIps("bar", "280 Broadway", "John Smith", ipArray);

        // assert
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getApp(), is("280 Broadway"));
        assertThat(result.get(0).getCheckRT(), is(-1L));
        assertThat(result.get(0).getFailCount(), equalTo(new AtomicInteger(0)));
        assertThat(result.get(0).getLastBeat(), is(1_599_780_185_308L));
        assertThat(result.get(0).getOKCount(), equalTo(new AtomicInteger(0)));
        assertThat(result.get(0).getTenant(), is("public"));
        assertThat(result.get(0).isMarked(), is(false));
        assertThat(result.get(0).isMockValid(), is(false));
        assertThat(result.get(0).getClusterName(), is("John Smith"));
        assertThat(result.get(0).getInstanceId(), is(nullValue()));
        assertThat(result.get(0).getIp(), is("280 Broadway"));
        assertThat(result.get(0).getMetadata().isEmpty(), is(true));
        assertThat(result.get(0).getPort(), is(8_848));
        assertThat(result.get(0).getServiceName(), is("bar"));
        assertThat(result.get(0).getWeight(), closeTo(1.0, 0.0));
        assertThat(result.get(0).isEnabled(), is(true));
        assertThat(result.get(0).isEphemeral(), is(false));
        assertThat(result.get(0).isHealthy(), is(true));
    }

    @Test
    void generateInstancesByIpsServiceNameIsEmpty() {
        String[] ipArray = new String[] { "280 Broadway" };
        assertThat(service.generateInstancesByIps("", "280 Broadway", "John Smith", ipArray), empty());
    }

    @Test
    void generateIpAndPort() {
        assertThat(service.generateIpAndPort("280 Broadway"), Matchers.arrayContaining("280 Broadway", "8848"));
        assertThat(service.generateIpAndPort(":"), Matchers.arrayContaining("", ""));
    }

    @Test
    void generateNacosServiceName() {
        assertThat(service.generateNacosServiceName("DEFAULT_GROUP"), is("DEFAULT_GROUP"));
        assertThat(service.generateNacosServiceName("Acme"), is("DEFAULT_GROUP@@Acme"));
    }

    @Test
    void generateProductName() {
        assertThat(service.generateProductName("Acme"), is("nacos.as.Acme"));
        assertThat(service.generateProductName(""), is("nacos.as.default"));
        assertThat(service.generateProductName("nacos"), is("nacos.as.default"));
    }

    @Test
    void generateResponseIpsInstanceListIsEmptyReturnsEmpty() {
        assertThat(service.generateResponseIps(new ArrayList<Instance>()), is(""));
    }
}
