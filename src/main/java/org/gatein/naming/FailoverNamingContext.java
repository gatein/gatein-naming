package org.gatein.naming;

import javax.naming.*;
import javax.naming.event.EventContext;
import javax.naming.event.NamingListener;
import java.util.Hashtable;

/**
 * @author <a htef="mailto:mstrukel@redhat.com">Marko Strukelj</>
 */
public class FailoverNamingContext implements EventContext {

    private Context failover;
    private NamingContext delegate;

    public FailoverNamingContext(Context failover, NamingContext namingContext) {
        this.failover = failover;
        this.delegate = namingContext;
    }

    public void addNamingListener(final Name target, final int scope, final NamingListener listener) throws NamingException {
        delegate.addNamingListener(target, scope, listener);
    }

    public void addNamingListener(final String target, final int scope, final NamingListener listener) throws NamingException {
        delegate.addNamingListener(target, scope, listener);
    }

    public void removeNamingListener(final NamingListener listener) throws NamingException {
        delegate.removeNamingListener(listener);
    }

    public boolean targetMustExist() throws NamingException {
        return delegate.targetMustExist();
    }

    public Object lookup(final Name name) throws NamingException {
        try {
            return delegate.lookup(name);
        }
        catch(NamingException e) {
            return failover.lookup(name);
        }
    }

    public Object lookup(final String name) throws NamingException {
        try {
            return delegate.lookup(name);
        }
        catch(NamingException e) {
            return failover.lookup(name);
        }
    }

    public void bind(final Name name, final Object object) throws NamingException {
        delegate.bind(name, object);
    }

    public void bind(final String name, final Object object) throws NamingException {
        delegate.bind(name, object);
    }

    public void rebind(final Name name, final Object object) throws NamingException {
        delegate.rebind(name, object);
    }

    public void rebind(final String name, final Object object) throws NamingException {
        delegate.rebind(name, object);
    }

    public void unbind(final Name name) throws NamingException {
        delegate.unbind(name);
    }

    public void unbind(final String name) throws NamingException {
        delegate.unbind(name);
    }

    public void rename(final Name oldName, final Name newName) throws NamingException {
        delegate.rename(oldName, newName);
    }

    public void rename(final String oldName, final String newName) throws NamingException {
        delegate.rename(oldName, newName);
    }

    public NamingEnumeration<NameClassPair> list(final Name name) throws NamingException {
        try {
            return delegate.list(name);
        }
        catch(NamingException e) {
            return failover.list(name);
        }
    }

    public NamingEnumeration<NameClassPair> list(final String name) throws NamingException {
        try {
            return delegate.list(name);
        }
        catch(NamingException e) {
            return failover.list(name);
        }
    }

    public NamingEnumeration<Binding> listBindings(final Name name) throws NamingException {
        try {
            return delegate.listBindings(name);
        }
        catch(NamingException e) {
            return failover.listBindings(name);
        }
    }

    public NamingEnumeration<Binding> listBindings(final String name) throws NamingException {
        try {
            return delegate.listBindings(name);
        }
        catch(NamingException e) {
            return failover.listBindings(name);
        }
    }

    public void destroySubcontext(final Name name) throws NamingException {
        delegate.destroySubcontext(name);
    }

    public void destroySubcontext(final String name) throws NamingException {
        delegate.destroySubcontext(name);
    }

    public Context createSubcontext(final Name name) throws NamingException {
        return delegate.createSubcontext(name);
    }

    public Context createSubcontext(final String name) throws NamingException {
        return delegate.createSubcontext(name);
    }

    public Object lookupLink(final Name name) throws NamingException {
        try {
            return delegate.lookupLink(name);
        }
        catch (NamingException e) {
            return failover.lookupLink(name);
        }
    }

    public Object lookupLink(final String name) throws NamingException {
        try {
            return delegate.lookupLink(name);
        }
        catch (NamingException e) {
            return failover.lookupLink(name);
        }
    }

    public NameParser getNameParser(final Name name) throws NamingException {
        return delegate.getNameParser(name);
    }

    public NameParser getNameParser(final String name) throws NamingException {
        return delegate.getNameParser(name);
    }

    public Name composeName(final Name name, final Name prefix) throws NamingException {
        return delegate.composeName(name, prefix);
    }

    public String composeName(final String name, final String prefix) throws NamingException {
        return delegate.composeName(name, prefix);
    }

    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        return delegate.addToEnvironment(propName, propVal);
    }

    public Object removeFromEnvironment(String propName) throws NamingException {
        return delegate.removeFromEnvironment(propName);
    }

    public Hashtable<?, ?> getEnvironment() throws NamingException {
        return delegate.getEnvironment();
    }

    public void close() throws NamingException {
        try {
            delegate.close();
        }
        finally {
            try {
                failover.close();
            }
            catch(Throwable ignored) {

            }
        }
    }

    public String getNameInNamespace() throws NamingException {
        return delegate.getNameInNamespace();
    }
}
