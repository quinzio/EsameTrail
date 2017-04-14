package trail;

public class Passage {
    private Delegate delegate;
    private Location location;
    private Runner runner;
    private long passageTime;
    
    public Delegate getDelegate() {
        return delegate;
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public long getPassageTime() {
        return passageTime;
    }

    public void setPassageTime(long passageTime) {
        this.passageTime = passageTime;
    }

    public Passage(Delegate delegate, Location location, Runner runner, long passageTime) {
	super();
	this.delegate = delegate;
	this.location = location;
	this.runner = runner;
	this.passageTime = passageTime;
    }
    
    
    

}
