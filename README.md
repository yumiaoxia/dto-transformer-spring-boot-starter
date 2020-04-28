dto-transformer-spring-boot-starter
_____
 **描述**： 该项目包含三个模块，前两个是针对Spring Boot的web项目简化开发的解决方案。
 第三个模块是测试模块
 
 ## common-web
 Spring Boot web项目的一些常用组件封装，可以被目标项目打包引入，也可以直接当作模块依赖引入使用，主要功能特性有以下几种：
  - 集成了swagger2。目标项目引入该模块后，可以直接通过`@EnableSwagger2`开启,项目启动后可以直接通过浏览器打开swagger API文档。
    这里框架只提供最基本的配置，可以自定义拓展其swagger配置
  - 默认提供了国际化支持，默认国际化文路径`resources/i18n/`。当然可以通过配置文件自定义其路径
  - 统一异常处理，如果目标项目发生的业务异常，可以抛出ServiceException对象，并结合国际化组件向前端返回错误提示
  - 统一接口规范。框架提供了封装接口请求参数和响应参数的系列组件，前后台交互传递规范的Json格式数据
  - JDK8的java.time包下的时间组件，如LocalDate，LocalDateTime,ZoneDateTime,还是原来的Date的时间数据类型，在Json序列化时提供了
   格式化支持
  - 接口日志。框架提供了打印接口日志功能，包括请求头，请求参数，响应参数，还是报错等等。方便开发时接口调试
  
  ## dto-assembler-spring-boot-starter
  
  一个Dto转换神器。我们在做项目开发时，通常使用分层开发（MVC,MVVC... 等等），DTO（Data Transfer Object）数据传输对象，
  一般是作为层与层之间传递数据。而该框架就是可以把普通的pojo对象转换成DTO对象。它的主要功能特性如下：
  - Dto对象可以是接口或者类，两者兼容，或者互相嵌套。例如Dto的class对象是一个接口，然后它的一个成员变量的class对象可以是接口，也可以是类
  - Dto可以通过注解@DtoProperty的Value属性与源对象别名匹配。
  - Dto的源对象可以有多个
  - 可以支持转换大部分数据类型：Page,Collection，Array,Enum,...
  - Controller层可以通过注解@ViewSelector注解返回前端所需要的字段，而不需要重新定义一个Dto
  - Dto支持计算属性，支持接口的默认方法实现
  - 支持hibernate的懒加载

 ## dto-assembler-test-demo
  
 主要针对前第二个模块的的测试模块，同时测试了第一个模块的部分功能。该模块需要根据运行环境配置数据库地址，启动后可以打开浏览器查看swagger api