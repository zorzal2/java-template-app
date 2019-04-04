package yucom.template.services.spotify;

import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Component
public class ErrorService {

    private static final String REST_URI
            = "https://api.spotify.com/v1/albums/1";

    private Client client = ClientBuilder.newClient();

    public Object getAlbum() {
        try {
            return client
                    .target(REST_URI)
                    .request()
                    .get(Object.class);
        } catch (WebApplicationException ex){
            return ex.getResponse().getHeaders();
        }
    }

}
