import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GossipsFullySpreadCheckerShould {

    @Mock
    private Bus busThatKnowsAll;
    @Mock
    private Bus busThatDontKnowsAll;

    @Before
    public void setMockBusesKnowledgeOfGossips() {
        when(busThatKnowsAll.knowsAllGossips()).thenReturn(true);
        when(busThatDontKnowsAll.knowsAllGossips()).thenReturn(false);
    }

    @Test
    public void tellIfAllBusesKnowAllGossips() {
        GossipsSpreadChecker gossipsSpreadChecker = new GossipsSpreadChecker();

        gossipsSpreadChecker.addBus(busThatKnowsAll, null);
        gossipsSpreadChecker.addBus(busThatKnowsAll, null);
        gossipsSpreadChecker.addBus(busThatKnowsAll, null);

        assertThat(gossipsSpreadChecker.doAllDriversKnowAllGossips(), is(true));
    }

    @Test
    public void tellIfAllBusesDontKnowAllGossips() {
        GossipsSpreadChecker gossipsSpreadChecker = new GossipsSpreadChecker();

        gossipsSpreadChecker.addBus(busThatDontKnowsAll, null);
        gossipsSpreadChecker.addBus(busThatKnowsAll, null);
        gossipsSpreadChecker.addBus(busThatKnowsAll, null);

        assertThat(gossipsSpreadChecker.doAllDriversKnowAllGossips(), is(false));
    }

}
