package com.mono.monochrome.di;

import java.util.ArrayList;
import java.util.List;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public class BeanDefinition {

    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();
    private Scope scope = Scope.SINGLETON;
    private boolean lazyInit = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isSingleton() {
        return this.getScope().equals(Scope.SINGLETON);
    }

    public static enum Scope {
        SINGLETON,
        PROTOTYPE
    }

    public static class ConstructorArg {
        private boolean isRef = false;
        private Class type;
        private Object arg;

        public boolean isRef() {
            return isRef;
        }

        public Class getType() {
            return type;
        }

        public Object getArg() {
            return arg;
        }

        private ConstructorArg(boolean isRef, Class type, Object arg) {
            this.isRef = isRef;
            this.type = type;
            this.arg = arg;
        }

        public static class Builder{
            private boolean ref = false;
            private Class type;
            private Object arg;

            public ConstructorArg build() {
                if (ref && type != null) {
                    throw new RuntimeException("bean为ref类型时type不需要设置");
                }
                if (!ref && (type == null || arg == null)) {
                    throw new RuntimeException("bean不为ref类型时type和arg必须填写");
                }
                return new ConstructorArg(ref, type, arg);
            }

            public Builder setRef(boolean ref) {
                this.ref = ref;
                return this;
            }

            public Builder setType(Class type) {
                this.type = type;
                return this;
            }

            public Builder setArg(Object arg) {
                this.arg = arg;
                return this;
            }
        }

    }
}
