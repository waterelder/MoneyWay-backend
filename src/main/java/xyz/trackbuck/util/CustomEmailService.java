package xyz.trackbuck.util;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.InlinePicture;
import it.ozimov.springboot.templating.mail.service.EmailService;
import it.ozimov.springboot.templating.mail.service.EmailServiceImpl;
import it.ozimov.springboot.templating.mail.service.TemplateService;
import it.ozimov.springboot.templating.mail.service.exception.CannotSendEmailException;
import it.ozimov.springboot.templating.mail.service.exception.TemplateException;
import it.ozimov.springboot.templating.mail.utils.EmailToMimeMessage;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lex on 25.10.16.
 */
public class CustomEmailService implements EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
    private JavaMailSender javaMailSender;
    private TemplateService templateService;
    private EmailToMimeMessage emailToMimeMessage;

    @Autowired(
            required = false
    )
    public CustomEmailService(@NonNull JavaMailSender javaMailSender, TemplateService templateService, @NonNull EmailToMimeMessage emailToMimeMessage) {
        if(javaMailSender == null) {
            throw new NullPointerException("javaMailSender");
        } else if(emailToMimeMessage == null) {
            throw new NullPointerException("emailToMimeMessage");
        } else {
            this.javaMailSender = javaMailSender;
            this.templateService = templateService;
            this.emailToMimeMessage = emailToMimeMessage;
        }
    }

    @Autowired(
            required = false
    )
    public CustomEmailService(@NonNull JavaMailSender javaMailSender, @NonNull EmailToMimeMessage emailToMimeMessage) {
        this(javaMailSender, (TemplateService)null, emailToMimeMessage);
        if(javaMailSender == null) {
            throw new NullPointerException("javaMailSender");
        } else if(emailToMimeMessage == null) {
            throw new NullPointerException("emailToMimeMessage");
        }
    }

    public MimeMessage send(@NonNull Email email) {
        if(email == null) {
            throw new NullPointerException("email");
        } else {
            email.setSentAt(new Date());
            MimeMessage mimeMessage = this.toMimeMessage(email);
            this.javaMailSender.send(mimeMessage);
            return mimeMessage;
        }
    }

    public MimeMessage send(@NonNull Email email, @NonNull String template, Map<String, Object> modelObject, InlinePicture... inlinePictures) throws CannotSendEmailException {
        if(email == null) {
            throw new NullPointerException("email");
        } else {
            email.setSentAt(new Date());
            MimeMessage mimeMessage = this.toMimeMessage(email);

            try {
                MimeMultipart e = new MimeMultipart("related");
                String text = this.templateService.mergeTemplateIntoString(template, (Map) Optional.fromNullable(modelObject).or(ImmutableMap.of()));
                InlinePicture[] textPart = inlinePictures;
                int var9 = inlinePictures.length;

                /*for(int var10 = 0; var10 < var9; ++var10) {
                    InlinePicture inlinePicture = textPart[var10];
                    String cid = UUID.randomUUID().toString();
                    text = text.replace(inlinePicture.getTemplateName(), "cid:" + cid);
                    MimeBodyPart imagePart = new MimeBodyPart();
                    imagePart.attachFile(inlinePicture.getFile());
                    imagePart.setContentID('<' + cid + '>');
                    imagePart.setDisposition("inline");
                    imagePart.setHeader("Content-Type", inlinePicture.getImageType().getContentType());
                    e.addBodyPart(imagePart);
                }*/

                MimeBodyPart var17 = new MimeBodyPart();
                var17.setText(text, email.getEncoding().displayName(), "html");
                e.addBodyPart(var17);
                mimeMessage.setContent(e);
                this.javaMailSender.send(mimeMessage);
                return mimeMessage;
            } catch (IOException var14) {
                log.error("The template file cannot be read", var14);
                throw new CannotSendEmailException("Error while sending the email due to problems with the template file", var14);
            } catch (TemplateException var15) {
                log.error("The template file cannot be processed", var15);
                throw new CannotSendEmailException("Error while processing the template file with the given model object", var15);
            } catch (MessagingException var16) {
                log.error("The mime message cannot be created", var16);
                throw new CannotSendEmailException("Error while sending the email due to problems with the mime content", var16);
            }
        }
    }

    private MimeMessage toMimeMessage(@NotNull Email email) {
        return this.emailToMimeMessage.apply(email);
    }
}
