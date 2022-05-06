package com.cqjtu.mindassess.config.asrtconf;

import com.cqjtu.mindassess.asrt.BaseSpeechRecognizer;
import com.cqjtu.mindassess.asrt.Sdk;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangning
 */
@Configuration
public class AsrtConfig {

    @Bean
    public BaseSpeechRecognizer baseSpeechRecognizer(@Qualifier("asrtProperties") AsrtProperties asrtProperties){
        BaseSpeechRecognizer baseSpeechRecognizer = Sdk.GetSpeechRecognizer(
                asrtProperties.getHost(),
                asrtProperties.getPort(),
                asrtProperties.getProtocol());

        return baseSpeechRecognizer;
    }
}
