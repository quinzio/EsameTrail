package trail;

public class Runner {
    private String name;
    private String surname;
    private Integer pettorale;
    static Integer s_pettorale = 1;

    
    public Runner(String nome, String cognome) {
	super();
	this.name = nome;
	this.surname = cognome;
	this.pettorale = s_pettorale;
	s_pettorale++;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBibNumber(){
        return pettorale;
    }


}
