# Netty configuration using Srping

Now you don't need to configure your clients and servers using the code.
Just make your custom handler and configire desired bean.

```xml
<import resource="classpath:netty-spring.xml" />

<bean id="socksClientHandler" class="me.o_nix.socks_https.tests.utils.SocksClientHandler" />

<bean id="socksClient" parent="nettyClient">
    <property name="channelInitializer">
        <bean parent="clientInitializer">
            <property name="handlers">
                <set>
                    <ref bean="loggingHandler" />
                    <bean class="io.netty.handler.codec.socks.SocksInitResponseDecoder" />
                    <bean class="io.netty.handler.codec.socks.SocksMessageEncoder" />
                    <ref bean="socksClientHandler" />
                </set>
            </property>
        </bean>
    </property>
</bean>
```

Then just use it:

```java
@Autowired
private NettyClient socksClient;
```
```java

socksClient.connect("localhost", 1080);
```

## Maven

```xml
<repositories>
    <repository>
        <id>bintray</id>
        <url>http://jcenter.bintray.com</url>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.o_nix</groupId>
    	<artifactId>netty-spring</artifactId>
    	<version>1.0</version>
    </dependency>
</dependencies>
```
