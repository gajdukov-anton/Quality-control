package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Protocol {
    HTTP, HTTPS
}

public class UrlParser {
    private String url;
    private String domain;
    private String document;
    private int port;
    private Protocol protocol;

    public UrlParser(String url) throws IllegalArgumentException {
        this.url = url;
        parseUrl(url);
    }

    public UrlParser(String protocol, String domain, String port, String document) {
        createProtocol(protocol);
        createDomain(domain);
        createDocument(document);
        createPort(port);
        this.url = getStrProtocol() + "://" + getDomain() + ":" + getPort() + "/" + getDocument();
//        parseUrl(url);
    }

    public UrlParser(String protocol, String domain, String document) {
        createProtocol(protocol);
        createDomain(domain);
        createDocument(document);
        createPort("");
        this.url = getStrProtocol() + "://" + getDomain() + ":" + getPort() + "/" + getDocument();
    }

    private void parseUrl(String url) throws IllegalArgumentException {
        if (url.isEmpty()) {
            throw new IllegalArgumentException("Empty url");
        }

        Pattern pattern = Pattern.compile("^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher m = pattern.matcher(url);
        if (!m.find()) {
            throw new IllegalArgumentException("Incorrect url");
        }
        createProtocol();
        createDomain();
        createPort();
        createDocument();
    }

    private void createProtocol(String protocolStr) throws IllegalArgumentException {
        if (protocolStr.equals("http")) {
            this.protocol = Protocol.HTTP;
        } else if (protocolStr.equals("https")){
            this.protocol = Protocol.HTTPS;
        } else {
            throw new IllegalArgumentException("Invalid type of protocol");
        }
    }

    private void createDomain(String domain) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("([A-Za-z0-9]{1,}[\\-]{0,1}[A-Za-z0-9]{1,}[\\.]{0,1}[A-Za-z0-9]{1,})+");
        Matcher m = pattern.matcher(domain);
        if (!m.find() || domain.contains("/")) {
            throw new IllegalArgumentException("Incorrect domain");
        } else {
            this.domain = domain;
        }
    }

    private void createDocument(String document) throws IllegalArgumentException {
        if (document.contains("//")) {
            throw new IllegalArgumentException("Incorrect document");
        } else {
            this.document =  document;
        }
    }

    private void createPort(String port) throws IllegalArgumentException {
        if (this.protocol ==Protocol.HTTP && port.isEmpty()) {
            this.port = 80;
        } else if (this.protocol ==Protocol.HTTPS && port.isEmpty()) {
            this.port = 443;
        }
        try {
            this.port = new Integer(port);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect port");
        }
        if (!(this.port > 0 && this.port < 3000)) {
            throw new IllegalArgumentException("Value of port is out of range");
        }
    }

    private void createProtocol() throws IllegalArgumentException {
        String protocolStr = url.substring(0, 5);
        if (protocolStr.equals("https")) {
            this.protocol = Protocol.HTTPS;
        } else if (protocolStr.equals("http:")) {
            this.protocol = Protocol.HTTP;
        } else {
            throw new IllegalArgumentException("Invalid type of protocol");
        }
    }

    private void createDomain() {
        int pos = url.indexOf("://") + 3;
        String str = url.substring(pos);
        String domainStr;
        if (str.contains(":")) {
            pos = str.indexOf(":");
            domainStr = str.substring(0, pos);
        } else if (str.contains("/")){
            pos = str.indexOf("/");
            domainStr = str.substring(0, pos);
        } else {
            domainStr = str;
        }
        if (domainStr.isEmpty()) {
            throw new IllegalArgumentException("Empty domain");
        } else {
            this.domain = domainStr;
        }
    }

    private void createPort() throws IllegalArgumentException {
        int pos = url.indexOf(domain) + domain.length();
        if (pos != url.length()) {
            if (url.charAt(pos) != ':' && protocol == Protocol.HTTP) {
                this.port = 80;
            } else if (url.charAt(pos) != ':' && protocol == Protocol.HTTPS) {
                this.port = 443;
            } else {
                try {
                    String str = url.substring(pos);
                    String portStr = "";
                    char symbol = ' ';
                    int i = 1;
                    while (symbol != '/' && i < str.length()) {
                        symbol = str.charAt(i);
                        if (symbol != '/') {
                            portStr += symbol;
                        }
                        i++;
                    }
                    this.port = new Integer(portStr);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Incorrect port");
                }
                if (!(port > 0 && port < 3000)) {
                    throw new IllegalArgumentException("Value of port is out of range");
                }
            }
        } else if (protocol == Protocol.HTTP) {
            this.port = 80;
        } else if (protocol == Protocol.HTTPS) {
            this.port = 443;
        }
    }

    private void createDocument() {
        String str = url.substring(url.indexOf("://") + 3);
        int pos = str.indexOf('/');
        if (pos == -1) {
            this.document = "/";
        } else {
            String strDocument = str.substring(pos);
            if (strDocument.contains("//")) {
                throw new IllegalArgumentException("Incorrect document");
            } else {
                this.document =  strDocument;
            }
        }
    }

    public String getUrl() {
        return url;
    }


    public String getDomain() {
        return domain;
    }

    public int getPort() {
        return port;
    }

    public String getDocument() {
        return document;
    }

    public String getStrProtocol() {
        if (protocol == Protocol.HTTP) {
            return "http";
        } else {
            return "https";
        }
    }
}