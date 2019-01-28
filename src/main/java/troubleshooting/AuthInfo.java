package troubleshooting;

public class AuthInfo {
    private String machineId;
    private String machineKey;

    public AuthInfo(String machineId, String machineKey) {
        super();

        this.machineId = machineId;
        this.machineKey = machineKey;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineKey() {
        return machineKey;
    }

    public void setMachineKey(String machineKey) {
        this.machineKey = machineKey;
    }
}
