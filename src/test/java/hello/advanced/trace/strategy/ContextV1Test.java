package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void strategyV1() {
        Strategy strategy1 = new StrategyLogic1();
        Strategy strategy2 = new StrategyLogic2();
        ContextV1 context1 = new ContextV1(strategy1);
        ContextV1 context2 = new ContextV1(strategy2);
        context1.execute();
        context2.execute();
    }

    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new StrategyLogic1() {
            @Override
            public void call() {
                log.info("로직1 실행");
            }
        };
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();
    }

    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new StrategyLogic1() {
            @Override
            public void call() {
                log.info("로직1 실행");
            }
        });
        context1.execute();
    }

    @Test
    void strategyV4() {
        ContextV1 context1 = new ContextV1(() -> log.info("로직1 실행"));
        context1.execute();
        ContextV1 context2 = new ContextV1(() -> log.info("로직2 실행"));
        context2.execute();
    }
}
