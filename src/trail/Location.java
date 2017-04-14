package trail;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private int orderNum;
    private List<Delegate> delegates = new ArrayList<>();

    public List<Delegate> getDelegates() {
        return delegates;
    }

    public Location(String name, int orderNum) {
	super();
	this.name = name;
	this.orderNum = orderNum;
    }

    public String getName(){
        return name;
    }

    public int getOrderNum(){
        return orderNum;
    }
    public void addDelegate(Delegate d) {
	delegates.add(d);
    } 
}
