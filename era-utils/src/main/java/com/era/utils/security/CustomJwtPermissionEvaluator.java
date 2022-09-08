package com.era.utils.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.io.Serializable;
import java.util.List;

public class CustomJwtPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(
            Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }

        return hasPrivilege(auth, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(
            Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth,
                permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String permission) {

        if(auth instanceof JwtAuthenticationToken jwt) {
            try {
                List<String> grantedAuthorityList = (List<String>) jwt.getToken().getClaims().get("authorities");
                for (String grantedAuth : grantedAuthorityList) {
                    if (grantedAuth.toUpperCase().contains(permission)) {
                        return true;
                    }
                }
            } catch (Exception ignored) {
                return false;
            }
        }
        return false;
    }
}
