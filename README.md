# Program to download UUencoded e-mails, unzip and send the attachments to a recipient.

This service was develop to automate a job routine that I had where I had to download a specific e-mail everyday, download it's attachments, unzip and then send
the unziped content to my boss. Others employees had to this routine as well, so it was really helpful.

This project was built with JavaMail for e-mail handling, zip4j from @srikanth-lingala for zip files, Java Swing for the UI and MimeUtility to decode uuencoded 
e-mails.

In order to run this program you just have to set all parameters in EmailConfig.java class.

```java
public class EmailConfig {
	
	static final String to = "";
    static final String toCc = "";
    static final String from = "";
    static final String username = "";
    static final String password = "";
    static final String subject = "";
    static final String bodyText = "";
    static final String searchTerm = "";    
}
```
- Where searchTerm will search for a specific subject.

And then export a runnable .jar file to run it's main class:

```java
package br.com.ribeiro.fernando.unzipsantander;

public class MainMethod {
	public static void main(final String[] args) throws Exception {
		new SwingConsole().createPanel();
		StartButtonMethod.Start();
	}
}
```


