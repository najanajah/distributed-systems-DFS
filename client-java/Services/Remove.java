package Services;

import java.io.IOException;
import java.util.Map;

import Exceptions.ApplicationException;
import Exceptions.BadPathnameException;
import Exceptions.BadRangeException;
// import Helpers.CacheObject;
import Helpers.Constants;
import Helpers.Runner;

public class Remove extends Service {

    public Remove(Runner r) {
        super(r);
        service_id = Constants.REMOVE_FILE_ID;
    }
    @Override
    public void act() throws IOException {
        String[] request_values = get_user_request_values();
        String pathname = request_values[0];

        try{ 
            // Send directly to server since file must be deleted 
            Map<String, Object> reply = send_and_receive(request_values);
            //  remove key if in cache 
            if (runner.cache.containsKey(pathname)) {
                runner.cache.remove(pathname);
            }

            System.out.println("Content:");
            System.out.println(reply.get("content"));
            System.out.println("Done.");
        }
        catch(BadPathnameException bpe) {
            System.out.println("Error: " + bpe.getMessage() + ".");
        }
        catch(BadRangeException bre){ 
            System.out.println("Error: " + bre.getMessage() + ".");
        }
        catch(ApplicationException a){ 
            System.out.println("Error: " + a.getMessage() + ".");
        }

    } 
}
