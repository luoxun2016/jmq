package com.ipd.jmq.common.network.v3.command;

import com.ipd.jmq.toolkit.lang.Preconditions;

import java.util.Arrays;

/**
 * 更新重试
 */
public class UpdateRetry extends JMQPayload {
    public static byte SUCCESS = 0;
    public static byte EXPIRED = -2;
    public static byte FAILED = 1;
    private String topic;
    private String app;
    private long[] messages;
    private byte updateType;

    public UpdateRetry() {
    }

    public UpdateRetry app(final String app) {
        setApp(app);
        return this;
    }

    public UpdateRetry updateType(final byte updateType) {
        setUpdateType(updateType);
        return this;
    }

    public UpdateRetry messages(final long[] messages) {
        setMessages(messages);
        return this;
    }

    public UpdateRetry topic(final String topic) {
        this.topic = topic;
        return this;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getApp() {
        return this.app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public long[] getMessages() {
        return this.messages;
    }

    public void setMessages(long[] messages) {
        this.messages = messages;
    }

    public int getUpdateType() {
        return this.updateType;
    }

    public void setUpdateType(byte updateType) {
        this.updateType = updateType;
    }

    public int predictionSize() {
        int size = Serializer.getPredictionSize(topic, app) + 3;
        if (messages != null) {
            size += messages.length * 8;
        }
        size += 1;
        return size;
    }

    @Override
    public void validate() {
        super.validate();
        Preconditions.checkArgument(app != null && !app.isEmpty(), "app can not be empty.");
        Preconditions.checkArgument(topic != null && !topic.isEmpty(), "topic can not be empty.");
        Preconditions.checkArgument(messages != null && messages.length > 0, "messages can not be empty.");
    }

    @Override
    public int type() {
        return CmdTypes.UPDATE_RETRY;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateRetry{");
        sb.append("topic='").append(topic).append('\'');
        sb.append(", app='").append(app).append('\'');
        sb.append(", updateType=").append(updateType);
        sb.append(", messages=").append(Arrays.toString(messages));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        UpdateRetry that = (UpdateRetry) o;

        if (updateType != that.updateType) {
            return false;
        }
        if (app != null ? !app.equals(that.app) : that.app != null) {
            return false;
        }
        if (!Arrays.equals(messages, that.messages)) {
            return false;
        }
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (app != null ? app.hashCode() : 0);
        result = 31 * result + (messages != null ? Arrays.hashCode(messages) : 0);
        result = 31 * result + (int) updateType;
        return result;
    }
}