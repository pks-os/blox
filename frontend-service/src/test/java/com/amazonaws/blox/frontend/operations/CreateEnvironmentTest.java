/*
 * Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may
 * not use this file except in compliance with the License. A copy of the
 * License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.blox.frontend.operations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.amazonaws.blox.dataservicemodel.v1.model.DeploymentConfiguration;
import com.amazonaws.blox.dataservicemodel.v1.model.EnvironmentId;
import com.amazonaws.blox.dataservicemodel.v1.model.InstanceGroup;
import com.amazonaws.blox.frontend.mappers.CreateEnvironmentMapper;
import com.amazonaws.blox.frontend.operations.CreateEnvironment.CreateEnvironmentRequest;
import com.amazonaws.blox.frontend.operations.CreateEnvironment.CreateEnvironmentResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateEnvironmentTest extends EnvironmentControllerTestCase {
  @Autowired CreateEnvironment controller;
  @Autowired CreateEnvironmentMapper mapper;

  @Test
  public void mapsInputsAndOutputsCorrectly() throws Exception {
    EnvironmentId id =
        EnvironmentId.builder()
            .accountId(ACCOUNT_ID)
            .cluster(TEST_CLUSTER)
            .environmentName(ENVIRONMENT_NAME)
            .build();

    InstanceGroup instanceGroup = instanceGroupWithAttributeDS(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
    DeploymentConfiguration deploymentConfiguration = deploymentConfigurationDS();

    when(dataService.createEnvironment(any()))
        .thenReturn(
            com.amazonaws.blox.dataservicemodel.v1.model.wrappers.CreateEnvironmentResponse
                .builder()
                .environment(environmentDS(id, deploymentConfiguration))
                .environmentRevision(environmentRevisionDS(id, instanceGroup))
                .build());

    CreateEnvironmentResponse response =
        controller.createEnvironment(
            TEST_CLUSTER,
            CreateEnvironmentRequest.builder()
                .environmentName(ENVIRONMENT_NAME)
                .environmentType(ENVIRONMENT_TYPE_STRING)
                .role(ROLE)
                .deploymentMethod(DEPLOYMENT_METHOD)
                .deploymentConfiguration(deploymentConfigurationFE())
                .instanceGroup(instanceGroupWithAttributeFE(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
                .taskDefinition(TASK_DEFINITION)
                .build());

    verify(dataService)
        .createEnvironment(
            com.amazonaws.blox.dataservicemodel.v1.model.wrappers.CreateEnvironmentRequest.builder()
                .environmentId(id)
                .environmentType(ENVIRONMENT_TYPE)
                .deploymentMethod(DEPLOYMENT_METHOD)
                .deploymentConfiguration(deploymentConfiguration)
                .instanceGroup(instanceGroup)
                .role(ROLE)
                .taskDefinition(TASK_DEFINITION)
                .build());

    assertThat(response).isNotNull();

    assertThat(response.getEnvironmentRevisionId()).isEqualTo(ENVIRONMENT_REVISION_ID);
  }
}
