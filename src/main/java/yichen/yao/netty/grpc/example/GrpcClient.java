package yichen.yao.netty.grpc.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:上午9:56
 */
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8899).
                usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);

        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());

        System.out.println("-------------");
        Iterator<StudentResponse> studentsByAge = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while (studentsByAge.hasNext()){
            StudentResponse next = studentsByAge.next();
            System.out.println(next.getName() + "," + next.getAge() + "," + next.getCity());
        }
        System.out.println("-------------");

        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>(){
            @Override
            public void onNext(StudentResponseList studentResponseList) {
                studentResponseList.getStudentResponseList().forEach(value->{
                    System.out.println(value.getAge());
                    System.out.println(value.getName());
                    System.out.println(value.getCity());
                    System.out.println("****");
                });
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed!");
            }
        };
        StreamObserver<StudentRequest> streamObserver = stub.getStudentsWrapperByAges(studentResponseListStreamObserver);
        streamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        streamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        streamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        streamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
        streamObserver.onCompleted();

        System.out.println("-------------");

        StreamObserver<StreamRequest> streamRequestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse streamResponse) {
                System.out.println(streamResponse.getResponseInfo());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("omCompleted!");
            }
        });
        for(int i = 0; i < 10; i++){
            streamRequestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
        }
    }
}
