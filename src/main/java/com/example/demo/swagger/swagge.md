#swagger 配置 需要 

直接拷貝走config 内容也可以
首先通过maven坐标引入swagger相关的类库。
```
  <!-- Swagger API文档 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>swagger-bootstrap-ui</artifactId>
            <version>1.9.3</version>
        </dependency>

```
然后通过java Config对Swagger2进行配置。
```
Configuration
@EnableSwagger2 
public class Swagger2 {

    private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("springboot利用swagger构建api文档")
				.description("简单优雅的restfun风格")
				.termsOfServiceUrl("http://www.zimug.com")
				.version("1.0")
				.build();
    }

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo())
                        .select()
                        //扫描basePackage包下面的“/rest/”路径下的内容作为接口文档构建的目标
                        .apis(RequestHandlerSelectors.basePackage("com.zimug.bootlaunch"))
                        .paths(PathSelectors.regex("/rest/.*"))
                        .build();
	}
	
	
}
```
@EnableSwagger2 注解表示开启SwaggerAPI文档相关的功能
在apiInfo方法中配置接口文档的title(标题)、描述、termsOfServiceUrl（服务协议）、版本等相关信息
在createRestApi方法中，basePackage表示扫描哪个package下面的Controller类作为API接口文档内容范围
在createRestApi方法中，paths表示哪一个请求路径下控制器映射方法，作为API接口文档内容范围

##注解描述
```
@Api：用在Controller控制器类上
     属性tags="说明该类的功能及作用"

@ApiOperation：用在Controller控制器类的请求的方法上
    value="说明方法的用途、作用"
    notes="方法的备注说明"

@ApiImplicitParams：用在请求的方法上，表示一组参数说明
    @ApiImplicitParam：请求方法中参数的说明
        name：参数名
        value：参数的汉字说明、解释、用途
        required：参数是否必须传，布尔类型
        paramType：参数的类型，即参数存储位置或提交方式
            · header --> Http的Header携带的参数的获取：@RequestHeader
            · query --> 请求参数的获取：@RequestParam   （如上面的例子）
            · path（用于restful接口）--> 请求参数的获取：@PathVariable
            · body（不常用）
            · form（不常用）    
        dataType：参数类型，默认String，其它值dataType="Integer"       
        defaultValue：参数的默认值

@ApiResponses：用在控制器的请求的方法上，对方法的响应结果进行描述
    @ApiResponse：用于表达一个响应信息
        code：数字，例如400
        message：信息，例如"请求参数没填好"
        response：响应结果封装类，如上例子中的AjaxResponse.class

@ApiModel：value=“通常用在描述@RequestBody和@ResponseBody注解修饰的接收参数或响应参数实体类”
    @ApiModelProperty：value="实体类属性的描述"
```
##生产环境下如何禁用swagger2
```
我们的文档通常是在团队内部观看及使用的，不希望发布到生产环境让用户看到。

禁用方法1：使用注解@Profile({"dev","test"}) 表示在开发或测试环境开启，而在生产关闭。（推荐使用，如果你不了解Profile是什么，本专栏后面的章节将有介绍，您也可以先自己了解一下）
禁用方法2：使用注解@ConditionalOnProperty(name = "swagger.enable", havingValue = "true") 然后在测试配置或者开发配置中 添加 swagger.enable = true 即可开启，生产环境不填则默认关闭Swagger.
更多使用细节可以参考：
https://www.ibm.com/developerworks/cn/java/j-using-swagger-in-a-spring-boot-project/index.html
```