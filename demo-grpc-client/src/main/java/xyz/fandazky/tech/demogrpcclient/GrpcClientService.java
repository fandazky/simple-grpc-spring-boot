package xyz.fandazky.tech.demogrpcclient;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import xyz.fandazky.tech.demo.grpc.lib.GreetingGrpc;
import xyz.fandazky.tech.demo.grpc.lib.HelloReply;
import xyz.fandazky.tech.demo.grpc.lib.HelloRequest;

@Service
public class GrpcClientService {

    @GrpcClient("local-grpc-server")
    private GreetingGrpc.GreetingBlockingStub simpleStub;

    public String sendMessage(final String name) {
        try {
            final HelloReply response = this.simpleStub.sayHello(HelloRequest.newBuilder()
                    .setName(name)
                    .setGender(HelloRequest.Gender.MALE)
                    .addOrderIds("001")
                    .addOrderIds("002")
                    .build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}