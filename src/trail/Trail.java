package trail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Trail {
    private Collection<Runner> runners = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<Delegate> delegates = new ArrayList<>();
    private List<Passage> passages = new ArrayList<>();

    public int newRunner(String name, String surname) {
	Runner r = new Runner(name, surname);
	runners.add(r);
	return r.getBibNumber();
    }

    public Runner getRunner(int bibNumber) {
	for (Runner r : runners) {
	    if (r.getBibNumber() == bibNumber)
		return r;
	}
	return null;
    }

    public Collection<Runner> getRunner(String surname) {
	Collection<Runner> cr = new ArrayList<>();
	for (Runner r : runners) {
	    if (r.getSurname().equals(surname))
		cr.add(r);
	}
	Collections.sort((List<Runner>) cr, Comparator.comparing(Runner::getBibNumber));
	return cr;
    }

    public List<Runner> getRunners() {
	List<Runner> cr = new ArrayList<>(runners);
	Collections.sort((List<Runner>) cr, Comparator.comparing(Runner::getBibNumber));
	return cr;
    }

    public List<Runner> getRunnersByName() {
	List<Runner> cr = new ArrayList<>(runners);
	Collections.sort((List<Runner>) cr, Comparator.comparing(Runner::getSurname).thenComparing(Runner::getName));
	return cr;
    }

    public void addLocation(String location) {
	int pos = locations.size();
	Location loc = new Location(location, pos);
	locations.add(loc);
    }
    // public void addLocation(String name, double lat, double lon, double
    // elevation){
    //
    // }

    public Location getLocation(String location) {
	for (Location l : locations) {
	    if (locations.equals(l.getName()))
		return l;
	}
	return null;
    }

    public List<Location> getPath() {
	return locations;
    }

    public void newDelegate(String name, String surname, String id) {
	Delegate d = new Delegate(name, surname, id);
	delegates.add(d);
    }

    public List<String> getDelegates() {
	List<String> dls = new ArrayList<>();
	for (Delegate d : delegates) {
	    dls.add(d.getSurname() + ", " + d.getName() + ", " + d.getCodiceFiscale());
	}
	Collections.sort(dls);
	return dls;
    }

    public void assignDelegate(String location, String delegate) throws TrailException {
	Map<String, Location> msl = locations.stream().collect(Collectors.toMap(Location::getName, l -> l));
	Location l = msl.get(location);
	Map<String, Delegate> msd = delegates.stream().collect(Collectors.toMap(Delegate::getCodiceFiscale, d -> d));
	Delegate d = msd.get(delegate);

	if (d == null)
	    throw new TrailException();
	if (l == null)
	    throw new TrailException();

	l.addDelegate(d);
	d.addLocation(l);
    }

    public List<String> getDelegates(String location) {
	Map<String, Location> msl = locations.stream().collect(Collectors.toMap(Location::getName, l -> l));
	Location l = msl.get(location);
	List<Delegate> ld = l.getDelegates();

	List<String> dls = new ArrayList<>();
	for (Delegate d : ld) {
	    dls.add(d.getSurname() + ", " + d.getName() + ", " + d.getCodiceFiscale());
	}
	Collections.sort(dls);
	return dls;

    }

    public long recordPassage(String delegate, String location, int bibNumber) throws TrailException {
	Map<String, Location> msl = locations.stream().collect(Collectors.toMap(Location::getName, l -> l));
	Location l = msl.get(location);

	Map<String, Delegate> msd = delegates.stream().collect(Collectors.toMap(Delegate::getCodiceFiscale, d -> d));
	Delegate d = msd.get(delegate);

	Map<Integer, Runner> msr = runners.stream().collect(Collectors.toMap(r1 -> {
	    return new Integer(r1.getBibNumber());
	}, r1 -> r1));
	Runner r = msr.get(bibNumber);

	if (d == null)
	    throw new TrailException();
	if (l == null)
	    throw new TrailException();
	if (r == null)
	    throw new TrailException();

	long getTime = System.currentTimeMillis();
	passages.add(new Passage(d, l, r, getTime));
	return getTime;

    }

    public long getPassTime(String position, int bibNumber) throws TrailException {
	Map<String, Location> msl = locations.stream().collect(Collectors.toMap(Location::getName, l -> l));
	Location l = msl.get(position);

	Map<Integer, Runner> msr = runners.stream().collect(Collectors.toMap(r1 -> {
	    return new Integer(r1.getBibNumber());
	}, r1 -> r1));
	Runner r = msr.get(bibNumber);

	if (l == null)
	    throw new TrailException();
	if (r == null)
	    throw new TrailException();

	List<Passage> pl = passages.stream().filter(p -> (p.getLocation() == l && p.getRunner() == r))
		.collect(Collectors.toList());

	if (pl.size() != 1)
	    return -1;
	return pl.get(0).getPassageTime();
    }

    public List<Runner> getRanking(String location) {
	Map<String, Location> msl = locations.stream().collect(Collectors.toMap(Location::getName, l -> l));
	Location l = msl.get(location);

	List<Runner> rl = passages.stream().filter(p -> (p.getLocation() == l))
		.sorted(Comparator.comparing(Passage::getPassageTime)).map(Passage::getRunner)
		.collect(Collectors.toList());

	return rl;
    }

    public List<Runner> getRanking() {
	List<Runner> rl = passages.stream()
		.sorted(Comparator
			.<Passage, Integer>comparing(p -> p.getLocation().getOrderNum(), Comparator.reverseOrder())
			.thenComparing(Passage::getPassageTime))
		.map(Passage::getRunner).collect(Collectors.toList());
	return rl;
    }
}
