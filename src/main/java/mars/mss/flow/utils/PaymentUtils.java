package mars.mss.flow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentUtils {
    private static final Logger log = LoggerFactory.getLogger(PaymentUtils.class);

    private PaymentUtils() {}

    public static void processPayment(String crewId, int amount) {
        log.info("Processing payment of {} for crew {}", amount, crewId);
    }
}
