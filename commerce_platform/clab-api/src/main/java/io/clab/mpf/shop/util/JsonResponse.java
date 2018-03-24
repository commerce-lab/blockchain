package io.clab.mpf.shop.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * 处理结果Vo
 * 
 *
 *
 *
 * @param <T>
 */
@ApiModel(value="处理结果",description="处理结果Vo")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class JsonResponse<T> implements Serializable {

    private static final long serialVersionUID = 2273610255200563857L;


    @ApiModelProperty("结果（1：成功 true , 0:失败 false）")
    private int res;
    

    @ApiModelProperty("结果")
    private String result;


    @ApiModelProperty("主键")
    private String privateKey;

    /**
     * code
     */
    private String code;

    @ApiModelProperty("消息")
    private String msg;


    @ApiModelProperty("对象")
    private T obj;


    @ApiModelProperty("结果集")
    private List<T> list;

    public JsonResponse(){
    	
    } 
    public JsonResponse(int res){
    	this.res=res;
    }
    public JsonResponse(int res,String result){
    	this.res=res;
    	this.result=result;
    }
    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}
