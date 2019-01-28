package troubleshooting;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Defined health ranges for a particular metric.
 */
@Entity
@Table(name = "metric_ranges")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "metric_range_id")),
        @AttributeOverride(name = "refId", column = @Column(name = "metric_range_ref_id"))
})
public class MetricRange extends BaseEntity implements Comparable<MetricRange> {
    // Healthy, Unhealthy, Critical
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "metric_range_category_id", referencedColumnName = "metric_range_category_id")
    private MetricRangeCategory category;

    // Low value of the range
    @Column(name = "metric_range_low", columnDefinition = "DOUBLE")
    private BigDecimal low;

    // High value of the range
    @Column(name = "metric_range_high", columnDefinition = "DOUBLE")
    private BigDecimal high;

    // Whether the low value is inclusive or not
    @Column(name = "metric_range_low_inclusive")
    private Integer lowInclusive;

    // Whether the high value is inclusive or not
    @Column(name = "metric_range_high_inclusive")
    private Integer highInclusive;

    public MetricRange(Long id, String refId) {
        super(id, refId);
    }

    public MetricRange(Long id, String refId, MetricRangeCategory category, BigDecimal low, BigDecimal high,
                       Integer lowInclusive, Integer highInclusive) {
        super(id, refId);
        
        this.category = category;
        this.low = low;
        this.high = high;
        this.lowInclusive = lowInclusive;
        this.highInclusive = highInclusive;
    }

    public MetricRangeCategory getCategory() {
        return category;
    }

    public void setCategory(MetricRangeCategory category) {
        this.category = category;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public Integer getLowInclusive() {
        return lowInclusive;
    }

    public void setLowInclusive(Integer lowInclusive) {
        this.lowInclusive = lowInclusive;
    }

    public Integer getHighInclusive() {
        return highInclusive;
    }

    public void setHighInclusive(Integer highInclusive) {
        this.highInclusive = highInclusive;
    }

    public boolean includes(BigDecimal sample) {
        int lowComp = low.compareTo(sample);
        int highComp = high.compareTo(sample);

        if (lowComp > 0 || highComp < 0) {
            // If sample is lower than low or higher than high ==> false; not in range
            return false;
        } else if ((lowComp == 0 && lowInclusive == 0) || highComp == 0 && highInclusive == 0) {
            // Else if sample matches low/high but they are exclusive ==> false; not in range
            return false;
        }

        // Otherwise we're within range.
        return true;
    }

    @Override
    public int compareTo(MetricRange otherRange) {
        // (1 vs [1 vs [2 vs (0
        // (1 is bigger than [1
        // (1 is less than [2
        // (1 is bigger than [0
        int lowComparison = low.compareTo(otherRange.getLow());
        if (lowComparison < 0) {
            return -1;
        } else if (lowComparison == 0) {
            // [1  vs  [1       0
            // (1  vs  (1       0
            // [1  vs  (1       -1
            // (1  vs  [1       1
            if (lowInclusive == otherRange.getLowInclusive()) {
                return 0;
            } else if (lowInclusive == 1 && otherRange.getLowInclusive() == 0) {
                return -1;
            }
        }

        // else we're bigger
        return 1;
    }

    @Override
    public String toString() {
        return String.format("%s%s,%s%s",
                lowInclusive == 1 ? "[" : "(",
                low,
                high,
                highInclusive == 1 ? "]" : ")");
    }
}
