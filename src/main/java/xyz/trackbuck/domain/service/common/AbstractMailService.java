package xyz.trackbuck.domain.service.common;

import it.ozimov.springboot.templating.mail.service.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import xyz.trackbuck.domain.model.common.Messageble;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by lex on 08.10.16.
 */
@Getter
@Setter
public abstract class AbstractMailService implements MailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VelocityEngine velocityEngine;

    protected static final String CHARSET_UTF8 = "UTF-8";
    protected static final String MAIL_FROM="noreply@cashtrack.xyz";



    /*public void send(Messageble o){
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessagePreparator mimeMessagePreparator;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            mimeMessagePreparator = this.prepareMessage(helper, o);
            mailSender.send(mimeMessagePreparator);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    };


    protected abstract MimeMessagePreparator prepareMessage(MimeMessageHelper helper, Messageble o) throws MessagingException;*/

}
