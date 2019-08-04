package com.example;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import java.net.MalformedURLException;
import static com.example.DownloadingParse.makeApiRequest;
import static org.junit.Assert.fail;

public class AdventureTest {
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

