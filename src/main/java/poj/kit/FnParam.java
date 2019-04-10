package poj.kit;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;

/**
 * 此类为 Function 调用设计 实现入参封装
 * *部分实现需依赖 Guava 包
 *
 * @author Shibo.
 * @date 2019/4/10
 */
public class FnParam<P> {

    private List<P> params = Lists.newArrayList();

    private FnParam(List<P> params){
        this.params = params;
    }

    public static <P> FnParam<P> set(List<P> params){
        return new FnParam(params);
    }

    public static <P> FnParam<P> set(P ... ps){
        return new FnParam(Lists.newArrayList(ps));
    }

    public FnParam<P> add(P p){
        this.params.add(p);
        return this;
    }

    public FnParam<P> addMore(P ... ps){
        this.params.addAll(Lists.newArrayList(ps));
        return this;
    }

    public FnParam<P> addAll(List<P> p){
        this.params.addAll(p);
        return this;
    }

    public P get(int i){
        if(i <= params.size() && i > 0){
            return params.get(--i);
        }
        return null;
    }

    public <T> T get(int i, Function<P, T> fn){
        P p = this.get(i);
        return p == null ? null : fn.apply(p);
    }

    public Integer getInt(int i){
        P p = this.get(i);
        return p == null ? null : (Integer) p;
    }

    public Long getLong(int i){
        P p = this.get(i);
        return p == null ? null : (Long) p;
    }

    public String getString(int i){
        P p = this.get(i);
        return p == null ? null : String.valueOf(p);
    }

    public Object getObject(int i){
        P p = this.get(i);
        return p == null ? null : p;
    }

    public List<P> getParams() {
        return params;
    }
}
