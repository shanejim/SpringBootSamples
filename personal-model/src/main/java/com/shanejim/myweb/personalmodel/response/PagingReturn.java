package com.shanejim.myweb.personalmodel.response;

import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:25
 **/
public class PagingReturn<T> {

    private Long total;

    private List<T> results;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
