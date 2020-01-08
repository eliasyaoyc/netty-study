package yichen.yao.netty.grpc.example;

import io.grpc.stub.StreamObserver;

import java.util.UUID;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:上午9:47
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase{

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接受到客户端请求：" + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接受到客户端信息: " + request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三1").setAge(20).setCity("北京1").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三2").setAge(20).setCity("北京2").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三3").setAge(20).setCity("北京3").build());
        responseObserver.onCompleted();
    }

    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver){
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest studentRequest) {
                System.out.println("onNext: " + studentRequest.getAge());

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("李四").setAge(20).setCity("北京1").build();
                StudentResponseList list = StudentResponseList.newBuilder().addStudentResponse(studentResponse).addStudentResponse(studentResponse2).build();
                responseObserver.onNext(list);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest streamRequest) {
                System.out.println(streamRequest.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
             }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
