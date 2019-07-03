package edu.usts.sddb.entity.bPack;

import java.util.List;

//为大数据分析服务的基本类
//课程依赖关系
//返回两个基本情况
// {
// (1)未通过的核心必修课程
// (2)课程预警
// }
public class BCourseFailAndWarn {
    //未通过的核心必修课的列表
    private List<FailRequestCourse> fail;

    //预警课程类的列表
    private List<WarnCourse> warn;

    public List<FailRequestCourse> getFail() {
        return fail;
    }

    public void setFail(List<FailRequestCourse> fail) {
        this.fail = fail;
    }

    public List<WarnCourse> getWarn() {
        return warn;
    }

    public void setWarn(List<WarnCourse> warn) {
        this.warn = warn;
    }
}


