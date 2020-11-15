package com.zc.util.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

import static com.zc.util.common.StringParser.parse;

public class ReflectUtils {

    private ReflectUtils() { }

    /**
     * 由类名称得到类
     * @param className
     * @return
     */
    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw  new IllegalArgumentException(parse("Not found class{}, case:{}", className, e.getMessage()), e);
        }
    }

    /**
     * 获取构造方法名称
     * @param constructor
     * @return
     */
    public static String getName(Constructor<?> constructor) {
        StringBuilder ret = new StringBuilder("(");
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        for(int i = 0; i < parameterTypes.length; ++i) {
            if (i > 0) {
                ret.append(',');
            }

            ret.append(getName(parameterTypes[i]));
        }

        ret.append(')');
        return ret.toString();
    }

    /**
     * 获取泛型
     * @param clazz
     * @return
     */
    public static Class<?> getGebericClass(Class<?> clazz) {
        return getGenericClass(clazz, 0);
    }

    /**
     * 获取泛型
     * @param clazz
     * @param index
     * @return
     */
    public static Class<?> getGenericClass(Class<?> clazz, int index) {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericInterfaces()[0];
            Object genericClass = parameterizedType.getActualTypeArguments()[index];
            if (genericClass instanceof ParameterizedType) {
                return (Class<?>) ((ParameterizedType)genericClass).getRawType();
            } else if (genericClass instanceof GenericArrayType) {
                return (Class<?>) ((GenericArrayType) genericClass).getGenericComponentType();
            } else {
                return ((Class<?>)genericClass).isArray() ? ((Class<?>) genericClass).getComponentType()
                        : (Class<?>) genericClass;
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(parse("{} generic type undefined!", clazz.getName()), e);
        }
    }

    /**
     * 获取方法名称<br>
     * eg:<br></>
     * public static void com.zc.Test.main(java.lang.String[]) throws java.lang.Exception<br></>
     *      -> void main(java.lang.String[])
     * @param method
     * @return
     */
    public static String getName(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(getName(method.getReturnType())).append(' ');
        sb.append(method.getName()).append('(');
        Class<?>[] parameterTypes = method.getParameterTypes();

        for(int i = 0; i < parameterTypes.length; ++i) {
            if (i > 0) {
                sb.append(',');
            }

            sb.append(getName(parameterTypes[i]));
        }

        sb.append(')');
        return sb.toString();
    }

    /**
     * 获取类名称，包括数组<br>
     * eg: Object[][] -> [][]java.lang.Object
     * @param clazz
     * @return
     */
    public static String getName(Class<?> clazz) {
        if (!clazz.isArray()) {
            return clazz.getName();
        } else {
            StringBuilder sb = new StringBuilder();

            do {
                sb.append("[]");
                clazz = clazz.getComponentType();
            } while (clazz.isArray());

            return clazz.getName() + sb.toString();
        }
    }

    /**
     * 一次判断多个对象和多个类的关系
     * @param classes
     * @param objects
     * @return
     */
    public static boolean isCompatible(Class<?>[] classes, Object[] objects) {
        int len = classes.length;
        if (len != objects.length) {
            return false;
        } else if (len == 0) {
            return true;
        } else {
            for (int i = 0; i < len; i++) {
                if (!isCompatible(classes[i], objects[i])) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * 判断该对象是否是该类的实现类，（包括装箱的判断）
     * @param clazz
     * @param object
     * @return
     */
    public static boolean isCompatible(Class<?> clazz, Object object) {
        boolean isPrimitive = isPrimitive(clazz);
        if (object == null) {
            return !isPrimitive;
        } else {
            if (isPrimitive) {
                if (clazz == Integer.TYPE) {
                    clazz = Integer.class;
                } else if (clazz == Boolean.TYPE) {
                    clazz = Boolean.class;
                } else if (clazz == Short.TYPE) {
                    clazz = Short.class;
                } else if (clazz == Float.TYPE) {
                    clazz = Float.class;
                } else if (clazz == Double.TYPE) {
                    clazz = Double.class;
                } else if (clazz == Byte.TYPE) {
                    clazz = Byte.class;
                } else if (clazz == Long.TYPE) {
                    clazz = Long.class;
                } else if (clazz == Character.TYPE) {
                    clazz = Character.class;
                }
            }

            return clazz == object.getClass() || clazz.isInstance(object);
        }
    }

    /**
     * 是否是基本类型
     * @param clazz
     * @return
     */
    public static boolean isPrimitive(Class<?> clazz) {
        return clazz.isPrimitive() || clazz == String.class
                || clazz == Boolean.class
                || clazz == Character.class
                || Number.class.isAssignableFrom(clazz)
                ||  Date.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是基本类型，（包括数组）
     * @param clazz
     * @return
     */
    public static boolean isPrimitives(Class<?> clazz) {
        return clazz.isArray() ? isPrimitive(clazz.getComponentType()) : isPrimitive(clazz);
    }

    /**
     * 由基本类型，eg：int.class, boolean.class<br>
     * 获取其装箱类型，eg：int.class -> Integer.class
     * @param clazz
     * @return
     */
    public static Class<?> getBoxedClass(Class<?> clazz) {
        if (clazz == Integer.TYPE) {
            return Integer.class;
        } else if (clazz == Boolean.TYPE) {
            return Boolean.class;
        } else if (clazz == Long.TYPE) {
            return Long.class;
        } else if (clazz == Float.TYPE) {
            return Float.class;
        } else if (clazz == Double.TYPE) {
            return Double.class;
        } else if (clazz == Character.TYPE) {
            return Character.class;
        } else if (clazz == Byte.TYPE) {
            return Byte.class;
        } else if (clazz == Short.TYPE) {
            return Short.class;
        }

        return clazz;
    }

}
