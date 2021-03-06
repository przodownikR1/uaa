package pl.java.scalatech.audit.actuator;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.listener.AbstractAuditListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
class AuditEventListener extends AbstractAuditListener {

    private final AuditEventRepository auditEventRepository;

    @Override
    protected void onAuditEvent(AuditEvent event) {
        log.info("On audit event: timestamp: {}, principal: {}, type: {}, data: {}", event.getTimestamp(),
                event.getPrincipal(), event.getType(), event.getData());

        auditEventRepository.add(event);
    }
}