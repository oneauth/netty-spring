package me.o_nix.netty_spring;

import me.o_nix.netty_spring.initializers.NettyChannelInitializer;

import org.springframework.beans.factory.InitializingBean;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Author: Kirill Vergun
 * Date: 5/17/13
 */
public class NettyClient implements InitializingBean {
	protected NettyChannelInitializer channelInitializer;
	protected EventLoopGroup bossGroup = new NioEventLoopGroup();

	private Bootstrap bootstrap = new Bootstrap();
	private ChannelFuture connectionFuture;

	public NettyChannelInitializer getChannelInitializer() {
		return channelInitializer;
	}

	public void setChannelInitializer(NettyChannelInitializer channelInitializer) {
		this.channelInitializer = channelInitializer;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		bootstrap
			.group(bossGroup)
			.channel(NioSocketChannel.class)
			.handler(channelInitializer);
	}

	public ChannelFuture connect(String host, int port) {
		connectionFuture = bootstrap.connect(host, port);

		return connectionFuture;
	}

	public void disconnect() {
		if (connectionFuture != null) {
			connectionFuture
				.syncUninterruptibly()
				.channel()
				.disconnect();

			connectionFuture = null;
		}
	}
}
