import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.net.MalformedURLException;

import static com.example.DownloadingParse.makeApiRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DownloadingParseTest {
    String url = "https://api.myjson.com/bins/d0ebx";

    @Test
    public void adventureParseTest() throws MalformedURLException, UnirestException {
        System.out.print(makeApiRequest(url).getStartingRoom());
        assertEquals("UpsideDown", makeApiRequest(url).getStartingRoom());
    }

    @Test
    public void adventureRoomParseTest() throws MalformedURLException, UnirestException {
        System.out.print(makeApiRequest(url).getStartingRoom());
        assertEquals("UpsideDown", makeApiRequest(url).getRoom()[0].getName());
    }

    @Test
    public void adventurePlayerParseTest() throws MalformedURLException, UnirestException {
        System.out.print(makeApiRequest(url).getPlayer());

    }


    @Test
    public void adventureParseExceptTest() {
        try {
            makeApiRequest("t");
            fail();
        } catch (MalformedURLException e) {

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}