package TrackModel.UI;

/**
 * Created by adzun_000 on 1/25/2017.
 */
public class Metric {

    private String metric;
    private String value;

    public Metric(String metric, String value) {
        this.metric = metric;
        this.value = value;
    }

    public String getMetric() {
        return metric;
    }

    public String metricProperty() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getValue() {
        return this.value;
    }

    public String valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
