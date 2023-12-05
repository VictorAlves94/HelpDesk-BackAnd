package com.victor.HelpDesk.resources.exceptions;

import java.io.Serializable;

public class FieldMessege implements Serializable {
    private static final long serialVersionUid = 1L;

    private String fieldName;
    private String fieldMessage;

    public FieldMessege(){
        super();
    }
    public FieldMessege(String fieldName, String fieldMessage) {
        this.fieldName = fieldName;
        this.fieldMessage = fieldMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }
}
