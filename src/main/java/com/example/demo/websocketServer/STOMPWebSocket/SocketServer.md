1.websocket Server 
-----------------------
##### 描述：
       当前模块为WEBSOCKET 服务端 基于STOMP

STOMP(Simple Text-Orientated Messaging Protocol) 面向消息的简单文本协议

WebSocket是一个消息架构，不强制使用任何特定的消息协议，它依赖于应用层解释消息的含义；

与处在应用层的HTTP不同，WebSocket处在TCP上非常薄的一层，会将字节流转换为文本/二进制消息，因此，对于实际应用来说，WebSocket的通信形式层级过低，因此，可以在 WebSocket 之上使用 STOMP协议，来为浏览器 和 server间的 通信增加适当的消息语义。

如何理解 STOMP 与 WebSocket 的关系：
1) HTTP协议解决了 web 浏览器发起请求以及 web 服务器响应请求的细节，假设 HTTP 协议 并不存在，只能使用 TCP 套接字来 编写 web 应用，你可能认为这是一件疯狂的事情；
2) 直接使用 WebSocket（SockJS） 就很类似于 使用 TCP 套接字来编写 web 应用，因为没有高层协议，就需要我们定义应用间所发送消息的语义，还需要确保连接的两端都能遵循这些语义；
3) 同 HTTP 在 TCP 套接字上添加请求-响应模型层一样，STOMP 在 WebSocket 之上提供了一个基于帧的线路格式层，用来定义消息语义；
STOMP帧
STOMP帧由命令，一个或多个头信息、一个空行及负载（文本或字节）所组成；

##其中可用的COMMAND 包括：
```
CONNECT、SEND、SUBSCRIBE、UNSUBSCRIBE、BEGIN、COMMIT、ABORT、ACK、NACK、DISCONNECT；
```
例：
##发送消息
```
SEND
destination:/queue/trade
content-type:application/json
content-length:44
{“action”:”BUY”,”ticker”:”MMM”,”shares”,44}^@
```
##订阅消息
```
    SUBSCRIBE
    id:sub-1
    destination:/topic/price.stock.*
    ^@
   ```
##服务器进行广播消息
```
MESSAGE
message-id:nxahklf6-1
subscription:sub-1
destination:/topic/price.stock.MMM
{“ticker”:”MMM”,”price”:129.45}^@
```
##客户端 API
引入stomp.js
```
<script type="application/javascript" src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
```
##发起连接
客户端可以通过使用Stomp.js和sockjs-client连接
```
// 建立连接对象（还未发起连接）
var socket=new SockJS("/spring-websocket-portfolio/portfolio");
 
// 获取 STOMP 子协议的客户端对象
var stompClient = Stomp.over(socket);
 
// 向服务器发起websocket连接并发送CONNECT帧
stompClient.connect(
    {},
function connectCallback (frame) {
    // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
        document.getElementById("state-info").innerHTML = "连接成功";
        console.log('已连接【' + frame + '】');
        stompClient.subscribe('/topic/getResponse', function (response) {
            showResponse(response.body);
        });
    },
function errorCallBack (error) {
    // 连接失败时（服务器响应 ERROR 帧）的回调方法
        document.getElementById("state-info").innerHTML = "连接失败";
        console.log('连接失败【' + error + '】');
    }
);
```
###说明：

说明：
1) socket连接对象也可通过WebSocket(不通过SockJS)连接
```
var socket=new WebSocket("/spring-websocket-portfolio/portfolio");
```

2) stompClient.connect()方法签名：
```
client.connect(headers, connectCallback, errorCallback);
```
其中
headers表示客户端的认证信息，如：
```
var headers = {
  login: 'mylogin',
  passcode: 'mypasscode',
  // additional header
  'client-id': 'my-client-id'
};
```
若无需认证，直接使用空对象 “{}” 即可；

connectCallback 表示连接成功时（服务器响应 CONNECTED 帧）的回调方法；
errorCallback 表示连接失败时（服务器响应 ERROR 帧）的回调方法，非必须；

###断开连接
若要从客户端主动断开连接，可调用 disconnect() 方法
```
client.disconnect(function () {
   alert("See you next time!");
};
```
该方法为异步进行，因此包含了回调参数，操作完成时自动回调；

###心跳机制
若使用STOMP 1.1 版本，默认开启了心跳检测机制，可通过client对象的heartbeat field进行配置（默认值都是10000 ms）：
```
client.heartbeat.outgoing = 20000;  // client will send heartbeats every 20000ms
client.heartbeat.incoming = 0;      // client does not want to receive heartbeats from the server
// The heart-beating is using window.setInterval() to regularly send heart-beats and/or check server heart-beats
```

###发送信息
连接成功后，客户端可使用 send() 方法向服务器发送信息：
```
client.send(destination url[, headers[, body]]);
```
其中
destination url 为服务器 controller中 @MessageMapping 中匹配的URL，字符串，必须参数；
headers 为发送信息的header，JavaScript 对象，可选参数；
body 为发送信息的 body，字符串，可选参数；

例：
```
client.send("/queue/test", {priority: 9}, "Hello, STOMP");
client.send("/queue/test", {}, "Hello, STOMP");
```
###订阅、接收信息
STOMP 客户端要想接收来自服务器推送的消息，必须先订阅相应的URL，即发送一个 SUBSCRIBE 帧，然后才能不断接收来自服务器的推送消息；
订阅和接收消息通过 subscribe() 方法实现：
```
subscribe(destination url, callback[, headers])
```
其中
destination url 为服务器 @SendTo 匹配的 URL，字符串；
callback 为每次收到服务器推送的消息时的回调方法，该方法包含参数 message；
headers 为附加的headers，JavaScript 对象；什么作用？
该方法返回一个包含了id属性的 JavaScript 对象，可作为 unsubscribe() 方法的参数；

例：
```
var headers = {ack: 'client', 'selector': "location = 'Europe'"};
var  callback = function(message) {
  if (message.body) {
    alert("got message with body " + message.body)
  } else {
    alert("got empty message");
  }
});

var subscription = client.subscribe("/queue/test", callback, headers);
```
###取消订阅
```
var subscription = client.subscribe(...);
 
subscription.unsubscribe();
```
###JSON 支持
STOMP 帧的 body 必须是 string 类型，若希望接收/发送 json 对象，可通过 JSON.stringify() and JSON.parse() 实现；
例：
```
var quote = {symbol: 'APPL', value: 195.46};
client.send("/topic/stocks", {}, JSON.stringify(quote));
 
client.subcribe("/topic/stocks", function(message) {
var quote = JSON.parse(message.body);
alert(quote.symbol + " is at " + quote.value);
});
```
###事务支持
STOMP 客户端支持在发送消息时用事务进行处理：
举例说明：
```
// start the transaction
// 该方法返回一个包含了事务 id、commit()、abort() 的JavaScript 对象
var tx = client.begin();
// send the message in a transaction
// 最关键的在于要在 headers 对象中加入事务 id，若没有添加，则会直接发送消息，不会以事务进行处理
client.send("/queue/test", {transaction: tx.id}, "message in a transaction");
// commit the transaction to effectively send the message
tx.commit();
// tx.abort();
```
###Debug 信息
STOMP 客户端默认将传输过程中的所有 debug 信息以 console.log() 形式输出到客户端浏览器中，也可通过以下方式输出到 DOM 中：
```
client.debug = function(str) {
    // str 参数即为 debug 信息
// append the debug log to a #debug div somewhere in the page using JQuery:
$("#debug").append(str + "\n");
};
```
###认证
这一部分内容看的不是很理解，因此直接将原文放在这里了，待补充。
By default, STOMP messages will be automatically acknowledged by the server before the message is delivered to the client.

The client can chose instead to handle message acknowledgement by subscribing to a destination and specify a ack header set to client or client-individual.

In that case, the client must use the message.ack() method to inform the server that it has acknowledge the message.
```
var subscription = client.subscribe("/queue/test",
  function(message) {
    // do something with the message
    ...
    // and acknowledge it
    message.ack();
  },
  {ack: 'client'}
);
```
The ack() method accepts a headers argument for additional headers to acknowledge the message. For example, it is possible to acknowledge a message as part of a transaction and ask for a receipt when the ACK STOMP frame has effectively be processed by the broker:
var tx = client.begin();
message.ack({ transaction: tx.id, receipt: ‘my-receipt’ });
tx.commit();
The nack() method can also be used to inform STOMP 1.1 brokers that the client did not consume the message. It takes the same arguments than the ack() method.

 

1、JavaScript 依赖
 ```
    STOMP 依赖 sockjs.js 和 stomp.min.js。stomp.min.js的下载链接：http://www.bootcdn.cn/stomp.js/
 
    <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
    <script type="text/javascript" src="/js/stomp.min.js"></script>
 ```
2、JavaScript 客户端实现
 
[点击并拖拽以移动]
 ```
/*STOMP*/
var url = 'http://localhost:8080/stomp';
var sock = new SockJS(url);
var stomp = Stomp.over(sock);
 
var strJson = JSON.stringify({'message': 'Marco!'});
 
//默认的和STOMP端点连接
/*stomp.connect("guest", "guest", function (franme) {
});*/
 
var headers={
    username:'admin',
    password:'admin'
};
 
stomp.connect(headers, function (frame) {
 
    //发送消息
    //第二个参数是一个头信息的Map，它会包含在STOMP的帧中
    //事务支持
    var tx = stomp.begin();
    stomp.send("/app/marco", {transaction: tx.id}, strJson);
    tx.commit();
 
 
    //订阅服务端消息 subscribe(destination url, callback[, headers])
    stomp.subscribe("/topic/marco", function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("订阅的服务端消息：" + obj.message);
    }, {});
 
 
    stomp.subscribe("/app/getShout", function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("订阅的服务端直接返回的消息：" + obj.message);
    }, {});
 
 
    /*以下是针对特定用户的订阅*/
    var adminJSON = JSON.stringify({'message': 'ADMIN'});
    /*第一种*/
    stomp.send("/app/singleShout", {}, adminJSON);
    stomp.subscribe("/user/queue/shouts",function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("admin用户特定的消息1：" + obj.message);
    });
    /*第二种*/
    stomp.send("/app/shout", {}, adminJSON);
    stomp.subscribe("/user/queue/notifications",function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("admin用户特定的消息2：" + obj.message);
    });
 
    /*订阅异常消息*/
    stomp.subscribe("/user/queue/errors", function (message) {
        console.log(message.body);
    });
 
    //若使用STOMP 1.1 版本，默认开启了心跳检测机制（默认值都是10000ms）
    stomp.heartbeat.outgoing = 20000;
 
    stomp.heartbeat.incoming = 0; //客户端不从服务端接收心跳包
});
```
##STOMP 服务端整理
一、STOMP 简介
    直接使用WebSocket（或SockJS）就很类似于使用TCP套接字来编写Web应用。因为没有高层级的线路协议（wire protocol），因此就需要我们定义应用之间所发送消息的语义，还需要确保连接的两端都能遵循这些语义。

    就像HTTP在TCP套接字之上添加了请求-响应模型层一样，STOMP在WebSocket之上提供了一个基于帧的线路格式（frame-based wire format）层，用来定义消息的语义。

    与HTTP请求和响应类似，STOMP帧由命令、一个或多个头信息以及负载所组成。例如，如下就是发送数据的一个STOMP帧：
```
>>> SEND
transaction:tx-0
destination:/app/marco
content-length:20

{"message":"Marco!"}
```
 

    在这个例子中，STOMP命令是send，表明会发送一些内容。紧接着是三个头信息：一个表示消息的的事务机制，一个用来表示消息要发送到哪里的目的地，另外一个则包含了负载的大小。然后，紧接着是一个空行，STOMP帧的最后是负载内容。

 

1、启用STOMP功能
    STOMP 的消息根据前缀的不同分为三种。如下，以 /app 开头的消息都会被路由到带有@MessageMapping 或 @SubscribeMapping 注解的方法中；以/topic 或 /queue 开头的消息都会发送到STOMP代理中，根据你所选择的STOMP代理不同，目的地的可选前缀也会有所限制；以/user开头的消息会将消息重路由到某个用户独有的目的地上。



 View Code

2、处理来自客户端的STOMP消息
    服务端处理客户端发来的STOMP消息，主要用的是 @MessageMapping 注解。如下：

 
```
  @MessageMapping("/marco")
  @SendTo("/topic/marco")
  public Shout stompHandle(Shout shout){
      LOGGER.info("接收到消息：" + shout.getMessage());
      Shout s = new Shout();
      s.setMessage("Polo!");
      return s;
  }
 ```

    2.1、@MessageMapping 指定目的地是“/app/marco”（“/app”前缀是隐含的，因为我们将其配置为应用的目的地前缀）。

    2.2、方法接收一个Shout参数，因为Spring的某一个消息转换器会将STOMP消息的负载转换为Shout对象。Spring 4.0提供了几个消息转换器，作为其消息API的一部分：



    2.3、尤其注意，这个处理器方法有一个返回值，这个返回值并不是返回给客户端的，而是转发给消息代理的，如果客户端想要这个返回值的话，只能从消息代理订阅。@SendTo 注解重写了消息代理的目的地，如果不指定@SendTo，帧所发往的目的地会与触发处理器方法的目的地相同，只不过会添加上“/topic”前缀。

    2.4、如果客户端就是想要服务端直接返回消息呢？听起来不就是HTTP做的事情！即使这样，STOMP 仍然为这种一次性的响应提供了支持，用的是@SubscribeMapping注解，与HTTP不同的是，这种请求-响应模式是异步的...

 
```
   @SubscribeMapping("/getShout")
   public Shout getShout(){
       Shout shout = new Shout();
       shout.setMessage("Hello STOMP");
       return shout;
   }
 ```

3、发送消息到客户端
3.1 在处理消息之后发送消息

    正如前面看到的那样，使用 @MessageMapping 或者 @SubscribeMapping 注解可以处理客户端发送过来的消息，并选择方法是否有返回值。

    如果 @MessageMapping 注解的控制器方法有返回值的话，返回值会被发送到消息代理，只不过会添加上"/topic"前缀。可以使用@SendTo 重写消息目的地；

    如果 @SubscribeMapping 注解的控制器方法有返回值的话，返回值会直接发送到客户端，不经过代理。如果加上@SendTo 注解的话，则要经过消息代理。

3.2 在应用的任意地方发送消息

    spring-websocket 定义了一个 SimpMessageSendingOperations 接口（或者使用SimpMessagingTemplate ），可以实现自由的向任意目的地发送消息，并且订阅此目的地的所有用户都能收到消息。

 

  @Autowired
  private SimpMessageSendingOperations simpMessageSendingOperations;


  /**
  * 广播消息，不指定用户，所有订阅此的用户都能收到消息
  * @param shout
  */
  @MessageMapping("/broadcastShout")
  public void broadcast(Shout shout) {
      simpMessageSendingOperations.convertAndSend("/topic/shouts", shout);
  }
 

3.3 为指定用户发送消息

    3.2介绍了如何广播消息，订阅目的地的所有用户都能收到消息。如果消息只想发送给特定的用户呢？spring-websocket 介绍了两种方式来实现这种功能，一种是 基于@SendToUser注解和Principal参数，一种是SimpMessageSendingOperations 接口的convertAndSendToUser方法。

基于@SendToUser注解和Principal参数
    @SendToUser 表示要将消息发送给指定的用户，会自动在消息目的地前补上"/user"前缀。如下，最后消息会被发布在  /user/queue/notifications-username。但是问题来了，这个username是怎么来的呢？就是通过 principal 参数来获得的。那么，principal 参数又是怎么来的呢？需要在spring-websocket 的配置类中重写 configureClientInboundChannel 方法，添加上用户的认证。

 spring-websocket 用户认证

 

  @MessageMapping("/shout")
  @SendToUser("/queue/notifications")
  public Shout userStomp(Principal principal, Shout shout) {
        String name = principal.getName();
        String message = shout.getMessage();
        LOGGER.info("认证的名字是：{}，收到的消息是：{}", name, message);
        return shout;
  }
 

convertAndSendToUser方法
    除了convertAndSend()以外，SimpMessageSendingOperations 还提供了convertAndSendToUser()方法。按照名字就可以判断出来，convertAndSendToUser()方法能够让我们给特定用户发送消息。

 

    @MessageMapping("/singleShout")
    public void singleUser(Shout shout, StompHeaderAccessor stompHeaderAccessor) {
        String message = shout.getMessage();
        LOGGER.info("接收到消息：" + message);
        Principal user = stompHeaderAccessor.getUser();
        simpMessageSendingOperations.convertAndSendToUser(user.getName(), "/queue/shouts", shout);
    }
 

    如上，这里虽然我还是用了认证的信息得到用户名。但是，其实大可不必这样，因为 convertAndSendToUser 方法可以指定要发送给哪个用户。也就是说，完全可以把用户名的当作一个参数传递给控制器方法，从而绕过身份认证！convertAndSendToUser 方法最终会把消息发送到 /user/sername/queue/shouts 目的地上。

4、处理消息异常
    在处理消息的时候，有可能会出错并抛出异常。因为STOMP消息异步的特点，发送者可能永远也不会知道出现了错误。@MessageExceptionHandler标注的方法能够处理消息方法中所抛出的异常。我们可以把错误发送给用户特定的目的地上，然后用户从该目的地上订阅消息，从而用户就能知道自己出现了什么错误啦...

 

     @MessageExceptionHandler(Exception.class)
     @SendToUser("/queue/errors")
     public Exception handleExceptions(Exception t){
         t.printStackTrace();
         return t;
     }
 


@SendTo 与 @SendToUser 是Spring的STOMP协议中注解的标签。
@SendTo
会将接收到的消息发送到指定的路由目的地，所有订阅该消息的用户都能收到，属于广播。

@SendToUser
消息目的地有UserDestinationMessageHandler来处理，会将消息路由到发送者对应的目的地。默认该注解前缀为/user。如：用户订阅/user/hi ，在@SendToUser('/hi')查找目的地时，会将目的地的转化为/user/{name}/hi, 这个name就是principal的name值，该操作是认为用户登录并且授权认证，使用principal的name作为目的地标识。发给消息来源的那个用户。（就是谁请求给谁，不会发给所有用户，区分就是依照principal-name来区分的)。

此外该注解还有个broadcast属性，表明是否广播。就是当有同一个用户登录多个session时，是否都能收到。取值true/false.


 

