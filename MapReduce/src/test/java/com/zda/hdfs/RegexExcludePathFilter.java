package com.zda.hdfs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * hdfs文件匹配器
 */
public class RegexExcludePathFilter implements PathFilter {
    private final String reg;

    public RegexExcludePathFilter(String reg) {
        this.reg = reg;
    }

    @Override
    public boolean accept(Path path) {
        return !path.toString().matches(reg);
    }
}
