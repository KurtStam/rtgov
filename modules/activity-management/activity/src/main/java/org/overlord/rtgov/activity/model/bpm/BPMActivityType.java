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
package org.overlord.rtgov.activity.model.bpm;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.Entity;

import org.overlord.rtgov.activity.model.ActivityType;
import org.overlord.rtgov.activity.model.Context;

/**
 * This activity type represents a BPM actvity.
 *
 */
@Entity
public abstract class BPMActivityType extends ActivityType implements java.io.Externalizable {

    private static final int VERSION = 1;

    private String _processType=null;
    private String _instanceId=null;

    /**
     * The default constructor.
     */
    public BPMActivityType() {
    }
    
    /**
     * The copy constructor.
     * 
     * @param ba The bpm activity to copy
     */
    public BPMActivityType(BPMActivityType ba) {
        super(ba);
        _processType = ba._processType;
        _instanceId = ba._instanceId;
    }
    
    /**
     * This method sets the process type.
     * 
     * @param processType The process type
     */
    public void setProcessType(String processType) {
        _processType = processType;
        
        updateEndpointContext();
    }
    
    /**
     * This method gets the process type.
     * 
     * @return The process type
     */
    public String getProcessType() {
        return (_processType);
    }
   
    /**
     * This method sets the instance id. The information is
     * actually stored as a context entry for the Endpoint type.
     * 
     * @param instanceId The instance id
     */
    public void setInstanceId(String instanceId) {
        _instanceId = instanceId;
        
        updateEndpointContext();
    }
    
    /**
     * This method gets the instance id.
     * 
     * @return The instance id
     */
    public String getInstanceId() {
        return (_instanceId);
    }

    /**
     * This method updates the endpoint context value
     * when the process type and/or instance id are
     * changed.
     */
    protected void updateEndpointContext() {
        Context current=null;
        
        for (Context context : getContext()) {
            if (context.getType() == Context.Type.Endpoint) {
                current = context;
                break;
            }
        }
        
        if (current == null) {
            current = new Context();
            current.setType(Context.Type.Endpoint);
            getContext().add(current);
        }
        
        String endpoint="";
        
        if (_processType != null) {
            endpoint = _processType;
            
            if (_instanceId != null) {
                endpoint += ":";
            }
        }
        
        if (_instanceId != null) {
            endpoint += _instanceId;
        }
        
        current.setValue(endpoint);
    }
    
    /**
     * {@inheritDoc}
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        
        out.writeInt(VERSION);
        
        out.writeObject(_processType);
        out.writeObject(_instanceId);
    }

    /**
     * {@inheritDoc}
     */
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        super.readExternal(in);
        
        in.readInt(); // Consume version, as not required for now
        
        _processType = (String)in.readObject();
        _instanceId = (String)in.readObject();
    }
}
