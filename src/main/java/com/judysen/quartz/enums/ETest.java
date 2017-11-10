package com.judysen.quartz.enums;/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/4 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */

public enum ETest implements EnumInterface {
    Ok(1,"Ok1"),Fail(0,"Fail0");

    int val;
    String name;
    ETest(int a,String name){
        this.val=a;
        this.name=name;
    }

    @Override
    public String getDescription() {
        return this.name;
    }
}
