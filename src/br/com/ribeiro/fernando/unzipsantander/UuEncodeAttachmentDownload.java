// 
// Decompiled by Procyon v0.5.36
// 

package br.com.ribeiro.fernando.unzipsantander;

import java.io.InputStream;
import javax.mail.Message;
import javax.mail.Folder;
import javax.mail.Store;
import java.io.FileOutputStream;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SearchTerm;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.Flags;
import javax.mail.Address;
import javax.mail.search.FromTerm;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import java.util.Properties;

public class UuEncodeAttachmentDownload
{
    public static void downloadAttachment() throws Exception {
        final Session session = Session.getDefaultInstance(new Properties());
        final Store store = session.getStore("imaps");
        store.connect("imap.googlemail.com", 993, EmailConfig.username, EmailConfig.password);
        final Folder inbox = store.getFolder("INBOX");
        inbox.open(2);
        final SearchTerm sender = new FromTerm(new InternetAddress(EmailConfig.searchTerm));
        final Flags seen = new Flags(Flags.Flag.SEEN);
        final FlagTerm unseen = new FlagTerm(seen, false);
        final SearchTerm term = new AndTerm(unseen, sender);
        final Message[] messages = inbox.search(term);
        System.out.println("Emails encontrados: " + messages.length);
        if (messages.length == 0) {
            throw new EmailNotFoundException("Nenhum email encontrado.");
        }
        Message[] array;
        for (int length = (array = messages).length, i = 0; i < length; ++i) {
            final Message message = array[i];
            try {
                final InputStream input = MimeUtility.decode(message.getInputStream(), "uuencode");
                final String destinationFilePath = String.valueOf(Path.getInput()) + message.getSubject() + ".zip";
                final FileOutputStream output = new FileOutputStream(destinationFilePath);
                System.out.println("Salvando " + message.getSubject() + "...");
                final byte[] buffer = new byte[4096];
                int byteRead;
                while ((byteRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, byteRead);
                }
                output.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
