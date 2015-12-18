/**
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.errai.security.server;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.security.shared.api.Role;
import org.jboss.errai.security.shared.api.RoleImpl;
import org.jboss.errai.security.shared.api.identity.User;
import org.jboss.errai.security.shared.api.identity.UserImpl;
import org.jboss.errai.security.shared.service.AuthenticationService;

@Service
@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

  private User user = User.ANONYMOUS;

  @Override
  public User login(String username, String password) {
    user = new UserImpl(username, makeUpFakeRolesBasedOnUsername(username));
    return user;
  }

  @Override
  public boolean isLoggedIn() {
    return !user.equals(User.ANONYMOUS);
  }

  @Override
  public void logout() {
    user = User.ANONYMOUS;
  }

  @Override
  public User getUser() {
    return user;
  }

  private static Set<Role> makeUpFakeRolesBasedOnUsername(String username) {
    final Set<Role> roles = new HashSet<Role>();
    if (username.equals("admin")) {
      roles.add(new RoleImpl("admin"));
    }
    roles.add(new RoleImpl("user"));

    return roles;
  }

}
