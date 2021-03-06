package com.java.learn.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.Date;
import java.util.List;

public class DiscardServerTest {

    static class UnixTime {
        private final long value;

        public UnixTime() {
            this(System.currentTimeMillis() / 1000L + 2208988800L);
        }

        UnixTime(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        @Override
        public String toString() {
            return new Date((getValue() - 2208988800L) * 1000L).toString();
        }
    }

    //管道解码器2
    static class TimeDecoder extends ReplayingDecoder<Void> {
        @Override
        protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
            list.add(new UnixTime(byteBuf.readUnsignedInt()));
        }
    }

    static class TimeEncoder extends MessageToByteEncoder<UnixTime> {

        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext, UnixTime unixTime, ByteBuf byteBuf) throws Exception {
            byteBuf.writeInt(((int) unixTime.getValue()));
        }
    }

    static class TimeClient {
        public static void main(String[] args) throws Exception {
            new TimeClient(8080).run();
        }

        private int port;

        public TimeClient(int port) {
            this.port = port;
        }

        public void run() throws Exception {
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("localhost", port).sync();
            future.channel().closeFuture().sync();
        }

        //管道解码器1
//        static class TimeDecoder extends ByteToMessageDecoder{
//
//            @Override
//            protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//                if (byteBuf.readableBytes() < 4) {
//                    return;
//                }
//                list.add(byteBuf.readBytes(4));
//            }
//        }


        static class TimeClientHandler extends ChannelInboundHandlerAdapter {

//            private ByteBuf byteBuf;
//
//            @Override
//            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//                byteBuf = ctx.alloc().buffer(4);
//            }
//
//            @Override
//            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//                byteBuf.release();
//                byteBuf=null;
//            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                ByteBuf byteBuf = (ByteBuf) msg;

//                this.byteBuf.writeBytes(byteBuf);
//                byteBuf.release();
//                if(this.byteBuf.readableBytes()>=4){
//                    long currentTimeMillis = (this.byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
//                    System.out.println(new Date(currentTimeMillis));
//                    ctx.close();
//                }

//                try {
//                    long currentTimeMillis = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
//                    System.out.println(new Date(currentTimeMillis));
//                    ctx.close();
//                } finally {
//                    byteBuf.release();
//                }
                UnixTime unixTime = (UnixTime) msg;
                System.out.println(unixTime);
                ctx.close();
            }
        }
    }

    static class TimeServer {
        public static void main(String[] args) throws Exception {
            new TimeServer(8080).run();
        }

        private int port;

        public TimeServer(int port) {
            this.port = port;
        }

        public void run() throws Exception {
            //主从模型
            try (AutoCloseNioEventLoopGroup bossGroup = new AutoCloseNioEventLoopGroup(1);
                 AutoCloseNioEventLoopGroup workerGroup = new AutoCloseNioEventLoopGroup(8);) {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new TimeEncoder(),new DiscardServerHandler());
                            }
                        })
                        //指定队列大小
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true);
                //绑定端口 接收链接
                ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
                //等待关闭服务器
                channelFuture.channel().closeFuture().sync();
            }
        }

        //管道编码器
//        static class TimeEncoder extends ChannelOutboundHandlerAdapter{
//
//            @Override
//            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//                UnixTime unixTime = (UnixTime) msg;
//                ByteBuf buffer = ctx.alloc().buffer();
//                buffer.writeInt(((int) unixTime.getValue()));
//                ctx.write(buffer, promise);
//            }
//        }
        static class DiscardServerHandler extends ChannelInboundHandlerAdapter {

            //            @Override
//            public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                final ByteBuf buffer = ctx.alloc().buffer(4);
//                buffer.writeInt(((int) (System.currentTimeMillis() / 1000L + 2208988800L)));
//                final ChannelFuture f = ctx.writeAndFlush(buffer);
//                f.addListener(new ChannelFutureListener() {
//                    @Override
//                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                        assert f == channelFuture;
//                        ctx.close();
//                    }
//                });
//            }
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                ChannelFuture channelFuture = ctx.writeAndFlush(new UnixTime());
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            //应答
//            ctx.writeAndFlush(msg);
//            //丢弃
////            ByteBuf byteBuf = (ByteBuf) msg;
////            try {
////                System.out.println(byteBuf.toString(Charset.defaultCharset()));
////            } finally {
////                ReferenceCountUtil.release(msg);
////            }
//        }
//
//        @Override
//        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            cause.printStackTrace();
//            ctx.close();
//        }
        }

    }

    static class AutoCloseNioEventLoopGroup extends NioEventLoopGroup implements AutoCloseable {

        public AutoCloseNioEventLoopGroup(int nThreads) {
            super(nThreads);
        }

        @Override
        public void close() throws Exception {
            System.out.println("自动关闭");
            super.shutdownGracefully();
        }
    }

}
