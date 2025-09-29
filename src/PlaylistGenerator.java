import java.util.List;

/**
 * Generira playlistu na temelju rezultata ankete (mood, žanr, energija),
 * uz primjenu jednostavnih preferencija (instrumental/acoustic).
 */
public class PlaylistGenerator {

    /**
     * Prazan konstruktor.
     */
    public PlaylistGenerator() {}

    /**
     * Generira playlistu prema kombinaciji mood-genre-energy.
     * @param event rezultati ankete
     * @return generirana playlista
     */
    public Playlist generate(SurveyEvent event) {
        String mood = event.getMood();
        String genre = event.getGenre();
        String energy = event.getEnergy();

        String name = mood + " " + genre + " " + energy;
        Playlist playlist = new Playlist(name);

        if ("Pop".equals(genre)) {
            if ("Happy".equals(mood)) {
                if ("High".equals(energy))       fillPopHappyHigh(playlist);
                else if ("Medium".equals(energy)) fillPopHappyMedium(playlist);
                else                               fillPopHappyLow(playlist);
            } else if ("Sad".equals(mood)) {
                if ("High".equals(energy))       fillPopSadHigh(playlist);
                else if ("Medium".equals(energy)) fillPopSadMedium(playlist);
                else                               fillPopSadLow(playlist);
            } else {
                if ("High".equals(energy))       fillPopCalmHigh(playlist);
                else if ("Medium".equals(energy)) fillPopCalmMedium(playlist);
                else                               fillPopCalmLow(playlist);
            }
        } else if ("Rock".equals(genre)) {
            if ("Happy".equals(mood)) {
                if ("High".equals(energy))       fillRockHappyHigh(playlist);
                else if ("Medium".equals(energy)) fillRockHappyMedium(playlist);
                else                               fillRockHappyLow(playlist);
            } else if ("Sad".equals(mood)) {
                if ("High".equals(energy))       fillRockSadHigh(playlist);
                else if ("Medium".equals(energy)) fillRockSadMedium(playlist);
                else                               fillRockSadLow(playlist);
            } else {
                if ("High".equals(energy))       fillRockCalmHigh(playlist);
                else if ("Medium".equals(energy)) fillRockCalmMedium(playlist);
                else                               fillRockCalmLow(playlist);
            }
        } else if ("Jazz".equals(genre)) {
            if ("Happy".equals(mood)) {
                if ("High".equals(energy))       fillJazzHappyHigh(playlist);
                else if ("Medium".equals(energy)) fillJazzHappyMedium(playlist);
                else                               fillJazzHappyLow(playlist);
            } else if ("Sad".equals(mood)) {
                if ("High".equals(energy))       fillJazzSadHigh(playlist);
                else if ("Medium".equals(energy)) fillJazzSadMedium(playlist);
                else                               fillJazzSadLow(playlist);
            } else {
                if ("High".equals(energy))       fillJazzCalmHigh(playlist);
                else if ("Medium".equals(energy)) fillJazzCalmMedium(playlist);
                else                               fillJazzCalmLow(playlist);
            }
        }

        applyPreferences(playlist, event);
        return playlist;
    }

    /**
     * Primjenjuje preferencije iz ankete na generiranu playlistu.
     * @param p playlista
     * @param ev rezultati ankete
     */
    private void applyPreferences(Playlist p, SurveyEvent ev) {
        if (ev.isInstrumentalOnly()) {
            List<Song> songs = p.getSongs();
            for (int i = 0; i < songs.size(); i++) {
                Song s = songs.get(i);
                String t = s.getTitle() == null ? "" : s.getTitle();
                if (t.toLowerCase().indexOf("instrumental") == -1) {
                    songs.set(i, new Song(t + " (Instrumental)", s.getArtist()));
                }
            }
        }
        if (ev.isAcousticVibe()) {
            List<Song> songs = p.getSongs();
            for (int i = 0; i < songs.size(); i++) {
                Song s = songs.get(i);
                String t = s.getTitle() == null ? "" : s.getTitle();
                if (t.toLowerCase().indexOf("acoustic") == -1) {
                    songs.set(i, new Song(t + " (Acoustic)", s.getArtist()));
                }
            }
        }
    }

    // --- Pop (Happy) ---
    private void fillPopHappyHigh(Playlist p) {
        p.addSong(new Song("Can't Stop the Feeling!", "Justin Timberlake"));
        p.addSong(new Song("Shut Up and Dance", "WALK THE MOON"));
        p.addSong(new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars"));
        p.addSong(new Song("Levitating", "Dua Lipa"));
        p.addSong(new Song("Blinding Lights", "The Weeknd"));
    }
    private void fillPopHappyMedium(Playlist p) {
        p.addSong(new Song("Happy", "Pharrell Williams"));
        p.addSong(new Song("Good as Hell", "Lizzo"));
        p.addSong(new Song("Sugar", "Maroon 5"));
        p.addSong(new Song("Roar", "Katy Perry"));
        p.addSong(new Song("Shake It Off", "Taylor Swift"));
    }
    private void fillPopHappyLow(Playlist p) {
        p.addSong(new Song("Budapest", "George Ezra"));
        p.addSong(new Song("Put Your Records On", "Corinne Bailey Rae"));
        p.addSong(new Song("Riptide", "Vance Joy"));
        p.addSong(new Song("I'm Yours", "Jason Mraz"));
        p.addSong(new Song("Yellow", "Coldplay"));
    }

    // --- Pop (Sad) ---
    private void fillPopSadHigh(Playlist p) {
        p.addSong(new Song("Stronger (What Doesn't Kill You)", "Kelly Clarkson"));
        p.addSong(new Song("Titanium", "David Guetta ft. Sia"));
        p.addSong(new Song("Firework", "Katy Perry"));
        p.addSong(new Song("Alive", "Sia"));
        p.addSong(new Song("Dog Days Are Over", "Florence + The Machine"));
    }
    private void fillPopSadMedium(Playlist p) {
        p.addSong(new Song("Someone Like You", "Adele"));
        p.addSong(new Song("Back to December", "Taylor Swift"));
        p.addSong(new Song("Stay", "Rihanna ft. Mikky Ekko"));
        p.addSong(new Song("All I Want", "Kodaline"));
        p.addSong(new Song("Let Her Go", "Passenger"));
    }
    private void fillPopSadLow(Playlist p) {
        p.addSong(new Song("When I Was Your Man", "Bruno Mars"));
        p.addSong(new Song("Skinny Love", "Birdy"));
        p.addSong(new Song("The Night We Met", "Lord Huron"));
        p.addSong(new Song("Jealous", "Labrinth"));
        p.addSong(new Song("Say Something", "A Great Big World ft. Christina Aguilera"));
    }

    // --- Pop (Calm) ---
    private void fillPopCalmHigh(Playlist p) {
        p.addSong(new Song("Rather Be", "Clean Bandit ft. Jess Glynne"));
        p.addSong(new Song("Symphony", "Clean Bandit ft. Zara Larsson"));
        p.addSong(new Song("Counting Stars", "OneRepublic"));
        p.addSong(new Song("Wake Me Up", "Avicii"));
        p.addSong(new Song("Geronimo", "Sheppard"));
    }
    private void fillPopCalmMedium(Playlist p) {
        p.addSong(new Song("Photograph", "Ed Sheeran"));
        p.addSong(new Song("If I Ain't Got You", "Alicia Keys"));
        p.addSong(new Song("Lucky", "Jason Mraz & Colbie Caillat"));
        p.addSong(new Song("Gravity", "John Mayer"));
        p.addSong(new Song("Bubbly", "Colbie Caillat"));
    }
    private void fillPopCalmLow(Playlist p) {
        p.addSong(new Song("Holocene", "Bon Iver"));
        p.addSong(new Song("Lost", "Dermot Kennedy"));
        p.addSong(new Song("Fix You", "Coldplay"));
        p.addSong(new Song("The Scientist", "Coldplay"));
        p.addSong(new Song("River", "Leon Bridges"));
    }

    // --- Rock (Happy) ---
    private void fillRockHappyHigh(Playlist p) {
        p.addSong(new Song("Don't Stop Me Now", "Queen"));
        p.addSong(new Song("Mr. Brightside", "The Killers"));
        p.addSong(new Song("I Bet You Look Good on the Dancefloor", "Arctic Monkeys"));
        p.addSong(new Song("American Idiot", "Green Day"));
        p.addSong(new Song("Are You Gonna Be My Girl", "Jet"));
    }
    private void fillRockHappyMedium(Playlist p) {
        p.addSong(new Song("Dakota", "Stereophonics"));
        p.addSong(new Song("Learn to Fly", "Foo Fighters"));
        p.addSong(new Song("Drive", "Incubus"));
        p.addSong(new Song("Vertigo", "U2"));
        p.addSong(new Song("You Really Got Me", "The Kinks"));
    }
    private void fillRockHappyLow(Playlist p) {
        p.addSong(new Song("Blackbird", "The Beatles"));
        p.addSong(new Song("Wish You Were Here", "Pink Floyd"));
        p.addSong(new Song("Patience", "Guns N' Roses"));
        p.addSong(new Song("Good Riddance (Time of Your Life)", "Green Day"));
        p.addSong(new Song("Here Comes the Sun", "The Beatles"));
    }

    // --- Rock (Sad) ---
    private void fillRockSadHigh(Playlist p) {
        p.addSong(new Song("Numb", "Linkin Park"));
        p.addSong(new Song("In the End", "Linkin Park"));
        p.addSong(new Song("It's My Life", "Bon Jovi"));
        p.addSong(new Song("Basket Case", "Green Day"));
        p.addSong(new Song("You Give Love a Bad Name", "Bon Jovi"));
    }
    private void fillRockSadMedium(Playlist p) {
        p.addSong(new Song("Creep", "Radiohead"));
        p.addSong(new Song("Everybody Hurts", "R.E.M."));
        p.addSong(new Song("Nothing Else Matters", "Metallica"));
        p.addSong(new Song("Hurt", "Johnny Cash"));
        p.addSong(new Song("With or Without You", "U2"));
    }
    private void fillRockSadLow(Playlist p) {
        p.addSong(new Song("Black", "Pearl Jam"));
        p.addSong(new Song("Tears in Heaven", "Eric Clapton"));
        p.addSong(new Song("Hallelujah", "Jeff Buckley"));
        p.addSong(new Song("The Sound of Silence", "Simon & Garfunkel"));
        p.addSong(new Song("Street Spirit (Fade Out)", "Radiohead"));
    }

    // --- Rock (Calm) ---
    private void fillRockCalmHigh(Playlist p) {
        p.addSong(new Song("Beautiful Day", "U2"));
        p.addSong(new Song("Everlong (Acoustic)", "Foo Fighters"));
        p.addSong(new Song("Walk of Life", "Dire Straits"));
        p.addSong(new Song("You Make My Dreams", "Hall & Oates"));
        p.addSong(new Song("Roll With It", "Oasis"));
    }
    private void fillRockCalmMedium(Playlist p) {
        p.addSong(new Song("Scar Tissue", "Red Hot Chili Peppers"));
        p.addSong(new Song("Times Like These (Acoustic)", "Foo Fighters"));
        p.addSong(new Song("Yellow", "Coldplay"));
        p.addSong(new Song("Run", "Snow Patrol"));
        p.addSong(new Song("Chasing Cars", "Snow Patrol"));
    }
    private void fillRockCalmLow(Playlist p) {
        p.addSong(new Song("Landslide", "Fleetwood Mac"));
        p.addSong(new Song("No Surprises", "Radiohead"));
        p.addSong(new Song("The Blower's Daughter", "Damien Rice"));
        p.addSong(new Song("Holes", "Passenger"));
        p.addSong(new Song("Drive (Acoustic)", "Incubus"));
    }

    // --- Jazz (Happy) ---
    private void fillJazzHappyHigh(Playlist p) {
        p.addSong(new Song("Sing, Sing, Sing", "Benny Goodman"));
        p.addSong(new Song("It Don't Mean a Thing", "Duke Ellington"));
        p.addSong(new Song("A Night in Tunisia", "Dizzy Gillespie"));
        p.addSong(new Song("Cantaloupe Island", "Herbie Hancock"));
        p.addSong(new Song("On the Sunny Side of the Street", "Louis Armstrong"));
    }
    private void fillJazzHappyMedium(Playlist p) {
        p.addSong(new Song("Take the 'A' Train", "Duke Ellington"));
        p.addSong(new Song("All of Me", "Ella Fitzgerald"));
        p.addSong(new Song("Blue Bossa", "Joe Henderson"));
        p.addSong(new Song("Autumn Leaves", "Cannonball Adderley"));
        p.addSong(new Song("So What", "Miles Davis"));
    }
    private void fillJazzHappyLow(Playlist p) {
        p.addSong(new Song("Fly Me to the Moon", "Frank Sinatra"));
        p.addSong(new Song("My Baby Just Cares for Me", "Nina Simone"));
        p.addSong(new Song("Cheek to Cheek", "Ella & Louis"));
        p.addSong(new Song("L-O-V-E", "Nat King Cole"));
        p.addSong(new Song("Misty", "Erroll Garner"));
    }

    // --- Jazz (Sad) ---
    private void fillJazzSadHigh(Playlist p) {
        p.addSong(new Song("Spain", "Chick Corea"));
        p.addSong(new Song("St. Thomas", "Sonny Rollins"));
        p.addSong(new Song("Freddie Freeloader", "Miles Davis"));
        p.addSong(new Song("Moanin'", "Art Blakey"));
        p.addSong(new Song("Caravan", "Duke Ellington"));
    }
    private void fillJazzSadMedium(Playlist p) {
        p.addSong(new Song("'Round Midnight", "Thelonious Monk"));
        p.addSong(new Song("Blue in Green", "Miles Davis"));
        p.addSong(new Song("Strange Fruit", "Billie Holiday"));
        p.addSong(new Song("Naima", "John Coltrane"));
        p.addSong(new Song("Body and Soul", "Coleman Hawkins"));
    }
    private void fillJazzSadLow(Playlist p) {
        p.addSong(new Song("My Funny Valentine", "Chet Baker"));
        p.addSong(new Song("Don't Explain", "Billie Holiday"));
        p.addSong(new Song("Polka Dots and Moonbeams", "Chet Baker"));
        p.addSong(new Song("In a Sentimental Mood", "Ellington & Coltrane"));
        p.addSong(new Song("Goodbye Pork Pie Hat", "Charles Mingus"));
    }

    // --- Jazz (Calm) ---
    private void fillJazzCalmHigh(Playlist p) {
        p.addSong(new Song("Watermelon Man", "Herbie Hancock"));
        p.addSong(new Song("Take Five", "Dave Brubeck"));
        p.addSong(new Song("Song for My Father", "Horace Silver"));
        p.addSong(new Song("The Girl from Ipanema", "Stan Getz & João Gilberto"));
        p.addSong(new Song("Desafinado", "Antonio Carlos Jobim"));
    }
    private void fillJazzCalmMedium(Playlist p) {
        p.addSong(new Song("Round Midnight (Miles Version)", "Miles Davis"));
        p.addSong(new Song("Waltz for Debby", "Bill Evans"));
        p.addSong(new Song("Peace Piece", "Bill Evans"));
        p.addSong(new Song("You Must Believe in Spring", "Bill Evans"));
        p.addSong(new Song("Blue Monk", "Thelonious Monk"));
    }
    private void fillJazzCalmLow(Playlist p) {
        p.addSong(new Song("River Man", "Nick Drake"));
        p.addSong(new Song("Summertime", "Ella & Louis"));
        p.addSong(new Song("April in Paris", "Count Basie"));
        p.addSong(new Song("Skylark", "Carmen McRae"));
        p.addSong(new Song("Moon River", "Sarah Vaughan"));
    }
}
