package ui.utils;

import ui.TestContext;
import ui.models.TestData;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SubjectTerm;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailReader {
    TestData testData;
    public GmailReader(TestData testData){
        this.testData = testData;
    }
    public String getQuickbaseOTP() {
        String email = testData.getEmail();
        String appPassword = testData.getAppPassword();
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", email, appPassword);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Search for the specific subject
            Message[] messages = inbox.search(new SubjectTerm(testData.getEmailSubject()));
            
            if (messages.length == 0) return null;

            // Get the newest email
            Message latestMessage = messages[messages.length - 1];
            String content = getTextFromMessage(latestMessage);

            // Regex to find the Alphanumeric Code (e.g., B4V898)
            Pattern pattern = Pattern.compile("sign in:\\s*([A-Z0-9]{6})");
            Matcher matcher = pattern.matcher(content);

            if (matcher.find()) {
                return matcher.group(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
                break; // Often the plain text part is enough
            } else if (bodyPart.isMimeType("text/html")) {
                // If there's no plain text, take the HTML and strip tags later if needed
                result.append(bodyPart.getContent());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }
}