public class SecureEmailContent extends EmailDecorator {

    public SecureEmailContent(EmailContent decorated) {
        super(decorated);
    }

    public String getContent() {
        String content = super.getContent();
        String encryptedContent = encrypt(content);
        return encryptedContent;
    }

    private String encrypt(String content) {
        return content + "Encrypted";
    }
}
