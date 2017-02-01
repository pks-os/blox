// Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License"). You may
// not use this file except in compliance with the License. A copy of the
// License is located at
//
//     http://aws.amazon.com/apache2.0/
//
// or in the "license" file accompanying this file. This file is distributed
// on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
// express or implied. See the License for the specific language governing
// permissions and limitations under the License.

// Automatically generated by MockGen. DO NOT EDIT!
// Source: pkg/deployment/deployment_worker.go

package mocks

import (
	context "context"
	types "github.com/blox/blox/daemon-scheduler/pkg/types"
	gomock "github.com/golang/mock/gomock"
)

// Mock of DeploymentWorker interface
type MockDeploymentWorker struct {
	ctrl     *gomock.Controller
	recorder *_MockDeploymentWorkerRecorder
}

// Recorder for MockDeploymentWorker (not exported)
type _MockDeploymentWorkerRecorder struct {
	mock *MockDeploymentWorker
}

func NewMockDeploymentWorker(ctrl *gomock.Controller) *MockDeploymentWorker {
	mock := &MockDeploymentWorker{ctrl: ctrl}
	mock.recorder = &_MockDeploymentWorkerRecorder{mock}
	return mock
}

func (_m *MockDeploymentWorker) EXPECT() *_MockDeploymentWorkerRecorder {
	return _m.recorder
}

func (_m *MockDeploymentWorker) StartPendingDeployment(ctx context.Context, environmentName string) (*types.Deployment, error) {
	ret := _m.ctrl.Call(_m, "StartPendingDeployment", ctx, environmentName)
	ret0, _ := ret[0].(*types.Deployment)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

func (_mr *_MockDeploymentWorkerRecorder) StartPendingDeployment(arg0, arg1 interface{}) *gomock.Call {
	return _mr.mock.ctrl.RecordCall(_mr.mock, "StartPendingDeployment", arg0, arg1)
}

func (_m *MockDeploymentWorker) UpdateInProgressDeployment(ctx context.Context, environmentName string) (*types.Deployment, error) {
	ret := _m.ctrl.Call(_m, "UpdateInProgressDeployment", ctx, environmentName)
	ret0, _ := ret[0].(*types.Deployment)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

func (_mr *_MockDeploymentWorkerRecorder) UpdateInProgressDeployment(arg0, arg1 interface{}) *gomock.Call {
	return _mr.mock.ctrl.RecordCall(_mr.mock, "UpdateInProgressDeployment", arg0, arg1)
}
