package com.wangchenglong.myblog.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/9 15:12
 * @Description: TODO
 */
@Component
public class CopyProperties {

    public <S, T> List<T> copyList(List<S> source, Class<T> target) {
        List<T> targetList = new ArrayList<T>();
        for (S s : source) {
            try {
                T t = target.newInstance();
                BeanUtils.copyProperties(s, t);
                targetList.add(t);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return targetList;
    }
}