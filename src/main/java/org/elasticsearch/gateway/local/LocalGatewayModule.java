/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.gateway.local;

import org.elasticsearch.cluster.routing.allocation.allocator.ShardsAllocatorModule;
import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.inject.PreProcessModule;
import org.elasticsearch.gateway.Gateway;
import org.elasticsearch.gateway.local.state.meta.LocalGatewayMetaState;
import org.elasticsearch.gateway.local.state.meta.TransportNodesListGatewayMetaState;
import org.elasticsearch.gateway.local.state.shards.LocalGatewayShardsState;
import org.elasticsearch.gateway.local.state.shards.TransportNodesListGatewayStartedShards;

/**
 *
 */
public class LocalGatewayModule extends AbstractModule implements PreProcessModule {

    @Override
    protected void configure() {
        bind(Gateway.class).to(LocalGateway.class).asEagerSingleton();
        bind(LocalGatewayShardsState.class).asEagerSingleton();
        bind(TransportNodesListGatewayMetaState.class).asEagerSingleton();
        bind(LocalGatewayMetaState.class).asEagerSingleton();
        bind(TransportNodesListGatewayStartedShards.class).asEagerSingleton();
    }

    @Override
    public void processModule(Module module) {
        if (module instanceof ShardsAllocatorModule) {
            ((ShardsAllocatorModule) module).setGatewayAllocator(LocalGatewayAllocator.class);
        }
    }
}
