package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlParserTest {

    @Test
    public void unitTest1() {
        UrlParser urlParser = new UrlParser("http://www.google.com");
        Assert.assertEquals(80, urlParser.getPort());
        Assert.assertEquals("http", urlParser.getStrProtocol());
        Assert.assertEquals("www.google.com", urlParser.getDomain());
        Assert.assertEquals("/", urlParser.getDocument());
    }

    @Test
    public void unitTest2() {
        UrlParser urlParser = new UrlParser("https://www.google.com:456/");
        Assert.assertEquals(456, urlParser.getPort());
        Assert.assertEquals("https", urlParser.getStrProtocol());
        Assert.assertEquals("www.google.com", urlParser.getDomain());
        Assert.assertEquals("/", urlParser.getDocument());
    }

    @Test
    public void unitTest3() {
        UrlParser urlParser = new UrlParser("https://wm:456/");
        Assert.assertEquals(456, urlParser.getPort());
        Assert.assertEquals("https", urlParser.getStrProtocol());
        Assert.assertEquals("wm", urlParser.getDomain());
        Assert.assertEquals("/", urlParser.getDocument());
    }

    @Test
    public void unitTest4() {
        UrlParser urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals(98, urlParser.getPort());
        Assert.assertEquals("http", urlParser.getStrProtocol());
        Assert.assertEquals("www.goo.sor.ggle.com", urlParser.getDomain());
        Assert.assertEquals("e/oc", urlParser.getDocument());
    }

    @Test
    public void unitTest5() {
        UrlParser urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals(98, urlParser.getPort());
        Assert.assertEquals("http", urlParser.getStrProtocol());
        Assert.assertEquals("www.goo.sor.ggle.com", urlParser.getDomain());
        Assert.assertEquals("e/oc", urlParser.getDocument());
    }

    @Test
    public void getPort() {
    }

    @Test
    public void getDocument() {
    }

    @Test
    public void getStrProtocol() {
    }
}