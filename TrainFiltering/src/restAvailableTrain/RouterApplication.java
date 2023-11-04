package restAvailableTrain;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RouterApplication extends Application {
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/findTrain/{villes}", BookTrain.class);
		router.attach("/train", BookTrain.class);
		router.attach("/updateTicket/{id}", BookTrain.class);
		return router;
	}
}
