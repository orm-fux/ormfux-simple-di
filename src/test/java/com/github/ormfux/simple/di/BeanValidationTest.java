package com.github.ormfux.simple.di;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import com.github.ormfux.simple.di.InjectionContext;
import com.github.ormfux.simple.di.annotations.Bean;
import com.github.ormfux.simple.di.annotations.BeanConstructor;
import com.github.ormfux.simple.di.annotations.ConfigValue;
import com.github.ormfux.simple.di.exception.BeanDefinitionException;

public class BeanValidationTest extends AbstractDependencyInjectionTest {
    
    @Test
    public void testCircularBeanDefinition() {
        assertThatThrownBy(() -> InjectionContext.getBean(SelfReferencingBean.class)).isExactlyInstanceOf(BeanDefinitionException.class);
        assertThatThrownBy(() -> InjectionContext.getBean(CircularBean.class)).isExactlyInstanceOf(BeanDefinitionException.class);
        assertThatThrownBy(() -> InjectionContext.getBean(CircularIntermediateBean.class)).isExactlyInstanceOf(BeanDefinitionException.class);
    }
    
    @Test
    public void testNotABean() {
        assertThatThrownBy(() -> InjectionContext.getBean(BeanValidationTest.class)).isExactlyInstanceOf(BeanDefinitionException.class);
    }
    
    @Test
    public void testAbstractBean() {
        assertThatThrownBy(() -> InjectionContext.getBean(AbstractBean.class)).isExactlyInstanceOf(BeanDefinitionException.class);
    }
    
    @Test
    public void testMissingBeanConstructor() {
        assertThatThrownBy(() -> InjectionContext.getBean(MissingBeanConstructor.class)).isExactlyInstanceOf(BeanDefinitionException.class);
    }
    
    @Test
    public void testNonUniqueBeanConstructor() {
        assertThatThrownBy(() -> InjectionContext.getBean(NonUniqueBeanConstructor.class)).isExactlyInstanceOf(BeanDefinitionException.class);
    }
    
    @Test
    public void testNonBeanConstructorParam() {
        assertThatThrownBy(() -> InjectionContext.getBean(NonBeanConstructorParam.class)).isExactlyInstanceOf(BeanDefinitionException.class);
    }
    
    @Test
    public void testMissingPrimitiveConfigValue() {
        assertThatThrownBy(() -> InjectionContext.getBean(PrimitiveConfigValue.class)).isExactlyInstanceOf(BeanDefinitionException.class);
    }
    
    @Bean
    public static class SelfReferencingBean {
        
        @BeanConstructor
        public SelfReferencingBean(SelfReferencingBean b) {}
        
    }
    
    @Bean
    public static class CircularBean {
        
        @BeanConstructor
        public CircularBean(CircularIntermediateBean b) {}
        
    }
    
    @Bean
    public static class CircularIntermediateBean {
        
        @BeanConstructor
        public CircularIntermediateBean(CircularBean b) {}
    }
    
    @Bean
    public static abstract class AbstractBean {}
    
    @Bean
    public static class MissingBeanConstructor {
        
        public MissingBeanConstructor(Object o) {
        }
        
    }
    
    @Bean
    public static class NonUniqueBeanConstructor {
        
        @BeanConstructor
        public NonUniqueBeanConstructor(Object o) {
        }
        
        @BeanConstructor
        public NonUniqueBeanConstructor() {
        }
        
    }
    
    @Bean
    public static class NonBeanConstructorParam {
        
        @BeanConstructor
        public NonBeanConstructorParam(Object o) {
        }
        
    }
    
    @Bean
    public static class PrimitiveConfigValue {
        
        @BeanConstructor
        public PrimitiveConfigValue(@ConfigValue("configkey") int val) {
        }
        
    }
    
}
