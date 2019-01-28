package troubleshooting;

public class Fizzbuzz {
    private String something;
    private Long foobarId;

    public Fizzbuzz(String something, Long foobarId) {
        this.something = something;
        this.foobarId = foobarId;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    public Long getFoobarId() {
        return foobarId;
    }

    public void setFoobarId(Long foobarId) {
        this.foobarId = foobarId;
    }
}
