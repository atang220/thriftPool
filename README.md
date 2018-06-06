## 项目背景：
业务系统需要调用多处其他系统thrift接口，每个thrift服务特性不一样而且跨机房，有些thrift服务需要每隔几十秒ping一次，否则会服务器会主动断开链接，有些thrift服务没有ping接口且不会主动断开，
有些由于网络问题会导致超时，有时候重试会出现seqId问题。为解决这些问题，将所有的thrift链接放进对象池，由对象池管理生成和维护这些链接，
并采用动态代理的方式避免对原有业务代码的入侵。
## 使用方法：
* 1、在自己的项目导入准备的thrift生成的java文件，如com.atang.thrift.thriftPool.example.client内的java文件；
* 2、根据业务编写服务接口和实现，如com.atang.thrift.thriftPool.example.service.BroadCastServiceImpl，注入iface接口；
```java
@Autowired private GmBcService.Iface broadCastClient;
```
* 3、根据业务thrift服务端需求编写PooledObjectFactory，需要继承虚类com.atang.thrift.thriftPool.base.AbstractPooledObjectFactory，实现getTProtocol()方法，
如果需要做ping检测，实现customValidate()方法，参考用例com.atang.thrift.thriftPool.example.TFramTransportPooledObjectFactory
* 4、创建配置xxx-thrift-client.xml文件,例如/thriftPool/src/main/resources/thrift-client.xml，包含3个配置GenericObjectPool、
PooledObjectFactory、TClientProxyFactory。其中PooledObjectFactory主要配置thrift服务器host和port，可支持多个配置如下：
```xml
  <bean id="broadCastPooledObjectFactory" class="com.atang.thrift.thriftPool.example.TFramTransportPooledObjectFactory">
    	<property name="factoryName" value="com.atang.thrift.thriftPool.example.client.GmBcService$Client$Factory"></property>
    	<property name="configList">
    		<list>
    			<bean id="userInfoTsConfig1" class="com.atang.thrift.thriftPool.modal.TSConfig">
    				<property name="host" value="${broadCast.host}"></property>
    				<property name="port" value="${broadCast.port}"></property>
    				<property name="timeout" value="${broadCast.timeout}"></property>
    			</bean>
    		</list>
    	</property>
    </bean>
```
TClientProxyFactory配置如下，用于动态代理com.atang.thrift.thriftPool.example.client.GmBcService$Iface接口，
id="broadCastClient"用于BroadCastServiceImpl注入。
```xml
 <bean id="broadCastClient" class="com.atang.thrift.thriftPool.base.TClientProxyFactory">
        <property name="pooledObjectFactory" ref="broadCastPooledObjectFactory"/>
        <property name="ifaceName" value="com.atang.thrift.thriftPool.example.client.GmBcService$Iface"/>
        <!-- poolConfig 可以使用默认值 -->
        <property name="poolConfig" ref="broadCastPoolConfig"/>
    </bean>
```
GenericObjectPool是apache common pools对象池配置，包含“创建链接的时候检测”、“最小链接数”、“最大链接数”、“链接检测间隔”等常用配置，
这里可以不配置，默认最大连接数8个，最小链接数0个，可以自己根据需求自行设置。
```xml
  <bean id="broadCastPoolConfig" class="org.apache.commons.pool2.impl.GenericObjectPoolConfig">
        <property name="testOnBorrow" value="true"/>
        <!-- 是否在创建链接的时候检测 -->
        <property name="testOnCreate" value="true"/>
        <!-- 最小链接数 -->
        <property name="minIdle" value="2"></property>
       <!--   资源最小空闲时间  1天-->
        <property name="minEvictableIdleTimeMillis" value="86400000"></property>
       <!-- 链接检测间隔  20s -->
        <property name="timeBetweenEvictionRunsMillis" value="20000"></property>
    </bean>
```
## 联系方式
有任何疑问或者建议，请联系atang220@qq.com
