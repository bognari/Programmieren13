package blatt11.aufgabe66p;

/**
 * Ein Eintrag in der Bundesligatabelle
 */
public class Team implements Comparable<Team> {
    /**
     * Enum für die 1., 2. und 3. Liga
     * die Reihenfolge darf nicht geändert werden!
     */
    public enum League {
        ERSTE, ZWEITE, DRITTE
    };

    /**
     * die Liga der Mannschaft
     */
    private final League   league;
    /**
     * der Name der Mannschaft
     */
    private final String name;
    /**
     * die erzielten Tore
     */
    private int          goalsFor;
    /**
     * die bekommenen Gegentore
     */
    private int          goalsAgainst;
    /**
     * Anzahl der Spiele (Spieltag)
     */
    private int          played;
    /**
     * Anzahl der Siege
     */
    private int          won;
    /**
     * Anzahl der Niederlagen
     */
    private int          lost;
    /**
     * Anzahl der Unentschieden
     */
    private int          drawn;

    /**
     * Erstellt ein neues Team
     * 
     * @param league
     * @param name
     */
    public Team(League league, String name) {
        this.league = league;
        this.name = name;
    }

    /**
     * Trägt das Spielresultat ein
     * 
     * @param goalsFor
     * @param goalsAgainst
     */
    public void game(int goalsFor, int goalsAgainst) {
        this.goalsFor += goalsFor;
        this.goalsAgainst += goalsAgainst;
        this.played++;
        if (goalsFor == goalsAgainst) {
            this.drawn++;
            return;
        }
        if (goalsFor > goalsAgainst) {
            this.won++;
            return;
        }
        if (goalsFor < goalsAgainst) {
            this.lost++;
            return;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("%6s | %25s | %5d | %3d | %3d | %3d | %2d:%2d | %3d | %3d", this.league, this.name,
                this.played, this.won, this.drawn, this.lost, this.goalsFor, this.goalsAgainst,
                this.getGD(), this.getPoints());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Team o) {
        int liga = this.league.compareTo(o.league);
        if (liga != 0)
            return liga;
        int punkte = Integer.compare(this.getPoints(), o.getPoints());
        if (punkte != 0)
            return punkte * -1; // max zu min
        int tordiff = Integer.compare(this.getGD(), o.getGD());
        if (tordiff != 0)
            return tordiff * -1; // max zu min
        return Integer.compare(this.goalsFor, o.goalsFor) * -1; // max zu min
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        Team obj;
        try {
            obj = (Team) o;
        } catch (ClassCastException cce) {
            return false;
        }
        return this.league.equals(obj.league) && this.name.equals(obj.name);
    }
    
    public int getPoints() {
        return this.won * 3 + this.drawn;
    }
    
    public int getGD() {
        return this.goalsFor - this.goalsAgainst;
    }
}
