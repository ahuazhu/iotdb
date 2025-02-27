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
package org.apache.iotdb.itbase.env;

import java.util.Properties;

public interface BaseConfig {

  default void clearAllProperties() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method clearAllProperties not implement");
  }

  default Properties getEngineProperties() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method getEngineProperties not implement");
  }

  default Properties getConfignodeProperties() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method getConfignodeProperties not implement");
  }

  default BaseConfig setMaxNumberOfPointsInPage(int maxNumberOfPointsInPage) {
    return this;
  }

  default int getPageSizeInByte() {
    return 64 * 1024;
  }

  default BaseConfig setPageSizeInByte(int pageSizeInByte) {
    return this;
  }

  default int getGroupSizeInByte() {
    return 128 * 1024 * 1024;
  }

  default BaseConfig setGroupSizeInByte(int groupSizeInByte) {
    return this;
  }

  default long getMemtableSizeThreshold() {
    return 1024 * 1024 * 1024L;
  }

  default BaseConfig setMemtableSizeThreshold(long memtableSizeThreshold) {
    return this;
  }

  default int getDataRegionNum() {
    return 1;
  }

  default BaseConfig setDataRegionNum(int dataRegionNum) {
    return this;
  }

  default BaseConfig setPartitionInterval(long partitionInterval) {
    return this;
  }

  default long getPartitionInterval() {
    return 604800;
  }

  default BaseConfig setCompressor(String compressor) {
    return this;
  }

  default BaseConfig setMaxQueryDeduplicatedPathNum(int maxQueryDeduplicatedPathNum) {
    return this;
  }

  default BaseConfig setRpcThriftCompressionEnable(boolean rpcThriftCompressionEnable) {
    return this;
  }

  default BaseConfig setRpcAdvancedCompressionEnable(boolean rpcAdvancedCompressionEnable) {
    return this;
  }

  default BaseConfig setEnablePartition(boolean enablePartition) {
    return this;
  }

  default BaseConfig setUdfCollectorMemoryBudgetInMB(float udfCollectorMemoryBudgetInMB) {
    return this;
  }

  default BaseConfig setUdfTransformerMemoryBudgetInMB(float udfTransformerMemoryBudgetInMB) {
    return this;
  }

  default BaseConfig setUdfReaderMemoryBudgetInMB(float udfReaderMemoryBudgetInMB) {
    return this;
  }

  default BaseConfig setEnableSeqSpaceCompaction(boolean enableSeqSpaceCompaction) {
    return this;
  }

  default boolean isEnableSeqSpaceCompaction() {
    return true;
  }

  default BaseConfig setEnableUnseqSpaceCompaction(boolean enableUnseqSpaceCompaction) {
    return this;
  }

  default boolean isEnableUnseqSpaceCompaction() {
    return true;
  }

  default BaseConfig setEnableCrossSpaceCompaction(boolean enableCrossSpaceCompaction) {
    return this;
  }

  default boolean isEnableCrossSpaceCompaction() {
    return true;
  }

  default BaseConfig setEnableIDTable(boolean isEnableIDTable) {
    return this;
  }

  default BaseConfig setDeviceIDTransformationMethod(String deviceIDTransformationMethod) {
    return this;
  }

  default BaseConfig setAutoCreateSchemaEnabled(boolean enableAutoCreateSchema) {
    return this;
  }

  default BaseConfig setEnableLastCache(boolean lastCacheEnable) {
    return this;
  }

  default boolean isLastCacheEnabled() {
    return true;
  }

  default int getMaxNumberOfPointsInPage() {
    return 1024 * 1024;
  }

  default boolean isAutoCreateSchemaEnabled() {
    return true;
  }

  default BaseConfig setPrimitiveArraySize(int primitiveArraySize) {
    return this;
  }

  default int getPrimitiveArraySize() {
    return 32;
  }

  default String getFlushCommand() {
    return "flush";
  }

  default int getMaxQueryDeduplicatedPathNum() {
    return 1000;
  }

  default int getAvgSeriesPointNumberThreshold() {
    return 100000;
  }

  default BaseConfig setAvgSeriesPointNumberThreshold(int avgSeriesPointNumberThreshold) {
    return this;
  }

  default int getMaxTsBlockLineNumber() {
    return 1000;
  }

  default BaseConfig setMaxTsBlockLineNumber(int maxTsBlockLineNumber) {
    return this;
  }

  default BaseConfig setConfigNodeConsesusProtocolClass(String configNodeConsesusProtocolClass) {
    return this;
  }

  default String getConfigNodeConsesusProtocolClass() {
    return "org.apache.iotdb.consensus.standalone.StandAloneConsensus";
  }

  default BaseConfig setSchemaRegionConsensusProtocolClass(
      String schemaRegionConsensusProtocolClass) {
    return this;
  }

  default String getSchemaRegionConsensusProtocolClass() {
    return "org.apache.iotdb.consensus.standalone.StandAloneConsensus";
  }

  default BaseConfig setDataRegionConsensusProtocolClass(String dataRegionConsensusProtocolClass) {
    return this;
  }

  default String getDataRegionConsensusProtocolClass() {
    return "org.apache.iotdb.consensus.standalone.StandAloneConsensus";
  }
}
