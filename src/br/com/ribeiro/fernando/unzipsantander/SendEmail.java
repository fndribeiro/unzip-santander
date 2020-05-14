package br.com.ribeiro.fernando.unzipsantander;

import javax.activation.DataSource;
import java.util.List;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.util.Arrays;
import java.io.File;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import java.util.Properties;
import java.time.LocalDate;

public class SendEmail {
	static final LocalDate today;

	static {
		today = LocalDate.now();
	}

	public static void sendAttachment() {
		final Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		final Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailConfig.username, EmailConfig.password);
			}
		});
		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EmailConfig.from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EmailConfig.to));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(EmailConfig.toCc));
			message.setSubject(EmailConfig.subject + SendEmail.today);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(EmailConfig.bodyText);
			final Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			final File path = new File(Path.getOutput());
			final List<File> listOfFiles = Arrays.asList(path.listFiles());
			for (final File x : listOfFiles) {
				messageBodyPart = new MimeBodyPart();
				final DataSource source = new FileDataSource(x);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(x.getName());
				multipart.addBodyPart(messageBodyPart);
			}
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("E-mail enviado para" + EmailConfig.to + ".");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
