package com.itsherman.web.common.request;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

public class PageParam {

    @Min(0L)
    @ApiModelProperty(
            value = "页码",
            example = "0"
    )
    private Integer pageNo;

    @Min(0L)
    @ApiModelProperty(
            value = "每页记录数",
            example = "20"
    )
    private Integer pageSize;

    @ApiModelProperty("排序类型,1——升序，2——降序。顺序跟sortProperties中的字段的顺序一一对应")
    private Integer[] directions;

    @ApiModelProperty("排序字段,顺序跟directions中的排序类型的顺序一一对应")
    private String[] sortProperties;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer[] getDirections() {
        return directions;
    }

    public void setDirections(Integer[] directions) {
        this.directions = directions;
    }

    public String[] getSortProperties() {
        return sortProperties;
    }

    public void setSortProperties(String[] sortProperties) {
        this.sortProperties = sortProperties;
    }

    @ApiIgnore
    public Pageable getPageable() {
        Sort sort = this.getSort();
        Pageable pageable = PageRequest.of(0, 20, sort);
        if (this.pageSize != null && this.pageNo != null) {
            pageable = PageRequest.of(this.pageNo, this.pageSize, sort);
        }
        return pageable;
    }

    @ApiIgnore
    private Sort getSort() {
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        if (this.directions != null && this.sortProperties != null) {
            if (this.directions.length != this.sortProperties.length) {
                throw new IllegalArgumentException("排序字段必须和排序方式一一对应");
            }
            List<Sort.Order> orders = new ArrayList(this.sortProperties.length);

            for (int i = this.sortProperties.length - 1; i >= 0; --i) {
                Sort.Order order = null;
                switch (this.directions[i]) {
                    case 1:
                        order = Sort.Order.asc(this.sortProperties[i]);
                        break;
                    case 2:
                        order = Sort.Order.desc(this.sortProperties[i]);
                        break;
                    default:
                        continue;
                }

                orders.add(order);
            }

            sort = Sort.by(orders);
        }
        return sort;
    }
}
