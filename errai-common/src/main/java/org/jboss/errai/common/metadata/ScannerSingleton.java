/*
 * Copyright 2010 JBoss, a divison Red Hat, Inc
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
package org.jboss.errai.common.metadata;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Shared scanner instance used with {@link com.google.gwt.core.ext.Generator}'s
 *
 * @author Heiko Braun
 * @author Mike Brock
 */
public class ScannerSingleton {
  private static volatile MetaDataScanner scanner;

  private static final FutureTask<MetaDataScanner> future = new FutureTask<MetaDataScanner>(
      new Callable<MetaDataScanner>() {
        @Override
        public MetaDataScanner call() throws Exception {
          if (Boolean.getBoolean("errai.reflections.cache")
              && RebindUtils.cacheFileExists(RebindUtils.getClasspathHash() + ".cache.xml")) {
              return MetaDataScanner.createInstanceFromCache();
          }

          return MetaDataScanner.createInstance();
        }
      }
  );

  static {
    new Thread(future).start();
  }

  private static final Object lock = new Object();

  public static MetaDataScanner getOrCreateInstance() {
    synchronized (lock) {
      if (scanner == null) {
        try {
          scanner = future.get();

          if (scanner != null && Boolean.getBoolean("errai.reflections.cache") ) {
            scanner.save(RebindUtils.getCacheFile(RebindUtils.getClasspathHash() + ".cache.xml").getAbsolutePath());
          }
        }
        catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("failed to load class metadata", t);
        }
      }
      return scanner;
    }
  }
}
