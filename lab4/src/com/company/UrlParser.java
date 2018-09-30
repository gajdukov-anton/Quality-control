package com.company;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class UrlParser {
    private ArrayList<Link> links = new ArrayList<>();
    private ArrayList<Link> brokenLinks = new ArrayList<>();
    private org.jsoup.Connection.Response response = null;
    private String baseUrl;

    private class Link {
        public Link() {
        }

        public Link(String str, int code) {
            this.code = code;
            this.str = str;
        }

        public String str;
        public int code;
    }

    UrlParser(String link) throws IllegalArgumentException {
        if (isUrl(link)) {
            this.baseUrl = link;
            findLink(link);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isContain(Link link) {
        for (Link elem : links) {
            if (elem.str.equals(link.str)) {
                return true;
            }
        }
        return false;
    }

    private void findLink(String strLink) {
        try {
            Link link = new Link(strLink, 0);
            if (!isContain(link)) {
                response = Jsoup.connect(strLink).execute();
                org.jsoup.nodes.Document doc = response.parse();
                link.code = response.statusCode();
                links.add(link);
                Elements newLinks = doc.getElementsByTag("a");
                for (Element elem : newLinks) {
                    if (isContainBaseUrl(elem.absUrl("href"))) {
                        if (isUrl(elem.absUrl("href"))) {
                            findLink(elem.absUrl("href"));
                        } else {
                            if (!isContain(new Link(elem.toString(), 0))) {
                                links.add(new Link(elem.toString(), 0));
                                brokenLinks.add(new Link(elem.toString(), 0));
                            }
                        }
                    }
                }
            }

        } catch (HttpStatusException e) {
            if (!isContain(new Link(strLink, e.getStatusCode()))) {
                links.add(new Link(strLink, e.getStatusCode()));
                brokenLinks.add(new Link(strLink, e.getStatusCode()));
            }
        } catch (IOException e) {
            //e.getMessage();
        }
    }

    private boolean isContainBaseUrl(String str) {
        if (str.contains(baseUrl)) {
            return true;
        } else {
            return  false;
        }
    }

    private boolean isUrl(String str) {

        try {
            URL url = new URL(str);
            url.toURI();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void writeResultToFile(String strLinkFile, String strBrokenLinkFile) {
        try {
            FileWriter linkFile = new FileWriter(strLinkFile);
            FileWriter brokenLinkFile = new FileWriter(strBrokenLinkFile);
            Date date = new Date();
            for (Link elem : links) {
                linkFile.write(elem.str + ' ' + elem.code + System.lineSeparator());
            }
            linkFile.write(date.toString() + " Size: " + links.size());
            linkFile.close();
            for (Link elem : brokenLinks) {
                brokenLinkFile.write(elem.str + ' ' + elem.code + System.lineSeparator());
            }
            brokenLinkFile.write(date.toString() + " Size: " + brokenLinks.size());
            brokenLinkFile.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showResult() {
        for (Link elem : links) {
            System.out.println(elem.str + ' ' + elem.code);
        }
        System.out.println(links.size());
        for (Link elem : brokenLinks) {
            System.out.println(elem.str + ' ' + elem.code);
        }
    }
}
