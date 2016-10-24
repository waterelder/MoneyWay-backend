package xyz.trackbuck.domain.service.common;

import xyz.trackbuck.domain.model.common.Messageble;

import javax.mail.internet.AddressException;

/**
 * Created by lex on 08.10.16.
 */
public interface MailSender  {
   void send(Messageble o) throws AddressException;
}
