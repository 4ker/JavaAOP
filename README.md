# JavaAOP
## 内容
* 使用JDK的动态代理实现了简单地AOP思想编程
* 使用反射结合JDK动态代理实现了类似于Spring框架的简单“ @xxx(xx="xxxx") ”的数据注入
* 仅此，分享给大家，正在学爪哇的大家


## 相关文章
* [知乎-怎样理解 java 注解和运用注解编程？Accelerator的回答](https://www.zhihu.com/question/47449512/answer/106034220)
* [知乎-Accelerator的日常学习与分享专栏](https://zhuanlan.zhihu.com/Accelerator)

---

使用反射结合 JDK 动态代理实现了类似于 Spring 框架的简单 “@xxx(xx="xxxx") ” 的数据注入。

在软件业，**AOP 为 Aspect Oriented Programming** 的缩写，意为：面向切面编程，
通过

-   **预编译方式**和
-   **运行期动态代理**

实现程序功能的统一维护的一种技术。AOP 是 OOP 的延续，是软件开发中的一个热点，
也是 Spring 框架中的一个重要内容，是函数式编程的一种衍生范型。
利用 AOP 可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，
提高程序的可重用性，同时提高了开发的效率。

Aspect，没错，的确是 “方面” 的意思。不过，华语传统
语义中的 “方面”，大多数情况下指的是一件事情的不同
维度、或者说不同角度上的特性，比如我们常说：“这件
事情要从几个方面来看待”，往往意思是：需要从不同的
角度来看待同一个事物。

**这里的 “方面”，指的是事物的外在特性在不同观察角度下的体现。**
而在 AOP 中， Aspect 的含义，可能更多的理解为 “切面” 比较合适。
所以笔者更倾向于 “面向切面编程” 的译法。

---

```
== 给 'com.tangzhixiong.javaaop.DogImp@deb6432' 注入属性 ==
属性注入：'name' = '坏🐶' (通过 setter）
属性无注入：'property'
== 给 'com.tangzhixiong.javaaop.DogImp@deb6432' 注入方法 ==
方法注入：'setProperty' = '水陆两栖战士'
方法注入：'setProperty' = '水陆两栖战士'
方法无注入：'getProperty'
方法无注入：'getProperty'
方法无注入：'getName'
方法无注入：'setName'
方法无注入：'setName'
		成功拦截 'getProperty' 方法, 启动 

		成功拦截 'getProperty' 方法, 结束 
```

## 小结

-   一个类的 annotation 可以注解到属性（field）也可以注解到方法（method）
-   注解为何生效？因为你多加了一层 indirection，解析了注解，包装了一下，所以注解有效
-   从类上我们可以拿到 fields 和 methods，以及上面的注解
-   获取注解的时候你要告诉 field 你要啥样的注解（注解类名称），它会给你返回一个注解类的对象
-   这个对象的属性和值就是注解的 KEY=VALUE 键值对
-   注入就是把这个注解信息（一个注解类的成员，包含这种注解的一切可能信息（有默认值）拿到，然后自己调用相应的函数来让使生效
