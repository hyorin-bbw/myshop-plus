package com.hyorin.myshop.plus.commons.dto;

import java.io.Serializable;

/**
 * 通用数据传输对象
 * <p>
 * Description:
 * </p>
 *
 * @author hyorin
 * @version v1.0.0
 * @date 2020.10.13
 * @see com.hyorin.myshop.plus.commons.dto.ResponseResult
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 3468352004150968551L;

    /**
     * 状态码
     */
    private Integer state;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;

    /**
     * 业务状态枚举类
     */
    public enum Code {
        SUCCESS(0), FAILED(-1),UN_LOGIN(1000);
        private int value;

        Code(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer state) {
        super();
        this.state = state;
    }

    public ResponseResult(Integer state, String message) {
        super();
        this.state = state;
        this.message = message;
    }

    public ResponseResult(Integer state, Throwable throwable) {
        super();
        this.state = state;
        this.message = throwable.getMessage();
    }

    public ResponseResult(Integer state, T data) {
        super();
        this.state = state;
        this.data = data;
    }

    public ResponseResult(Integer state, String message, T data) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResponseResult<?> other = (ResponseResult<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (state == null) {
            if (other.state != null) {
                return false;
            }
        } else if (!state.equals(other.state)) {
            return false;
        }
        return true;
    }
}