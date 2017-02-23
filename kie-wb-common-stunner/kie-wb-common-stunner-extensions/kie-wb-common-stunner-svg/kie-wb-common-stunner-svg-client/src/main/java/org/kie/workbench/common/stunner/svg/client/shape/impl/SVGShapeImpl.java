/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.svg.client.shape.impl;

import org.kie.workbench.common.stunner.client.lienzo.shape.impl.LienzoShape;
import org.kie.workbench.common.stunner.core.client.shape.ShapeState;
import org.kie.workbench.common.stunner.svg.client.shape.SVGShape;
import org.kie.workbench.common.stunner.svg.client.shape.view.SVGShapeView;

public class SVGShapeImpl
        implements SVGShape<SVGShapeView<?>> {

    private final SVGShapeView<?> view;
    private final LienzoShape<?> shape;

    public SVGShapeImpl(final SVGShapeView<?> view) {
        this(view,
             new LienzoShape<SVGShapeView<?>>(view));
    }

    SVGShapeImpl(final SVGShapeView<?> view,
                 final LienzoShape<?> shape) {
        this.view = view;
        this.shape = shape;
    }

    @Override
    public void setUUID(final String uuid) {
        shape.setUUID(uuid);
    }

    @Override
    public String getUUID() {
        return shape.getUUID();
    }

    @Override
    public void beforeDraw() {
        shape.beforeDraw();
    }

    @Override
    public void afterDraw() {
        shape.afterDraw();
    }

    @Override
    public void applyState(final ShapeState shapeState) {
        shape.applyState(shapeState);
    }

    @Override
    public void destroy() {
        shape.destroy();
    }

    @Override
    public SVGShapeView<?> getShapeView() {
        return view;
    }
}
