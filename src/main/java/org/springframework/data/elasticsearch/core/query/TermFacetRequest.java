package org.springframework.data.elasticsearch.core.query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.search.facet.FacetBuilder;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Basic term facet
 *
 * @author Artur Konczak
 */
public class TermFacetRequest extends AbstractFacetRequest {

    private String[] fields;
    private int size = 10;
    private TermsFacet.ComparatorType order;

    public TermFacetRequest(String name) {
        super(name);
    }

    public void setFields(String... fields) {
        this.fields = fields;
    }

    public void setSize(int size) {
        Assert.isTrue(size <= 0, "Size should be bigger then zero !!!");
        this.size = size;
    }

    public void ascTerm() {
        order = TermsFacet.ComparatorType.TERM;
    }

    public void descTerm() {
        order = TermsFacet.ComparatorType.REVERSE_TERM;
    }

    public void ascCount() {
        order = TermsFacet.ComparatorType.REVERSE_COUNT;
    }

    public void descCount() {
        order = TermsFacet.ComparatorType.COUNT;
    }

    @Override
    public FacetBuilder getFacet() {
        Assert.notEmpty(fields, "Please select at last one field !!!");
        TermsFacetBuilder builder = FacetBuilders.termsFacet(getName()).fields(fields).size(size);
        if (order != null) {
            builder.order(order);
        }
        return builder;
    }
}