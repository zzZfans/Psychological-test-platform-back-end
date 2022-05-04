package com.cqjtu.mindassess.config.webconf;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;

import java.io.IOException;

public class NoSessionManager extends ManagerBase implements Lifecycle {

    @Override
    protected void startInternal() throws LifecycleException {
        super.startInternal();
        setState(LifecycleState.STARTING);
    }

    @Override
    protected void stopInternal() throws LifecycleException {
        setState(LifecycleState.STOPPING);
        super.stopInternal();
    }

    @Override
    public void load() throws ClassNotFoundException, IOException {

    }

    @Override
    public void unload() throws IOException {

    }

    @Override
    public Session createSession(String sessionId) {
        return null;
    }

    @Override
    public Session createEmptySession() {
        return null;
    }

    @Override
    public void add(Session session) {

    }

    @Override
    public Session findSession(String id) throws IOException {
        return null;
    }

    @Override
    public Session[] findSessions() {
        return null;
    }

    @Override
    public void processExpires() {

    }
}
