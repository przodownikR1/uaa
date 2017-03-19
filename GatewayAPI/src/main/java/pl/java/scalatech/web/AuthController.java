package pl.java.scalatech.web;

import static org.springframework.security.oauth2.common.util.OAuth2Utils.SCOPE_PREFIX;
import static org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus.APPROVED;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(AuthController.AUTHORIZATION_REQUEST)
public class AuthController {
    
    private static final String OAUTH_ERROR = "oauth_error";

    private static final String OAUTH_CONFIRM_ACCESS = "/oauth/confirm_access";

    private static final String TRUE = "true";

    private static final String FALSE = "false";

    protected static final String AUTHORIZATION_REQUEST = "authorizationRequest";

    private static final String CLIENT2 = "client";

    private static final String AUTH_REQUEST = "auth_request";

    private static final String SCOPES2 = "scopes";

    private static final String ACCESS_CONFIRMATION = "access_confirmation";

    private final ClientDetailsService clientDetailsService;

    private final ApprovalStore approvalStore;
   
    
    public AuthController(ClientDetailsService clientDetailsService, ApprovalStore approvalStore) {
        super();
        this.clientDetailsService = clientDetailsService;
        this.approvalStore = approvalStore;
    }


    @GetMapping(OAUTH_CONFIRM_ACCESS)
    public String getAccessConfirmation(Map<String, Object> model, Principal principal) throws Exception {
        AuthorizationRequest clientAuth = (AuthorizationRequest) model.remove(AUTHORIZATION_REQUEST);
        ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
        model.put(AUTH_REQUEST, clientAuth);
        model.put(CLIENT2, client);
        Map<String, String> scopes = new LinkedHashMap<>();
        for (String scope : clientAuth.getScope()) {
            scopes.put(SCOPE_PREFIX + scope, FALSE);
        }
        
        
        for (Approval approval : approvalStore.getApprovals(principal.getName(), client.getClientId())) {
            if (clientAuth.getScope().contains(approval.getScope())) {
                scopes.put(SCOPE_PREFIX + approval.getScope(),
                        approval.getStatus() == APPROVED ? TRUE : FALSE);
            }
        }
        model.put(SCOPES2, scopes);
        return ACCESS_CONFIRMATION;
}
    @GetMapping("/oauth/error")
    public String handleError(Map<String, Object> model) throws Exception {    
        model.put("message", "There was a problem with the OAuth2 protocol");
        return OAUTH_ERROR;
}
}
