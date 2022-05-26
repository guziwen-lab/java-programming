import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/26 3:40 PM
 */
public class IoCTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Object companyBean = applicationContext.getBean("companyBean");
        Object companyBeanFactory = applicationContext.getBean("&companyBean");
        System.out.println(companyBean);
        System.out.println(companyBeanFactory);
    }

}
