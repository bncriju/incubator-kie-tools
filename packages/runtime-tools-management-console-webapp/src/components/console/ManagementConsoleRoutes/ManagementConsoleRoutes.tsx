/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import * as React from "react";
import { Redirect, Route, Switch } from "react-router-dom";
import { JobsPage, ProcessListPage, ProcessDetailsPage, TasksPage, TaskDetailsPage } from "../../pages";
import { PageNotFound } from "@kie-tools/runtime-tools-shared-webapp-components/dist/PageNotFound";
import { NoData } from "@kie-tools/runtime-tools-shared-webapp-components/dist/NoData";

const ManagementConsoleRoutes: React.FC = () => {
  return (
    <Switch>
      <Route exact path="/" render={() => <Redirect to="/ProcessInstances" />} />
      <Route exact path="/ProcessInstances" component={ProcessListPage} />
      <Route exact path="/Jobs" component={JobsPage} />
      <Route exact path="/Process/:instanceID" component={ProcessDetailsPage} />
      <Route exact path="/Tasks" component={TasksPage} />
      <Route exact path="/TaskDetails/:taskId" render={(routeProps) => <TaskDetailsPage {...routeProps} />} />
      <Route
        path="/NoData"
        render={(_props) => <NoData {..._props} defaultPath="/Jobs" defaultButton="Go to Jobs" />}
      />
      <Route
        path="*"
        render={(_props) => <PageNotFound {..._props} defaultPath="/Jobs" defaultButton="Go to Jobs" />}
      />
    </Switch>
  );
};

export default ManagementConsoleRoutes;
