package vn.com.datech.uic.portal.common.base.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * The type Base aspect.
 */
@Slf4j
@Component
public class BaseAspect {

    /**
     * The constant RETURN_VARIABLE.
     */
    private static final String RETURN_VARIABLE = "return";

    /**
     * The Bean factory.
     */
    private BeanFactory beanFactory;

    /**
     * The Parser.
     */
    private ExpressionParser parser = new SpelExpressionParser();

    /**
     * The Context.
     */
    private StandardEvaluationContext context = null;

    /**
     * The Discoverer.
     */
    private LocalVariableTableParameterNameDiscoverer discoverer =
            new LocalVariableTableParameterNameDiscoverer();

    /**
     * @param pBeanFactory org.springframework.beans.factory.BeanFactory
     */
    @Autowired
    public void setBeanFactory(final BeanFactory pBeanFactory) {
        beanFactory = pBeanFactory;
    }

    /**
     * Gets method.
     *
     * @param joinPoint the join point
     * @return the method
     */
    protected Method getMethod(final JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    /**
     * Gets annotation.
     *
     * @param <A>            the type parameter
     * @param joinPoint      the join point
     * @param annotationType the annotation type
     * @return the annotation
     */
    protected <A extends Annotation> A getAnnotation(
            final JoinPoint joinPoint,
            final Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(
                getMethod(joinPoint), annotationType);
    }

    /**
     * Gets context.
     *
     * @param joinPoint   the join point
     * @param returnValue the return value
     */
    protected void setContext(final JoinPoint joinPoint,
                              final Object returnValue) {
        Object[] args = joinPoint.getArgs();
        Method method = getMethod(joinPoint);

        context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));
        context.setVariable(RETURN_VARIABLE, returnValue);
        String[] params = discoverer.getParameterNames(method);
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], args[len]);
        }
    }

    /**
     * Gets value.
     *
     * @param <T>               the type parameter
     * @param spel              the spel
     * @param desiredResultType the desired result type
     * @return the value
     */
    protected <T> T getValue(final String spel,
                             final Class<T> desiredResultType) {
        if (StringUtils.isBlank(spel)) {
            return null;
        }
        return parser.parseExpression(spel)
                .getValue(context, desiredResultType);
    }

    /**
     * Gets logged in username.
     *
     * @return the logged in username
     */
    protected String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
