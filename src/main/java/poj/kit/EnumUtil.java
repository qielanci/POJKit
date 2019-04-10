package poj.kit;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 枚举操作工具类
 *
 * @author Shibo.
 * @date 2019/4/10
 */
public class EnumUtil {


    public static <K, V extends Enum<V>, C extends Class<V>> Map<K, V> toMap(C clazz, Function<V, K> function){
        return Maps.uniqueIndex(Lists.newArrayList(clazz.getEnumConstants()), function);
    }

    public static <V, E extends Enum<E>, C extends Class<E>> E get(C clazz, Function<E, V> function, V value){
        return Maps.uniqueIndex(Lists.newArrayList(clazz.getEnumConstants()), function).get(value);
    }

    public static <E extends Enum<E>> boolean isEqual(E u, Function<E, Integer> function, int value){
        return function.apply(u) == (value);
    }

    public static <E extends Enum<E>> boolean isEqual(E u, Function<E, String> function, String value){
        return function.apply(u).contentEquals(value);
    }

    public static <V, E extends Enum<E>> boolean isEqual(E u, Function<E, V> function, E value){
        return function.apply(u).equals(function.apply(value));
    }

    public static <V, E extends Enum<E>> boolean isEqual(E u, Function<E, V> function, V value){
        return function.apply(u).equals(value);
    }

}
