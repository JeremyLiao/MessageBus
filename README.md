# MessageBus
- 一个android平台的基于订阅-发布模式的消息框架
- **支持跨进程消息通信**
MessageBus是对Android动态广播的封装，所以支持跨进程消息通信

## MessageBus 3 Steps:
#### 1、注册接收消息的接口

```java
    @BroadcastAction(action = "com.action.message")
    public void onMessageReceived(Intent intent) {
        //
    }
```

#### 2、订阅和退订

例如在Activity的onCreate中订阅，在onDestroy中取消订阅，注意订阅和取消订阅一定要配对出现

```java
    protected void onCreate(Bundle savedInstanceState) {
        MessageBus.getDefault().register(this, this);
    }
```

```java
    protected void onDestroy() {
        super.onDestroy();
        MessageBus.getDefault().unregister(this);
    }
```

#### 3、发送消息

可以使用下面的代码或者直接发送一个广播

```java
 Intent intent = new Intent("com.action.message");
 intent.putExtra("data", "new message");
 MessageBus.getDefault().post(this, intent);
```

## Add MessageBus to your project

Please ensure that you are using the latest version by checking here

Gradle:

```
compile 'com.lhl:message-bus:0.0.1'
```

Maven:

```
<dependency>
  <groupId>com.lhl</groupId>
  <artifactId>message-bus</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```

## Why use MessageBus

- MessageBus是一个android平台的基于订阅-发布模式的消息框架，支持跨进程消息通信
- 主要可以用来代替android的动态广播
- 如果使用动态广播，我们的代码中会增加很多重复的注册动态广播，解注册广播的代码，这些重复的代码不仅写起来很烦，而且这些动态广播也降低了代码的可读性

#### MessageBus比EventBus好在哪：
- 1、轻量级
- 2、支持进程间通信，支持跨APP的通信
