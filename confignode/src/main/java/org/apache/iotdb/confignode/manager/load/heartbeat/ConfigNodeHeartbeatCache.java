/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.confignode.manager.load.heartbeat;

import org.apache.iotdb.common.rpc.thrift.TConfigNodeLocation;
import org.apache.iotdb.commons.cluster.NodeStatus;
import org.apache.iotdb.confignode.manager.load.LoadManager;

import java.util.LinkedList;

public class ConfigNodeHeartbeatCache implements INodeCache {

  // Cache heartbeat samples
  private static final int maximumWindowSize = 100;
  private final LinkedList<NodeHeartbeatSample> slidingWindow;

  private final TConfigNodeLocation configNodeLocation;

  // For showing cluster
  private volatile NodeStatus status;

  public ConfigNodeHeartbeatCache(TConfigNodeLocation configNodeLocation) {
    this.configNodeLocation = configNodeLocation;
    this.slidingWindow = new LinkedList<>();
    this.status = NodeStatus.Running;
  }

  @Override
  public void cacheHeartbeatSample(NodeHeartbeatSample newHeartbeatSample) {
    synchronized (slidingWindow) {
      // Only sequential heartbeats are accepted.
      // And un-sequential heartbeats will be discarded.
      if (slidingWindow.size() == 0
          || slidingWindow.getLast().getSendTimestamp() < newHeartbeatSample.getSendTimestamp()) {
        slidingWindow.add(newHeartbeatSample);
      }

      if (slidingWindow.size() > maximumWindowSize) {
        slidingWindow.removeFirst();
      }
    }
  }

  @Override
  public boolean updateLoadStatistic() {
    if (configNodeLocation.getInternalEndPoint().equals(LoadManager.CURRENT_NODE)) {
      // We don't need to update itself
      return false;
    }

    long lastSendTime = 0;
    synchronized (slidingWindow) {
      if (slidingWindow.size() > 0) {
        lastSendTime = slidingWindow.getLast().getSendTimestamp();
      }
    }

    NodeStatus originStatus;
    switch (status) {
      case Running:
        originStatus = NodeStatus.Running;
        break;
      case Unknown:
      default:
        originStatus = NodeStatus.Unknown;
    }

    // TODO: Optimize judge logic
    if (System.currentTimeMillis() - lastSendTime > 20_000) {
      status = NodeStatus.Unknown;
    } else {
      status = NodeStatus.Running;
    }
    return !status.getStatus().equals(originStatus.getStatus());
  }

  @Override
  public long getLoadScore() {
    // Return a copy of loadScore
    switch (status) {
      case Running:
        return 0;
      case Unknown:
      default:
        // The Unknown Node will get the highest loadScore
        return Long.MAX_VALUE;
    }
  }

  @Override
  public NodeStatus getNodeStatus() {
    // Return a copy of status
    switch (status) {
      case Running:
        return NodeStatus.Running;
      case Unknown:
      default:
        return NodeStatus.Unknown;
    }
  }
}
