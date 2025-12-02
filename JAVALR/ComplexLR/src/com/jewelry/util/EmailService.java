package com.jewelry.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    // --- НАЛАШТУВАННЯ ПОШТИ ---
    private static final String FROM_EMAIL = "yagababa371@gmail.com";
    private static final String PASSWORD = "ijll pmuv lzvb rfsv"; // Google App Password, не звичайний пароль!
    private static final String TO_EMAIL = "yagababa371@gmail.com";

    public static void sendCriticalError(String errorMessage) {
        System.out.println("📧 [EMAIL] Спроба відправки критичної помилки на " + TO_EMAIL + "...");

        // Якщо пароль не вказано, працюємо в режимі імітації (щоб код не падав)
        if (PASSWORD.equals("your_app_password")) {
            System.out.println("⚠️ [EMAIL MOCK] Email не налаштовано. Повідомлення: " + errorMessage);
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
            message.setSubject("CRITICAL ERROR: Jewelry App");
            message.setText("У програмі сталася критична помилка:\n\n" + errorMessage);

            Transport.send(message);
            System.out.println("✅ [EMAIL] Лист успішно відправлено!");

        } catch (MessagingException e) {
            System.err.println("❌ [EMAIL FAILED] Не вдалося відправити лист: " + e.getMessage());
        }
    }
}