package com.richardson.seletorrotas.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageUtils {
    @Autowired
    private MessageSource messageSource;
    
    private MessageSourceAccessor accessor;
    
    @PostConstruct
    public void init() {
        this.accessor = new MessageSourceAccessor(this.messageSource);
    }
    
    public String get(String code) {
        return accessor.getMessage(code);
    }
}