package trail;

import java.util.ArrayList;
import java.util.List;

public class Delegate {
    private String name;
    private String surname;
    private String codiceFiscale;
    private List<Location> locations = new ArrayList<>();

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Delegate(String name, String surname, String codiceFiscale) {
	super();
	this.name = name;
	this.surname = surname;
	this.codiceFiscale = codiceFiscale;
    }
    public void addLocation(Location l) {
	locations.add(l);
    }
    
    
    

}
