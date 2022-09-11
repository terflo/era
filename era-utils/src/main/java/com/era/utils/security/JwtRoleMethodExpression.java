package com.era.utils.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class JwtRoleMethodExpression extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    public JwtRoleMethodExpression(Authentication authentication) {
        super(authentication);
    }

    public boolean jwtRoleAllowed(String roleName) {

        if(this.authentication instanceof JwtAuthenticationToken jwt) {
            try {
                List<String> grantedAuthorityList = (List<String>) jwt.getToken().getClaims().get("authorities");
                for (String grantedAuth : grantedAuthorityList) {
                    if (grantedAuth.toUpperCase().contains(roleName)) {
                        return true;
                    }
                }
            } catch (Exception ignored) {
                return false;
            }
        }
        return false;

    }

    @Override
    public void setFilterObject(Object filterObject) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object returnObject) {

    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }
}
