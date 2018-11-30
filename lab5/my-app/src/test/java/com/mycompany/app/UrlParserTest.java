package com.mycompany.app;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlParserTest {

    @Test
    public void parseUrl() {
        try {
            UrlParser urlParser = new UrlParser("http:///e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Empty domain", e.getMessage());
        }

        try {
            UrlParser urlParser = new UrlParser("http://ww.ggg.com:sgsg/e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect port", e.getMessage());
        }

        try {
            UrlParser urlParser = new UrlParser("http://ww.ggg.com:443/e////oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect document", e.getMessage());
        }

        try {
            UrlParser urlParser = new UrlParser("http://ww.ggg.com:50000/e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Value of port is out of range", e.getMessage());
        }

        try {
            UrlParser urlParser = new UrlParser("http://ww.ggg.com:-3/e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Value of port is out of range", e.getMessage());
        }

        try {
            UrlParser urlParser = new UrlParser("");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Empty url", e.getMessage());
        }

        try {
            UrlParser urlParser = new UrlParser("aifabfajb");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect url", e.getMessage());
        }

        UrlParser urlParser = new UrlParser("https://ww.ggg.com/e/oc");
        Assert.assertEquals(443, urlParser.getPort());
        urlParser = new UrlParser("https://ww.ggg.com:10");
        Assert.assertEquals(10, urlParser.getPort());
        urlParser = new UrlParser("http://ww.ggg.com/yj");
        Assert.assertEquals(80, urlParser.getPort());
    }

    @Test
    public void createProtocol() {
        try {
            UrlParser urlParser = new UrlParser("htp", "www.google.com", "98", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Invalid type of protocol", e.getMessage());
        }
        try {
            UrlParser urlParser = new UrlParser("htpp", "www.google.com", "98", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Invalid type of protocol", e.getMessage());
        }

        UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "e/oc");
        Assert.assertEquals("http", urlParser.getStrProtocol());
        urlParser = new UrlParser("http://www.google.com");
        Assert.assertEquals("http", urlParser.getStrProtocol());
        urlParser = new UrlParser("https://www.google.com:456/");
        Assert.assertEquals("https", urlParser.getStrProtocol());
        urlParser = new UrlParser("https://wm:456/");
        Assert.assertEquals("https", urlParser.getStrProtocol());
        urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals("http", urlParser.getStrProtocol());
        urlParser = new UrlParser("https://www.google.com");
        Assert.assertEquals("https", urlParser.getStrProtocol());
        urlParser = new UrlParser("https", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals("https", urlParser.getStrProtocol());
    }

    @Test
    public void createDomain() {
        try {
            UrlParser urlParser = new UrlParser("http", "txtjycjyc/ex", "98", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect domain", e.getMessage());
        }
        try {
            UrlParser urlParser = new UrlParser("http", "", "98", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect domain", e.getMessage());
        }
        try {
            UrlParser urlParser = new UrlParser("http", "&@@@@@@#", "98", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect domain", e.getMessage());
        }
        UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "e/oc");
        Assert.assertEquals("www.google.com", urlParser.getDomain());
        urlParser = new UrlParser("http://www.google.com");
        Assert.assertEquals("www.google.com", urlParser.getDomain());
        urlParser = new UrlParser("https://www.google.com:456/");
        Assert.assertEquals("www.google.com", urlParser.getDomain());
        urlParser = new UrlParser("https://wm:456/");
        Assert.assertEquals("wm", urlParser.getDomain());
        urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals("www.goo.sor.ggle.com", urlParser.getDomain());
    }

    @Test
    public void createPort() {
        try {
            UrlParser urlParser = new UrlParser("http", "www.google.com", "wrfs", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect port", e.getMessage());
        }
        try {
            UrlParser urlParser = new UrlParser("http", "www.google.com", "55555", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Value of port is out of range", e.getMessage());
        }
        try {
            UrlParser urlParser = new UrlParser("http", "www.google.com", "-55555", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Value of port is out of range", e.getMessage());
        }
        UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "e/oc");
        Assert.assertEquals(98, urlParser.getPort());
        urlParser = new UrlParser("http://www.google.com");
        Assert.assertEquals(80, urlParser.getPort());
        urlParser = new UrlParser("https://www.google.com:456/");
        Assert.assertEquals(456, urlParser.getPort());
        urlParser = new UrlParser("https", "www.google.com", "");
        Assert.assertEquals(443, urlParser.getPort());
        urlParser = new UrlParser("https://wm:456/");
        Assert.assertEquals(456, urlParser.getPort());
        urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals(98, urlParser.getPort());
        urlParser = new UrlParser("https://www.google.com");
        Assert.assertEquals(443, urlParser.getPort());
    }

    @Test
    public void createDocument() {
        try {
            UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "//");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Incorrect document", e.getMessage());
        }

        UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "e/oc");
        Assert.assertEquals("e/oc", urlParser.getDocument());
        urlParser = new UrlParser("http://www.google.com");
        Assert.assertEquals("/", urlParser.getDocument());
        urlParser = new UrlParser("https://www.google.com:456/");
        Assert.assertEquals("/", urlParser.getDocument());
        urlParser = new UrlParser("https://wm:456/");
        Assert.assertEquals("/", urlParser.getDocument());
        urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals("e/oc", urlParser.getDocument());
        urlParser = new UrlParser("https://www.google.com");
        Assert.assertEquals("/", urlParser.getDocument());
    }

    @Test
    public void getUrl() {
        UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "e/oc");
        Assert.assertEquals("http://www.google.com:98/e/oc", urlParser.getUrl());
        urlParser = new UrlParser("http://www.google.com");
        Assert.assertEquals("http://www.google.com", urlParser.getUrl());
        urlParser = new UrlParser("https://www.google.com:456/");
        Assert.assertEquals("https://www.google.com:456/", urlParser.getUrl());
        urlParser = new UrlParser("https://wm:456/");
        Assert.assertEquals("https://wm:456/", urlParser.getUrl());
        urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals("http://www.goo.sor.ggle.com:98/e/oc", urlParser.getUrl());
        urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "e/oc");
        Assert.assertEquals("http://www.goo.sor.ggle.com:80/e/oc", urlParser.getUrl());
    }

    @Test
    public void getDomain() {
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