/*
 * Copyright 2011 JBoss, by Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.marshalling.server;

import java.util.Map;

import org.jboss.errai.common.client.protocols.SerializationParts;
import org.jboss.errai.marshalling.client.api.AbstractMarshallingSession;

/**
 * @author Mike Brock
 */
public class EncodingSession extends AbstractMarshallingSession {

  public EncodingSession(ServerMappingContext context) {
    super(context);
  }

  @Override
  public String determineTypeFor(String formatType, Object o) {
    if (o == null) return null;

    if (o instanceof Map) {
      Map map = (Map) o;
      if (map.containsKey(SerializationParts.ENCODED_TYPE)) {
        return (String) map.get(SerializationParts.ENCODED_TYPE);
      }
      else {
        return Map.class.getName();
      }
    }
    else {
      return o.getClass().getName();
    }
  }
}
