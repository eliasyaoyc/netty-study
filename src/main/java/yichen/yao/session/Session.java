package yichen.yao.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午2:30
 */
@Data
@NoArgsConstructor
public class Session {

    //用户唯一标识
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
