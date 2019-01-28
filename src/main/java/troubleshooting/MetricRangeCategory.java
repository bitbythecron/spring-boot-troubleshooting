package troubleshooting;

import javax.persistence.*;

/**
 * Name of a particular metric range (Healthy, Unhealthy, Critical, etc.).
 */
@Entity
@Table(name = "metric_range_categories")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "metric_range_category_id")),
        @AttributeOverride(name = "refId", column = @Column(name = "metric_range_category_ref_id")),
        @AttributeOverride(name = "name", column = @Column(name = "metric_range_category_name")),
        @AttributeOverride(name = "label", column = @Column(name = "metric_range_category_label")),
        @AttributeOverride(name = "description", column = @Column(name = "metric_range_category_description"))
})
public class MetricRangeCategory extends BaseLookup {
    public MetricRangeCategory() {
    }

    public MetricRangeCategory(Long id, String refId, String name, String label, String description) {
        super(id, refId, name, label, description);
    }
}
