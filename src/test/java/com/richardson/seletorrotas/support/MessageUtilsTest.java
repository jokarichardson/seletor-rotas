package com.richardson.seletorrotas.support;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class MessageUtilsTest {
    @InjectMocks
    private MessageUtils messageUtils;
    
    @Mock
    private MessageSource messageSource;
    
    @Before
    public void prepare() {
        initMocks(this);
        this.messageUtils.init();
    }
    
    @Test
    public void shouldGetAStringMessage() {
        String expectedMessage = "Testando messageUtils";
        
        when(this.messageSource.getMessage(any(), any(), any())).thenReturn(expectedMessage);
        
        String actualMessage = this.messageUtils.get("mensagem-configurada");
    
        assertEquals(expectedMessage, actualMessage);
        
        verify(this.messageSource, times(1)).getMessage(any(), any(), any());
    }
}
