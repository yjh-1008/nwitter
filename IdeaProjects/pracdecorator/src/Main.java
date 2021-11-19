import sun.security.util.SecurityConstants;

public class Main {
    public static void main(String[] args) {
        BasicEmailContent b = new BasicEmailContent("Hello");
        ExternalEmailContent e = new ExternalEmailContent(b);
        SecureEmailContent content = new SecureEmailContent(e);
        System.out.println(content.getContent());

        BasicEmailContent b1 = new BasicEmailContent("Hello");
        SecureEmailContent s1 = new SecureEmailContent(b1);
        ExternalEmailContent e1 = new ExternalEmailContent(s1);
        System.out.println(e1.getContent());

    }
}
