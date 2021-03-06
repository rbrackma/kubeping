/**
 *  Copyright 2014 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package org.jboss.kubeping.rest;

import java.util.logging.Logger;

import org.jgroups.Channel;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class JBossServerFactory implements ServerFactory {
    private static final Logger log = Logger.getLogger(JBossServerFactory.class.getName());

    public boolean isAvailable() {
        try {
            return JBossServerFactory.class.getClassLoader().loadClass("org.jboss.com.sun.net.httpserver.HttpServer") != null;
        } catch (Exception e) {
            log.warning(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Server create(int port, Channel channel) {
        return new JBossServer(port, channel);
    }
}
