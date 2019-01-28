package troubleshooting;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "troubleshooting")
public class TroubleshootingConfig {
    private Machine machine;
    private Integer maxChildRestarts;

    public Integer getMaxChildRestarts() {
        return maxChildRestarts;
    }

    public void setMaxChildRestarts(Integer maxChildRestarts) {
        this.maxChildRestarts = maxChildRestarts;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public static class Machine {
        private String id;
        private String key;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

}
