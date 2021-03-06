/*
 * Copyright 2012-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.facebook.buck.rules;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class RobolectricTestRule extends JavaTestRule {

  protected RobolectricTestRule(BuildRuleParams buildRuleParams,
      Set<String> srcs,
      Set<String> resources,
      Set<String> labels,
      String proguardConfig,
      AnnotationProcessingParams annotationProcessors,
      List<String> vmArgs,
      ImmutableSet<JavaLibraryRule> sourceUnderTest,
      String sourceLevel,
      String targetLevel) {
    super(buildRuleParams,
        srcs,
        resources,
        labels,
        proguardConfig,
        annotationProcessors,
        vmArgs,
        sourceUnderTest,
        sourceLevel,
        targetLevel);
  }

  @Override
  public BuildRuleType getType() {
    return BuildRuleType.ROBOLECTRIC_TEST;
  }

  @Override
  public boolean isAndroidRule() {
    return true;
  }

  @Override
  protected List<String> getInputsToCompareToOutput(BuildContext context) {
    return super.getInputsToCompareToOutput(context);
  }

  public static Builder newRobolectricTestRuleBuilder() {
    return new Builder();
  }

  public static class Builder extends JavaTestRule.Builder {



    @Override
    public RobolectricTestRule build(Map<String, BuildRule> buildRuleIndex) {
      ImmutableSet<JavaLibraryRule> sourceUnderTest = generateSourceUnderTest(sourceUnderTestNames,
          buildRuleIndex);

      ImmutableList.Builder<String> allVmArgs = ImmutableList.builder();
      allVmArgs.addAll(vmArgs);

      return new RobolectricTestRule(createBuildRuleParams(buildRuleIndex),
          srcs,
          resources,
          labels,
          proguardConfig,
          getAnnotationProcessingBuilder().build(buildRuleIndex),
          allVmArgs.build(),
          sourceUnderTest,
          sourceLevel,
          targetLevel);
    }

  }
}
