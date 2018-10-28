import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlParserTest {

    @Test
    public void UrlParser(){
        try{
            UrlParser urlParser = new UrlParser("htp", "www.google.com", "98", "e/oc");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Invalid type of protocol", e.getMessage());
        }
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
    }

    @Test
    public void getDomain() {
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
    public void getPort() {
        UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "e/oc");
        Assert.assertEquals(98, urlParser.getPort());
        urlParser = new UrlParser("http://www.google.com");
        Assert.assertEquals(80, urlParser.getPort());
        urlParser = new UrlParser("https://www.google.com:456/");
        Assert.assertEquals(456, urlParser.getPort());
        urlParser = new UrlParser("https://wm:456/");
        Assert.assertEquals(456, urlParser.getPort());
        urlParser = new UrlParser("http", "www.goo.sor.ggle.com", "98", "e/oc");
        Assert.assertEquals(98, urlParser.getPort());
        urlParser = new UrlParser("https://www.google.com");
        Assert.assertEquals(443, urlParser.getPort());
    }

    @Test
    public void getDocument() {
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
    public void getStrProtocol() {
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
    }
}