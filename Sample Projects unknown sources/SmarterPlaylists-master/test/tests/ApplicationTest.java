package tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import play.mvc.*;
import play.mvc.Http.Response;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void callIndex() {
    	  running(fakeApplication(), new Runnable() {
    		    public void run() {
			        Result result = callAction(
			        		controllers.routes.ref.Application.index()
					);
					assertThat(status(result)).isEqualTo(OK);
			
					result = route(fakeRequest(GET, "/"));
					assertThat(status(result)).isEqualTo(OK);
    		    }
    	  });
    }
    
    @Test
    public void callAbout() {
  	  running(fakeApplication(), new Runnable() {
		    public void run() {
    	Result result = callAction(
    			controllers.routes.ref.Application.about()
    	);
    	assertThat(status(result)).isEqualTo(OK);
    	
		result = route(fakeRequest(GET, "/about"));
		assertThat(status(result)).isEqualTo(OK);
		    }
	  });
    }

    @Test
    public void callLang() {
    	running(fakeApplication(), new Runnable() {
    		public void run() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "es");
                Result result = route
                        (fakeRequest(POST, "/lang").withHeader("Content-Type", "application/json").withFormUrlEncodedBody(map));
                assertThat(result).isNotNull();
    		}
    	});
    }

    @Test
    public void callAdmin() {
    	running(fakeApplication(), new Runnable() {
    		public void run() {
    			Result result = route(fakeRequest(GET, "/admin"));
    			assertThat(result).isNull();
    		}
    	});
    }

    @Test
    public void callDownload() {
  	  running(fakeApplication(), new Runnable() {
		    public void run() {
    	Result result = callAction(
    			controllers.routes.ref.Application.download("stuff", "file.xml")
    	);
    	assertThat(status(result)).isEqualTo(303);
		    }
	  });
    }
}
