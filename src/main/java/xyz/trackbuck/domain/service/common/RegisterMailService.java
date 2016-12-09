package xyz.trackbuck.domain.service.common;

import com.google.common.collect.Lists;
import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.ImageType;
import it.ozimov.springboot.templating.mail.model.InlinePicture;
import it.ozimov.springboot.templating.mail.model.impl.EmailImpl;
import it.ozimov.springboot.templating.mail.model.impl.InlinePictureImpl;
import it.ozimov.springboot.templating.mail.service.exception.CannotSendEmailException;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import xyz.trackbuck.domain.model.common.Messageble;
import xyz.trackbuck.domain.model.user.UnconfirmedUser;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lex on 08.10.16.
 */
@Service
public class RegisterMailService extends AbstractMailService {

    private static final String SUBJECT_MAIL_REGISTRATION_CONFIRMATION = "Потверждение регистрации";

    @Override
    public void send(Messageble o) throws AddressException {
        UnconfirmedUser user = (UnconfirmedUser) o;
        Map model = new HashMap<>();
        model.put("user", user);
        final Email email = EmailImpl.builder()
                .from(new InternetAddress(MAIL_FROM))
                .to(Lists.newArrayList(new InternetAddress(user.getEmail())))
                .subject(SUBJECT_MAIL_REGISTRATION_CONFIRMATION)
                .body(VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), "registration-confirmation.vm", CHARSET_UTF8, model))
                .encoding(Charset.forName(CHARSET_UTF8)).build();

        getEmailService().send(email);
    }









    /*@Override
    protected MimeMessagePreparator prepareMessage(MimeMessageHelper helper, Messageble o) throws MessagingException {
        MimeMessagePreparator preparator = mimeMessage -> {
            UnconfirmedUser user = (UnconfirmedUser) o;
            helper.setTo(user.getEmail());
            helper.setSubject(SUBJECT_MAIL_REGISTRATION_CONFIRMATION);
            helper.setFrom(MAIL_FROM);
            Map model = new HashMap<>();
            model.put("user", user);
            helper.setText("Хуй", true);

        };
        return preparator;


    }*/


}

