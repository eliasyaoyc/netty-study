package yichen.yao.netty.seventhThriftExample;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import yichen.yao.netty.thrift.generated.Person;
import yichen.yao.netty.thrift.generated.PersonService;


/**
 * @Author: lancer.yao
 * @time: 2020/1/6:下午5:36
 */
public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {
            tTransport.open();
            Person person = client.getPersonByUsername("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());
            Person person1 = new Person();
            person1.setUsername("李四");
            person1.setAge(30);
            person1.setMarried(true);
            client.savePerson(person1);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            tTransport.close();
        }
    }
}
