package troubleshooting;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.event.ContextRefreshedEvent;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StartupListenerTest {
    @Autowired
    private StartupListener startupListener;

    @MockBean
    private TroubleshootingConfig troubleshootingConfig;

    @MockBean
    private Fizzbuzz fizzbuzz;

    @MockBean
    private TroubleshootingConfig.Machine machine;

    @MockBean
    private ContextRefreshedEvent event;

    @Test
    public void should_do_something() {
        when(troubleshootingConfig.getMachine()).thenReturn(machine);
        when(fizzbuzz.getFoobarId()).thenReturn(2L);
        when(machine.getKey()).thenReturn("FLIM FLAM!");

        // when
        startupListener.onApplicationEvent(event);

        // then
        verify(machine).getKey();
    }
}
