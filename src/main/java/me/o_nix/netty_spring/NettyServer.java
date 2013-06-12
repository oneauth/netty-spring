package me.o_nix.netty_spring;

import me.o_nix.netty_spring.initializers.NettyChannelInitializer;

import org.springframework.beans.factory.InitializingBean;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer implements InitializingBean {
	private NettyChannelInitializer channelInitializer;
	private EventLoopGroup bossGroup = new NioEventLoopGroup();
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
	private ServerBootstrap bootstrap = new ServerBootstrap();
	private int port;

	private ChannelFuture listenerFuture;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ChannelFuture start() {
		listenerFuture = bootstrap
			.bind("127.0.0.1", port);

		return listenerFuture;
	}

	public NettyChannelInitializer getChannelInitializer() {
		return channelInitializer;
	}

	public void setChannelInitializer(NettyChannelInitializer channelInitializer) {
		this.channelInitializer = channelInitializer;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		bootstrap
			.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(channelInitializer);
	}

	public void stop() {
		if (listenerFuture != null) {
			listenerFuture
				.syncUninterruptibly()
				.channel()
				.close();

			listenerFuture = null;
		}
	}
}
