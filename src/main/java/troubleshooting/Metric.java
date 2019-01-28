package troubleshooting;

import javax.persistence.*;
import java.util.List;

/**
 * A particular metric or stat that describes the operating function, and indirectly, the health of a
 * particular I/O Device.
 */
@Entity
@Table(name = "metrics")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "metric_id")),
    @AttributeOverride(name = "refId", column = @Column(name = "metric_ref_id"))
})
public class Metric extends BaseEntity {
    // Metric name as it will appear in metrics monitoring platforms
    @Column(name = "metric_moniker")
    private String moniker;

    // Type of metric (Gauge, Meter, Counter, Timer)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "metric_type_id", referencedColumnName = "metric_type_id")
    private MetricType type;

    // Unit of measure the metric utilizes
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "metric_unit_id", referencedColumnName = "metric_unit_id")
    private MetricUnit unit;

    // Defined health ranges for the metric
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "metric_x_metric_ranges",
        joinColumns = {
            @JoinColumn(name = "metric_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "metric_range_id")
        }
    )
    private List<MetricRange> ranges;

    public Metric(Long id, String refId) {
        super(id, refId);
    }

    public Metric(Long id, String refId, String moniker, MetricType type, MetricUnit unit, List<MetricRange> ranges) {
        super(id, refId);

        this.moniker = moniker;
        this.type = type;
        this.unit = unit;
        this.ranges = ranges;
    }

    public String getMoniker() {
        return moniker;
    }

    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }

    public MetricType getType() {
        return type;
    }

    public void setType(MetricType type) {
        this.type = type;
    }

    public MetricUnit getUnit() {
        return unit;
    }

    public void setUnit(MetricUnit unit) {
        this.unit = unit;
    }

    public List<MetricRange> getRanges() {
        return ranges;
    }

    public void setRanges(List<MetricRange> ranges) {
        this.ranges = ranges;
    }
}
