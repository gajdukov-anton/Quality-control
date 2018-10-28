public class Main {
    public static void main(String[] args) {
        try{
            UrlParser urlParser = new UrlParser("http", "www.google.com", "98", "e/oc");
            System.out.println(urlParser.getUrl());
            System.out.println(urlParser.getStrProtocol());
            System.out.println(urlParser.getDomain());
            System.out.println(urlParser.getPort());
            System.out.println(urlParser.getDocument());
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
        }
    }

}
