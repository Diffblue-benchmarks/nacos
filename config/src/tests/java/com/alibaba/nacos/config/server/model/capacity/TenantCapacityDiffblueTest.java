package com.alibaba.nacos.config.server.model.capacity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

/**
 * Unit tests for com.alibaba.nacos.config.server.model.capacity.TenantCapacity
 *
 * @author Diffblue JCover
 */

public class TenantCapacityDiffblueTest {

    @Test(timeout=10000)
    public void getTenantReturnsNull() {
        assertThat(new TenantCapacity().getTenant(), is(nullValue()));
    }

    @Test(timeout=10000)
    public void setTenantToFoo() {
        TenantCapacity tenantCapacity = new TenantCapacity();
        tenantCapacity.setTenant("foo");
        assertThat(tenantCapacity.getTenant(), is("foo"));
    }
}
