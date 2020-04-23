package xyz.fandazky.tech.demo.grpc.server;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import xyz.fandazky.tech.demo.grpc.lib.GreetingGrpc;
import xyz.fandazky.tech.demo.grpc.lib.HelloReply;
import xyz.fandazky.tech.demo.grpc.lib.HelloRequest;

@GrpcService
public class GrpcServerService extends GreetingGrpc.GreetingImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {

        System.out.println("====================== request =======================");
        System.out.println(req);
        System.out.println("======================================================");
        System.out.println("IS ORDER TIME EXIST ? " + req.hasOrderTime());
        System.out.println("IS EMPTY NAME ? " + req.getName().isEmpty());

        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello " + req.getName() + ". You have " + req.getOrderIdsCount() + " order and you are " + req.getGender().toString() )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}