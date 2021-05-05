package com.cping.project.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    //success
    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    //override
    //when get success result. data is more important, code and msg can be default value
    public static Result succ(Object data) {
        return succ(200, "success", data);
    }

    //fail
    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }
}
