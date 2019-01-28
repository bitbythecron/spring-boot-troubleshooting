package troubleshooting;

import javax.persistence.*;

/**
 * Unit of measure for a particular metric.
 */
@Entity
@Table(name = "metric_units")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "metric_unit_id")),
        @AttributeOverride(name = "refId", column = @Column(name = "metric_unit_ref_id")),
        @AttributeOverride(name = "name", column = @Column(name = "metric_unit_name")),
        @AttributeOverride(name = "label", column = @Column(name = "metric_unit_label")),
        @AttributeOverride(name = "description", column = @Column(name = "metric_unit_description"))
})
public class MetricUnit extends BaseLookup {
    public MetricUnit() {
    }

    public MetricUnit(Long id, String refId, String name, String label, String description) {
        super(id, refId, name, label, description);
    }
}
