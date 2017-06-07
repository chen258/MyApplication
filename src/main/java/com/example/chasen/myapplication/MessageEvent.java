package com.example.chasen.myapplication;

/**
 * Created by chasen on 2017/5/25.
 */

class MessageEvent {
    public static int USER_INFO_CHANGE = 0x10;
    public static int USER_INFO_ADD = 0x11;
    public static int USER_INFO_DEL = 0x12;
    public static int USER_INFO_FIND = 0x13;

    public int getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(int eventInfo) {
        this.eventInfo = eventInfo;
    }

    private int eventInfo;

    public static class Builder{
        public MessageEvent buildEventInfo(int info){
            MessageEvent event = new MessageEvent();
            event.setEventInfo(info);
            return event;
        };
    }
//    public class ContainerBuilder implements com.example.chasen.myapplication.MessageEvent.Builder{
//        MessageEvent event;
//
//        public ContainerBuilder(MessageEvent event) {
//            this.event = event;
//        }
//
//        @Override
//        public void buildEventInfo(int info) {
//            event.setEventInfo(info);
//        }
//
//        @Override
//        public MessageEvent getMsg() {
//            return event;
//        }
//    }
}
