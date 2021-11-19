public class SecureEMailContent extends BasicEMailContent {

    public SecureEMailContent(String content) {
        super(content);
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
