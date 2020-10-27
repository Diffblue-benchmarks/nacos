package com.alibaba.nacos.client.naming.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for com.alibaba.nacos.client.naming.utils.Chooser
 *
 * @author Diffblue Cover
 */

class ChooserTest {

    @Test
    void factory() {
        ArrayList<Pair<String>> pairs = new ArrayList<Pair<String>>();
        pairs.add(new Pair<String>("foo", 1.0));
        assertThat(new Chooser<String, String>("foo", pairs).getUniqueKey(), is("foo"));
    }

    @Test
    void randomReturnsFoo() {
        ArrayList<Pair<String>> pairs = new ArrayList<Pair<String>>();
        pairs.add(new Pair<String>("foo", 1.0));
        assertThat(new Chooser<String, String>("bar", pairs).random(), is("foo"));
    }

    @Test
    void randomReturnsNull() {
        assertThat(new Chooser<String, String>("foo").random(), is(nullValue()));
    }

    @Test
    void randomWithWeight1() {
        ArrayList<Pair<String>> pairs = new ArrayList<Pair<String>>();
        pairs.add(new Pair<String>("foo", 1.0));
        assertThat(new Chooser<String, String>("bar", pairs).randomWithWeight(), is("foo"));
    }

    @Test
    void randomWithWeight2() {
        ArrayList<Pair<String>> pairs = new ArrayList<Pair<String>>();
        @SuppressWarnings("unchecked")
        Pair<String> pair = mock(Pair.class);
        when(pair.item())
            .thenReturn("foo");
        when(pair.weight())
            .thenReturn(1.0)
            .thenReturn(0.0);
        pairs.add(pair);
        assertThat(new Chooser<String, String>("bar", pairs).randomWithWeight(), is("foo"));
    }

    @Test
    void refreshItemsWithWeightIsEmpty() {
        new Chooser<String, String>("foo").refresh(new ArrayList<Pair<String>>());
    }

    @Test
    void testEquals() {
        assertThat(new Chooser<String, String>("foo").equals(new Object()), is(false));
        assertThat(new Chooser<String, String>("foo").equals(null), is(false));
    }
}
