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

package org.jboss.errai.codegen.meta.impl.gwt;

import org.jboss.errai.codegen.meta.MetaGenericArrayType;
import org.jboss.errai.codegen.meta.MetaType;

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * @author Mike Brock <cbrock@redhat.com>
 */
public class GWTGenericArrayType implements MetaGenericArrayType {
  private final JArrayType arrayType;
  private final TypeOracle oracle;

  public GWTGenericArrayType(final TypeOracle oracle, final JArrayType arrayType) {
    this.arrayType = arrayType;
    this.oracle = oracle;
  }

  @Override
  public MetaType getGenericComponentType() {
    return GWTUtil.fromType(oracle, arrayType.getComponentType());
  }

  @Override
  public String getName() {
    return arrayType.getParameterizedQualifiedSourceName();
  }
}
