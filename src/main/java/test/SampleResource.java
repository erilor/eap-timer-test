package test;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("/")
public class SampleResource {

	private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);

	@EJB
	private SingletonWithTimerService1 timer1;

	@EJB
	private SingletonWithTimerService1 timer2;

	@GET
	@Path("timer1/start")
	public void setTimer1Active() {
		timer1.start();
	}

	@GET
	@Path("timer1")
	public void setTimer1Delay(@QueryParam("time") long time) {
		timer1.setTime(time);
	}

	@GET
	@Path("timer2/start")
	public void setTimer2Active() {
		timer2.start();
	}

	@GET
	@Path("timer2")
	public void setTimer2Delay(@QueryParam("time") long time) {
		timer2.setTime(time);
	}

}
