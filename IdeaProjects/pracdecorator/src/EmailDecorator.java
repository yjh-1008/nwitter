public abstract class EmailDecorator extends EmailContent {
    private EmailContent decorated;

    public EmailDecorator(EmailContent decorated) {
        this.decorated = decorated;
    }

    public String getContent() {
      return decorated.getContent();
    }
}
