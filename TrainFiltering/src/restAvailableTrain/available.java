package restAvailableTrain;

import org.restlet.data.Protocol;
//import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
//import org.restlet.Server;
import org.restlet.Component;

public class available extends ServerResource{

	public static void main(String[] args) throws Exception {
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8182);
		
		component.getDefaultHost().attach(new RouterApplication());
		component.start();
	}
}
