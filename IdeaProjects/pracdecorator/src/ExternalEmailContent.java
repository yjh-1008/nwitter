public class ExternalEmailContent extends EmailDecorator {

    public ExternalEmailContent(EmailContent decorated) {
        super(decorated);
    }

    public String getContent() {
        String content = super.getContent();
        String externalContent = addDisclaimer(content);
        return externalContent;
    }

    private String addDisclaimer(String content) {
        return content + "Company Disclaimer";
    }
}
