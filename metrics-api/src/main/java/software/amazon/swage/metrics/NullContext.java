package software.amazon.swage.metrics;

import java.time.Instant;
import java.util.Optional;

import software.amazon.swage.collection.TypedMap;

/**
 * A MetricContext implementation that acts as a sink for recorded events.
 */
public class NullContext implements MetricContext {

    private static final NullContext EMPTY = new NullContext(TypedMap.empty());

    /**
     * The default NullContext with an empty set of attributes.
     *
     * @return the empty NullContext
     */
    public static NullContext empty() {
        return EMPTY;
    }

    private final MetricContext parent;
    private final TypedMap attributes;

    /**
     * @param attributes the attributes of the measurement context
     */
    public NullContext(TypedMap attributes) {
        this(null, attributes);
    }

    private NullContext(MetricContext parent, TypedMap attributes) {
        this.parent = parent;
        this.attributes = attributes;
    }

    @Override
    public Optional<MetricContext> parent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public MetricContext newChildContext(TypedMap additionalAttributes) {
        return new NullContext(this, attributes);
    }

    @Override
    public TypedMap attributes() {
        return attributes;
    }

    @Override
    public void record(Metric label, Number value, Unit unit, Instant time) {
    }

    @Override
    public void count(Metric label, long delta) {
    }

    @Override
    public void close() {
    }
}
