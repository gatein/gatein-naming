package org.gatein.naming;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * @author <a htef="mailto:mstrukel@redhat.com">Marko Strukelj</>
 */
public class FailoverInitialContextFactory implements javax.naming.spi.InitialContextFactory {
    /**
     * Get an initial context instance.
     *
     * @param environment The naming environment
     * @return A naming context instance
     * @throws javax.naming.NamingException
     */
    @SuppressWarnings("unchecked")
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {

        Context failover;

        String factory = System.getProperty("org.gatein.naming.fallback.factory");
        if (factory != null) {
            Hashtable params = new Hashtable();
            params.put(Context.INITIAL_CONTEXT_FACTORY, factory);

            String url = System.getProperty("org.gatein.naming.fallback.url");
            if (url != null)
                params.put(Context.PROVIDER_URL, url);
            failover = new InitialContext(params);
        }
        else {
            Hashtable params = new Hashtable();
            params.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
            failover = new InitialContext(params);
        }
        return new FailoverNamingContext(failover, new NamingContext((Hashtable<String, Object>) environment));
    }
}
