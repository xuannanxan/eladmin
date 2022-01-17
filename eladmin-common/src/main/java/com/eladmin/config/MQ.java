package com.eladmin.config;


import com.base.core.annotation.FieldDescribe;
import com.base.core.json.JsonPropertyObject;
import org.apache.commons.lang3.BooleanUtils;

public class MQ extends JsonPropertyObject {

    @FieldDescribe("是否启用.")
    private Boolean enable;

    @FieldDescribe("消息服务类型")
    private String mq;

    @FieldDescribe("Kafka服务器配置")
    private MQKafka kafka;



    public static MQ defaultInstance() {
        return new MQ();
    }

    public static final Boolean default_enable = false;
    public static final String default_mq = "kafka";

    public MQ() {
        this.enable = default_enable;
        this.mq = default_mq;

    }

    public Boolean getEnable() {
        return BooleanUtils.isTrue(this.enable);
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getMq() {
        return mq;
    }

    public void setMq(String mq) {
        this.mq = mq;
    }

    public MQKafka getKafka() {
        return kafka;
    }

    public void setKafka(MQKafka kafka) {
        this.kafka = kafka;
    }






}
