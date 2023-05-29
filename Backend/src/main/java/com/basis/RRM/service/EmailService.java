package com.basis.RRM.service;

import com.basis.RRM.config.ApplicationProperties;
import com.basis.RRM.service.dto.EmailDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Getter
@Setter
@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final ApplicationProperties applicationProperties;

    @SneakyThrows
    public void enviaEmail(EmailDTO dto){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mime = new MimeMessageHelper(message, false, "UTF-8");
        mime.setTo(dto.getDestinatario());
        mime.setFrom(applicationProperties.enderecoRemetente);
        mime.setSubject(dto.getAssunto());
        for(String s: dto.getCopias()){
            mime.addCc(s);
        }
        mime.setText(dto.getCorpo());
        javaMailSender.send(message);
    }
}
