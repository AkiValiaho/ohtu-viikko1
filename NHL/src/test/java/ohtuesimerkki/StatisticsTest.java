package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Test
    public void topScorers_validAmount_returnInOrder() {
        List<Player> players = stats.topScorers(1);
        assertEquals(2, players.size());
        Iterator<Player> iterator = players.iterator();
        assertEquals("Gretzky", iterator.next().getName());
        assertEquals("Lemieux", iterator.next().getName());
    }

    @Test
    public void searchTeam_validTeamMembers_returnListWithmembers() {
        List<Player> edm = stats.team("EDM");
        assertTrue(edm.size() == 3);
    }

    @Test
    public void searchTeam_nonValidTeam_returnsEmptyList() {
        List<Player> asdf = stats.team("asdf");
        assertTrue(asdf.size() == 0);
    }

    @Test
    public void search_returnsValidPlayer() {
        Player semenko = stats.search("Semenko");
        assertEquals("Semenko", semenko.getName());
    }

    @Test
    public void search_readerReturnsNoPlayers() {
        stats = new Statistics(Collections::emptyList);
        Player asdf = stats.search("asdf");
        assertNull(asdf);
    }

    @Test
    public void search_validReaderNoMatch_returnNull() {
        assertNull(stats.search("fdsa"));

    }

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
}
