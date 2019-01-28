package troubleshooting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TroubleshootingConfig troubleshootingConfig;

    @Autowired
    private Fizzbuzz fizzbuzz;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Fizz says: {}", fizzbuzz.getSomething());
        log.info("Machine key is: {}", troubleshootingConfig.getMachine().getKey());
    }
}
