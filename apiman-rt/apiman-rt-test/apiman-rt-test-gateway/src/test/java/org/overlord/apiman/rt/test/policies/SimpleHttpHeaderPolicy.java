/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.apiman.rt.test.policies;

import org.overlord.apiman.rt.engine.beans.ServiceRequest;
import org.overlord.apiman.rt.engine.beans.ServiceResponse;
import org.overlord.apiman.rt.engine.policy.IPolicy;
import org.overlord.apiman.rt.engine.policy.IPolicyChain;
import org.overlord.apiman.rt.engine.policy.IPolicyContext;

/**
 * A simple policy used to test adding custom headers to the inbound request prior
 * to proxying to the back end system.
 *
 * @author eric.wittmann@redhat.com
 */
public class SimpleHttpHeaderPolicy implements IPolicy {
    
    /**
     * Constructor.
     */
    public SimpleHttpHeaderPolicy() {
    }

    /**
     * @see org.overlord.apiman.rt.engine.policy.IPolicy#parseConfiguration(java.lang.String)
     */
    @Override
    public Object parseConfiguration(String jsonConfiguration) {
        return new Object();
    }

    /**
     * @see org.overlord.apiman.rt.engine.policy.IPolicy#apply(org.overlord.apiman.rt.engine.beans.ServiceRequest, org.overlord.apiman.rt.engine.policy.IPolicyContext, java.lang.Object, org.overlord.apiman.rt.engine.policy.IPolicyChain)
     */
    @Override
    public void apply(ServiceRequest request, IPolicyContext context, Object config, IPolicyChain chain) {
        request.getHeaders().put("X-SimpleHttpHeaderPolicy-1", "foo"); //$NON-NLS-1$ //$NON-NLS-2$
        request.getHeaders().put("X-SimpleHttpHeaderPolicy-2", "bar"); //$NON-NLS-1$ //$NON-NLS-2$
        chain.doApply(request);
    }

    /**
     * @see org.overlord.apiman.rt.engine.policy.IPolicy#apply(org.overlord.apiman.rt.engine.beans.ServiceResponse, org.overlord.apiman.rt.engine.policy.IPolicyContext, java.lang.Object, org.overlord.apiman.rt.engine.policy.IPolicyChain)
     */
    @Override
    public void apply(ServiceResponse response, IPolicyContext context, Object config, IPolicyChain chain) {
        chain.doApply(response);
    }

}
