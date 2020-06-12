package com.example.demo;

import com.example.demo.cglib.service.impl.Service1;
import com.example.demo.cglib.service.impl.Service2;
import com.example.demo.cglib.service.impl.Service3;
import com.example.demo.cglib.service.impl.Service4;
import com.example.demo.lambda.util.MatheUtil;
import com.example.demo.lambda.constants.StatisticsType;
import com.example.demo.lambda.service.Calculation;
import com.example.demo.lambda.service.Notice;
import com.example.demo.proxy.service.IService;
import com.example.demo.proxy.service.IUserService;
import com.example.demo.proxy.service.impl.CostTimeInvocationHandler;
import com.example.demo.proxy.service.impl.ServiceA;
import com.example.demo.proxy.service.impl.ServiceB;
import com.example.demo.proxy.service.impl.UserServiceImpl;
import com.example.demo.value.config.StaffConfig;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.*;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    StaffConfig staffConfig;

    @Value("${jasypt.encryptor.password}")
    String yan;
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void contextLoads() {

        //加密方法

        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法
        System.out.println(yan);
        System.out.println(stringEncryptor.decrypt("McaserZcmcoz70GBojBI9032gP+jvB9cby2TmZ5JHRs2BS00jrZy73f1SCmzGSkU"));

    }


    /**
     * 验证@Value和spEL配置加载对应
     */
    @Test
   void verificationValue() {
        System.out.println("用户名称集合List："+staffConfig.getStaffNames());
        System.out.println("用户名称集合List下的第一个参数："+staffConfig.getFirstStaffName());
        System.out.println("输出map类型的所有年龄："+staffConfig.getStaffAge());
        System.out.println("输出map类型的key为one的年龄："+staffConfig.getStaffAgeOne());
        System.out.println("输出map类型的年龄如果没有展示的默认年龄："+staffConfig.getAgeWithDefaultValue());
        System.out.println("输出javaHome："+staffConfig.getJavaHome());
        System.out.println("输出当前工作目录："+staffConfig.getUserDir());
        System.out.println("jasypt加密后获取的password："+staffConfig.getPassword());

    }

    /**
     * 实验lambda 接口
     */
    @Test
    void verificationLambda(){

      Calculation calculation=(BigDecimal a, BigDecimal b)-> a.add(b);

      System.out.println(calculation.call(BigDecimal.valueOf(1),BigDecimal.valueOf(2)));
      Notice natice=a->System.out.println(a);
        natice.call("小姐姐你来呀");
    };

    @Test
    void verificationLambdaList(){

        List<BigDecimal> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new BigDecimal(i));
        }
        System.out.println(MatheUtil.call(StatisticsType.AVERAGE, list).toPlainString());
        System.out.println(MatheUtil.call(StatisticsType.MIN, list).toPlainString());
        System.out.println(MatheUtil.call(StatisticsType.MAX, list).toPlainString());
        System.out.println(MatheUtil.call(StatisticsType.SUM, list).toPlainString());

        System.out.println(MatheUtil.call(StatisticsType.ADD,BigDecimal.TEN,BigDecimal.ONE).toPlainString());
        System.out.println(MatheUtil.call(StatisticsType.SUB,BigDecimal.TEN,BigDecimal.ONE).toPlainString());
        System.out.println(MatheUtil.call(StatisticsType.MUL,BigDecimal.TEN,BigDecimal.ONE).toPlainString());
        System.out.println(MatheUtil.call(StatisticsType.DIV,BigDecimal.TEN,BigDecimal.ONE).toPlainString());



    };

/**************************************************| 代理 |*********************************************************/
    /**
     * 代理方法1
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void m1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 1. 获取接口对应的代理类
        Class<IService> proxyClass = (Class<IService>) Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        // 2. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        // 3. 创建代理实例
        IService proxyService = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        // 4. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }



    @Test
    public void m2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 1. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        // 2. 创建代理实例
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, invocationHandler);
        // 3. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }



    @Test
    public void costTimeProxy() {
        IService serviceA = CostTimeInvocationHandler.createProxy(new ServiceA(), IService.class);
        IService serviceB = CostTimeInvocationHandler.createProxy(new ServiceB(), IService.class);
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();

        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

    /**
     * 不需要再去新建一个代理类了，只需要使用CostTimeInvocationHandler.createProxy创建一个新的代理对象就可以了，方便了很多。
     */
    @Test
    public void userService() {
        IUserService userService = CostTimeInvocationHandler.createProxy(new UserServiceImpl(), IUserService.class);
        userService.insert("路人甲Java");
    }

/************************************|jdk Proxy 代理|*****************************************************************/

    /************************************|cglib 代理|*****************************************************************/

    /**
     * 拦截所有方法（MethodInterceptor）
     *
     * 列出了给指定的类创建代理的具体步骤，整个过程中主要用到了Enhancer类和MethodInterceptor接口。
     * enhancer.setSuperclass用来设置代理类的父类，即需要给哪个类创建代理类，此处是Service1
     * enhancer.setCallback传递的是MethodInterceptor接口类型的参数，MethodInterceptor接口有个intercept方法，这个方法会拦截代理对象所有的方法调用。
     * 还有一个重点是Object result = methodProxy.invokeSuper(o, objects);可以调用被代理类，
     * 也就是Service1类中的具体的方法，从方法名称的意思可以看出是调用父类，实际对某个类创建代理，cglib底层通过修改字节码的方式为Service1类创建了一个子类。
     */

@Test
public void cglibTest1() {
    //使用Enhancer来给某个类创建代理类，步骤
    //1.创建Enhancer对象
    Enhancer enhancer = new Enhancer();
    //2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
    enhancer.setSuperclass(Service1.class);
        /*3.设置回调，需实现org.springframework.cglib.proxy.Callback接口，
        此处我们使用的是org.springframework.cglib.proxy.MethodInterceptor，也是一个接口，实现了Callback接口，
        当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理*/
    enhancer.setCallback(new MethodInterceptor() {
        /**
         *
         * 代理对象方法拦截器
         * @param o 代理对象
         * @param method 被代理的类的方法，即Service1中的方法
         * @param objects 调用方法传递的参数
         * @param methodProxy 方法代理对象
         * @return
         * @throws Throwable
         */
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("调用方法:" + method);
            //可以调用MethodProxy的invokeSuper调用被代理类的方法
            Object result = methodProxy.invokeSuper(o, objects);
            return result;
        }
    });
    //4.获取代理对象,调用enhancer.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
    Service1 proxy = (Service1) enhancer.create();
    //5.调用代理对象的方法
    proxy.m1();
    proxy.m2();
}

    /**
     * 拦截所有方法（MethodInterceptor）
     * 可以看出m1和m2方法都被拦截器处理了，而m2方法是在Service1的m1方法中调用的，也被拦截处理了。
     * spring中的@configuration注解就是采用这种方式实现的
     */
    @Test
    public void cglibTest2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法:" + method);
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        Service2 proxy = (Service2) enhancer.create();
        proxy.m1(); //@1
    }

    /**
     *  拦截所有方法并返回固定值（FixedValue）
     *  当调用某个类的任何方法的时候，都希望返回一个固定的值，此时可以使用FixedValue接口，如下：
     */

    @Test
    public void cglibTest3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "返回测试语句";
            }
        });

        System.out.println("源："+new Service3().m1());
        System.out.println("源："+new Service3().m2());
        Service3 proxy = (Service3) enhancer.create();
        System.out.println("代理："+proxy.m1());//@1
        System.out.println("代理："+proxy.m2()); //@2
        System.out.println("代理："+proxy.toString());//@3
    }


    /**
     * 不同的方法使用不同的拦截器（CallbackFilter）
     * 需求，给这个类创建一个代理需要实现下面的功能：
     *
     * 以insert开头的方法需要统计方法耗时
     * 以get开头的的方法直接返回固定字符串`欢迎和【路人甲java】一起学spring！`
     * 由于需求中要对不同的方法做不同的处理，所以需要有2个Callback对象，当调用代理对象的方法的时候，
     * 具体会走哪个Callback呢，此时会通过CallbackFilter中的accept来判断，这个方法返回callbacks数组的索引
     */
    @Test
    public void cglibTest4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service4.class);
        //创建2个Callback
        Callback[] callbacks = {
                //这个用来拦截所有insert开头的方法
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        long starTime = System.nanoTime();
                        Object result = methodProxy.invokeSuper(o, objects);
                        long endTime = System.nanoTime();
                        System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
                        return result;
                    }
                },
                //下面这个用来拦截所有get开头的方法，返回固定值的
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "返回测试语句";
                    }
                }
        };
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                return 0;
            }
        });
        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        /**
         * 设置过滤器CallbackFilter
         * CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方法
         * 返回的是callbacks数组的下标
         */
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                //获取当前调用的方法的名称
                String methodName = method.getName();
                /**
                 * 方法名称以insert开头，
                 * 返回callbacks中的第1个Callback对象来处理当前方法，
                 * 否则使用第二个Callback处理被调用的方法
                 */
                return methodName.startsWith("insert") ? 0 : 1;
            }
        });
        Service4 proxy = (Service4) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());

    }


    /**
     * cglib中有个CallbackHelper类，可以对cglibTest4的代码进行优化，CallbackHelper类相当于对一些代码进行了封装，方便实现案例cglibTest5()的需求，实现如下：
     */
    @Test
    public void cglibTest5() {
        Enhancer enhancer = new Enhancer();
        //创建2个Callback
        Callback costTimeCallback = (MethodInterceptor) (Object o, Method method, Object[] objects, MethodProxy methodProxy) -> {
            long starTime = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
            return result;
        };
        //下面这个用来拦截所有get开头的方法，返回固定值的
        Callback fixdValueCallback = (FixedValue) () -> "路人甲Java";
        CallbackHelper callbackHelper = new CallbackHelper(Service4.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixdValueCallback;
            }
        };
        enhancer.setSuperclass(Service4.class);
        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        /**
         * 设置CallbackFilter,用来判断某个方法具体走哪个Callback
         */
        enhancer.setCallbackFilter(callbackHelper);
        Service4 proxy = (Service4) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());

    }

    /**
     * 直接放行，不做任何操作（NoOp.INSTANCE）
     * Callback接口下面有个子接口org.springframework.cglib.proxy.NoOp，将这个作为Callback的时候，被调用的方法会直接放行，像没有任何代理一样
     */
    @Test
    public void test6() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
    }


}
