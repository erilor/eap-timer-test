package test;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
@TransactionAttribute(TransactionAttributeType.NEVER)
public class SingletonWithTimerService1 {

	private static final Logger log = LoggerFactory.getLogger(SingletonWithTimerService1.class);

	@Resource
	private TimerService timerService;

	private long time = 0;

	@PostConstruct
	public void initialize() {
		scheduleWithDelay();
	}

	@Timeout
	public void fetchEvents() {
		try {
			log.info("Running fetchEvents");
			scheduleWithDelay();
		} finally {
			if (timerService.getTimers().isEmpty()) {
				log.error("Failed to create new timer!");
			}
		}

	}

	private void scheduleWithDelay() {
		log.info("Created timer with delay: {}", time);
		timerService.createSingleActionTimer(time, new TimerConfig(null, false));
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void start() {
		scheduleWithDelay();
	}
}
