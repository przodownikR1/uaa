package pl.java.scalatech.audit.actuator;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuditApplicationEventListener {

    

    private final ApplicationEventPublisher applicationEventPublisher;

    public AuditApplicationEventListener(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @EventListener(condition = "#event.auditEvent.type != 'MY_EVENT'")
    @Async
    public void onAuditEvent(AuditApplicationEvent event) {
        AuditEvent actualAuditEvent = event.getAuditEvent();

        applicationEventPublisher.publishEvent(
            new AuditApplicationEvent(
                new AuditEvent(actualAuditEvent.getPrincipal(), "MY_EVENT")
            )
        );
    }

    @EventListener(condition = "#event.auditEvent.type == 'MY_EVENT'")
    @Async
    public void onCustomAuditEvent(AuditApplicationEvent event) {
        log.info("Handling custom audit event ...");
    }
}