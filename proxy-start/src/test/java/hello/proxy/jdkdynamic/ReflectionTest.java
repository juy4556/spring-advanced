package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        //공통로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result = {}", result1);

        //공통로직2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result = {}", result2);

    }

    @Test
    void reflection1() throws Exception {
        //class 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메서드 정보, 이렇게 메서드명을 파라미터로 넘길 수 있는 방법이 있음
        // 기존의 메소드를 직접 호출하는 부분이 'Method'로 추상화된 것
        Method methodCallA = classHello.getMethod("callA");
        //target에 있는 메서드 callA를 호출하는 것
        Object result1 = methodCallA.invoke(target);
        log.info("result={}", result1);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        //target에 있는 메서드 callB를 호출하는 것
        Object result2 = methodCallB.invoke(target);
        log.info("result={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        //class 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // callA 메서드 정보, 이렇게 메서드명을 파라미터로 넘길 수 있는 방법이 있음
        // 기존의 메소드를 직접 호출하는 부분이 'Method'로 추상화된 것
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
