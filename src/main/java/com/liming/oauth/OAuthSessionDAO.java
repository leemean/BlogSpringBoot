package com.liming.oauth;

import com.liming.common.cache.RedisManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * 将session保存到redis
 */
public class OAuthSessionDAO extends CachingSessionDAO implements InitializingBean {
    private RedisManager redisManager;

    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止，不更新
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()){
            return;
        }
        redisManager.set(session.getId().toString(),session,RedisManager.DEFAULT_EXPIRE);
    }

    @Override
    protected void doDelete(Session session) {
        redisManager.delete(session.getId().toString());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        redisManager.set(sessionId.toString(),session,RedisManager.DEFAULT_EXPIRE);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return redisManager.get(serializable.toString(),Session.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(null == this.redisManager){

        }
    }

    public void setRedisManager(RedisManager redisManager){
        this.redisManager = redisManager;
    }
}
