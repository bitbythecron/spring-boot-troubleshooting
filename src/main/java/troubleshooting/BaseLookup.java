package troubleshooting;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Abstract base lookup entity.
 */
@MappedSuperclass
public abstract class BaseLookup extends BaseEntity {
    @NotNull
    protected String name;

    @NotNull
    protected String label;

    @NotNull
    protected String description;

    public BaseLookup() {
    }

    public BaseLookup(Long id, String refId, String name, String label, String description) {
        super(id, refId);
        this.name = name;
        this.label = label;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
