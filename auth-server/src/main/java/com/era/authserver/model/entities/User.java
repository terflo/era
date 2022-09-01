package com.era.authserver.model.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder(toBuilder = true)
public class User implements UserDetails {

    @Id
    @Column(unique = true)
    private String UUID;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Date timestamp;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    private boolean expired;
    private boolean locked;
    private boolean credentials_expired;
    private boolean enabled;

    public void switchLock() {
        this.locked = !this.locked;
    }

    public Set<String> getRoleNames() {
        return this.roles
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentials_expired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (expired != user.expired) return false;
        if (locked != user.locked) return false;
        if (credentials_expired != user.credentials_expired) return false;
        if (enabled != user.enabled) return false;
        if (!UUID.equals(user.UUID)) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (!email.equals(user.email)) return false;
        if (!timestamp.equals(user.timestamp)) return false;
        return roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        int result = UUID.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + roles.hashCode();
        result = 31 * result + (expired ? 1 : 0);
        result = 31 * result + (locked ? 1 : 0);
        result = 31 * result + (credentials_expired ? 1 : 0);
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
