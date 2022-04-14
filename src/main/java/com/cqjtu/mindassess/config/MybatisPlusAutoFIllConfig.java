package com.cqjtu.mindassess.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cqjtu.mindassess.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author zhangning
 */
@Component
public class MybatisPlusAutoFIllConfig implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        this.strictInsertFill(metaObject, "creatorId",Long.class,userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        this.strictUpdateFill(metaObject, "updaterId", Long.class,userId);
    }
}
