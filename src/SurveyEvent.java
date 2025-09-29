import java.io.Serializable;

/**
 * An event that encapsulates the survey results (mood, genre, energy, preferences).
 */
public class SurveyEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mood;
    private String genre;
    private String energy;

    private boolean instrumentalOnly;
    private boolean acousticVibe;

    /**
     * Empty constructor.
     */
    public SurveyEvent() {}

    /** @return selected mood */
    public String getMood() { return mood; }
    /** @param mood sets the mood */
    public void setMood(String mood) { this.mood = mood; }

    /** @return selected genre */
    public String getGenre() { return genre; }
    /** @param genre sets the genre */
    public void setGenre(String genre) { this.genre = genre; }

    /** @return selected energy level */
    public String getEnergy() { return energy; }
    /** @param energy sets the energy level */
    public void setEnergy(String energy) { this.energy = energy; }

    /** @return true if only instrumental versions are desired */
    public boolean isInstrumentalOnly() { return instrumentalOnly; }
    /** @param instrumentalOnly sets the instrumental tone preference */
    public void setInstrumentalOnly(boolean instrumentalOnly) { this.instrumentalOnly = instrumentalOnly; }

    /** @return true if an acoustic atmosphere is desired */
    public boolean isAcousticVibe() { return acousticVibe; }
    /** @param acousticVibe sets acoustic ambience preference */
    public void setAcousticVibe(boolean acousticVibe) { this.acousticVibe = acousticVibe; }
}
