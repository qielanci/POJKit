package poj.kit;

import org.springframework.beans.BeanUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 定义 dto 外层对象，封装常用操作方法
 * 支持链式调用
 * 非线程安全
 *
 * @author Shibo.
 * @date 2019/3/20
 * @param <D>
 */
public class Dto<D> {

    private D dto;

    private Dto(D dto){
        this.dto = dto;
    }

    private Dto<D> update(D dto){
        this.dto = dto;
        return this;
    }

    public static <D> Dto<D> instance(D dto){
        return new Dto(dto);
    }

    /**
     * 从实体复制 依赖 spring framework
     * @param e
     * @param <E>
     * @return
     */
    public <E> Dto<D> copy(E e){
        if (null != e)
            BeanUtils.copyProperties(e, dto);
        return this;
    }

    /**
     * 修改
     * @param uo
     * @return
     */
    public Dto<D> upgrade(UnaryOperator<D> uo){
        return this.update(uo.apply(dto));
    }

    /**
     * 从实体根据动作转换
     * @param e
     * @param fn
     * @param <E>
     * @return
     */
    public <E> Dto<D> convert(E e, BiFunction<E, D, D> fn){
        return this.update(fn.apply(e, dto));
    }

    public <V> V extract(Function<D, V> fn){
        return fn.apply(dto);
    }

    public D get(){
        return this.dto;
    }

}
