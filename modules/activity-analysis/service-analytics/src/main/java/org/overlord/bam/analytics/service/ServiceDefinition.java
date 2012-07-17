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
package org.overlord.bam.analytics.service;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * This class represents the service contract details associated
 * with a service type.
 *
 */
public class ServiceDefinition implements java.io.Externalizable {

    private static final int VERSION = 1;

    private String _serviceType=null;
    private java.util.List<OperationDefinition> _operations=
                    new java.util.ArrayList<OperationDefinition>();

    /**
     * This method sets the service type.
     * 
     * @param serviceType The service type
     */
    public void setServiceType(String serviceType) {
        _serviceType = serviceType;
    }
    
    /**
     * This method gets the service type.
     * 
     * @return The service type
     */
    public String getServiceType() {
        return (_serviceType);
    }
    
    /**
     * This method sets the list of operations associated
     * with the service.
     * 
     * @param operations The operations
     */
    public void setOperations(java.util.List<OperationDefinition> operations) {
        _operations = operations;
    }
    
    /**
     * This method returns the list of operations associated
     * with the service.
     * 
     * @return The operations
     */
    public java.util.List<OperationDefinition> getOperations() {
        return (_operations);
    }
    
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (_serviceType.hashCode());
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object obj) {
        
        if (obj instanceof ServiceDefinition
                  && ((ServiceDefinition)obj).getServiceType().equals(_serviceType)) {
            return (true);
        }
        
        return (false);
    }
    
    /**
     * {@inheritDoc}
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(VERSION);
        
        out.writeObject(_serviceType);
        
        out.writeInt(_operations.size());
        for (int i=0; i < _operations.size(); i++) {
            out.writeObject(_operations.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        in.readInt(); // Consume version, as not required for now
        
        _serviceType = (String)in.readObject();
        
        int len=in.readInt();
        for (int i=0; i < len; i++) {
            _operations.add((OperationDefinition)in.readObject());
        }
    }
}
