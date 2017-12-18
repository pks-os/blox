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
package steps.helpers;

import java.util.List;
import java.util.function.Function;

/**
 * Represents an entity that caches previous values. The values can be of any type, and the history
 * is kept per type.
 */
public interface Memoized {

  /**
   * Retrieves a value from a particular type's history.
   *
   * @param type the class of the objects
   * @param fn a function to extract the value from the list of historical values
   * @param <T> the type of the objects
   * @return the value
   */
  <T> T getFromHistory(Class<T> type, Function<List<Object>, Object> fn);

  /**
   * Retrieves the last value from a particular type's history.
   *
   * @param type the class of the objects
   * @param <T> the type of the objects
   * @return the last value
   */
  <T> T getLastFromHistory(Class<T> type);

  /**
   * Store a new value in the type's history.
   *
   * @param type the class of the objects
   * @param value the value to store
   * @param <T> the type of the objects
   */
  <T> void addToHistory(Class<T> type, T value);
}
