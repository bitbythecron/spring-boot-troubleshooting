package troubleshooting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TroubleshootingInjector implements ApplicationContextAware {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    @Autowired
    private TroubleshootingConfig troubleshootingConfig;

    @Bean
    public AuthInfo authInfo() {
        return new AuthInfo(troubleshootingConfig.getMachine().getId(), troubleshootingConfig.getMachine().getKey());
    }

    @Bean
    public Fizzbuzz fizzbuzz() {
        return new Fizzbuzz("derp", -1L);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}