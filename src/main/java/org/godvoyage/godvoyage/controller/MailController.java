package org.godvoyage.godvoyage.controller;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @ResponseBody
    @PostMapping("/mail")
    public String MailSend(@RequestParam("email") String email){
        System.out.println(email);
       int number = mailService.sendMail(email);

        String num = "" + number;

        return num;

    }
}
