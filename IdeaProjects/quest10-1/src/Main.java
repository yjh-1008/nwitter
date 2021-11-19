public class Main {
    public static void main(String[] args) {
        BasicEMailContent simple = new BasicEMailContent("Hello");
        System.out.println(simple.getContent());

        ExternalEMailContent external = new ExternalEMailContent("Hello");
        System.out.println(external.getContent());

        SecureEMailContent secure = new SecureEMailContent("Hello");
        System.out.println(secure.getContent());
    }
}
