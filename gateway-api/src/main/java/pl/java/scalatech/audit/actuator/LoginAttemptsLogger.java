package pl.java.scalatech.audit.actuator;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.ProfileApp;

@Slf4j
@Component
@Profile(ProfileApp.TEST)
class LoginAttemptsLogger {

    private static final String DETAILS2 = "details";
    private static final String PRINCIPAL = "Principal ";
    private static final String REQUEST_URL = "  Request URL: ";
    private static final String SESSION_ID = "  Session Id: ";
    private static final String REMOTE_IP_ADDRESS = "  Remote IP address: ";

    @EventListener
    public void auditEventHappened(AuditApplicationEvent auditApplicationEvent) {
        AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
        log.info(PRINCIPAL + auditEvent.getPrincipal() + " - " + auditEvent.getType());

        WebAuthenticationDetails details = (WebAuthenticationDetails) auditEvent.getData().get(DETAILS2);
        log.info(REMOTE_IP_ADDRESS + details.getRemoteAddress());
        log.info(SESSION_ID + details.getSessionId());
        log.info(REQUEST_URL + auditEvent.getData().get("requestUrl"));
    }
}