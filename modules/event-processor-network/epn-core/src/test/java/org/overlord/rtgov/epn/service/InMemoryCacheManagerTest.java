/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008-12, Red Hat Middleware LLC, and others contributors as indicated
 * by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.overlord.rtgov.epn.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.overlord.rtgov.epn.service.InMemoryCacheManager;

public class InMemoryCacheManagerTest {

    @Test
    public void testSameCacheReturned() {
        InMemoryCacheManager ecm=new InMemoryCacheManager();
        
        java.util.Map<String, Object> c1=ecm.getCache("TestCache");
        
        c1.put("test", "value");
        
        java.util.Map<String, Object> c2=ecm.getCache("TestCache");
        
        if (!c2.containsKey("test")) {
            fail("Didn't return same cache");
        }
        
    }

    @Test
    public void testCacheItemLocked() {
        InMemoryCacheManager ecm=new InMemoryCacheManager();
        
        // For embedded cache, lock should always succeed
        if (!ecm.lock("TestCache", "test")) {
            fail("Couldn't lock cache item");
        }
        
    }

}