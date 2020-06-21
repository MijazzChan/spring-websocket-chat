package com.zstu.mijazz.utils;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 21-Jun-20.
 */
public class SensitiveNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 头两个字符的mix，mix相同，两个字符相同
     */
    protected final int headTwoCharMix;

    /**
     * 所有以这两个字符开头的词表
     */
    protected final TreeSet<StringPointer> words = new TreeSet<StringPointer>();

    /**
     * 下一个节点
     */
    protected SensitiveNode next;

    public SensitiveNode(int headTwoCharMix){
        this.headTwoCharMix = headTwoCharMix;
    }

    public SensitiveNode(int headTwoCharMix, SensitiveNode parent){
        this.headTwoCharMix = headTwoCharMix;
        parent.next = this;
    }

}
