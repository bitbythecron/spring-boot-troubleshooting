package troubleshooting;

import javax.persistence.*;

/**
 * Type of metric (Gauage, Counter, Timer, Meter, etc.).
 */
@Entity
@Table(name = "metric_types")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "metric_type_id")),
        @AttributeOverride(name = "refId", column = @Column(name = "metric_type_ref_id")),
        @AttributeOverride(name = "name", column = @Column(name = "metric_type_name")),
        @AttributeOverride(name = "label", column = @Column(name = "metric_type_label")),
        @AttributeOverride(name = "description", column = @Column(name = "metric_type_description"))
})
public class MetricType extends BaseLookup {
    public MetricType() {
    }

    public MetricType(Long id, String refId, String name, String label, String description) {
        super(id, refId, name, label, description);
    }
}
