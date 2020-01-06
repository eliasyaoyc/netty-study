package yichen.yao.netty.seventhThriftExample;

import org.apache.thrift.TException;
import yichen.yao.netty.thrift.generated.DataException;
import yichen.yao.netty.thrift.generated.Person;
import yichen.yao.netty.thrift.generated.PersonService;

/**
 * @Author: lancer.yao
 * @time: 2020/1/6:下午5:31
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got Client Param: " + username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got Client Param :");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
