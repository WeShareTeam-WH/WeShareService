package com.ws.model;

import com.ws.Constant;
import com.ws.util.SystemPropertiesUtil;

/**
 * Pagination model class
 */
public class Pagination {

    private Integer pageCount;
    private Integer pageSize = 0;
    private Integer currentPage;
    private Integer offset;
    private Integer totalCount;
    private String keyWord;

    public Integer getPageSize() {
        if (pageSize == Constant.ZERO) {
            pageSize = Integer.parseInt(SystemPropertiesUtil.getPropetiesValueByKey(Constant.PAGE_SIZE));
        }
        return pageSize;
    }

    public String getKeyWord() {
        if (keyWord == null) {
            keyWord = Constant.BLANK;
        }
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        if (keyWord == null) {
            keyWord = Constant.BLANK;
        }
        this.keyWord = keyWord;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
      //The default value of current page is 1.
        if (currentPage < Constant.DEFAULT_CURRENTPAGE) {
            currentPage = Constant.DEFAULT_CURRENTPAGE;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getOffset() {
        offset = (getCurrentPage() - Constant.DEFAULT_CURRENTPAGE) * pageSize;
        return offset;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        if(totalCount < Constant.DEFAULT_CURRENTPAGE) {
            pageCount = Constant.DEFAULT_CURRENTPAGE;
            return pageCount;
        }
        pageCount = (totalCount - Constant.DEFAULT_CURRENTPAGE) / getPageSize() + Constant.DEFAULT_CURRENTPAGE;
        return pageCount;
    }

    public Boolean isFirstPage() {
        if(this.currentPage < Constant.DEFAULT_CURRENTPAGE) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isLastPage() {
        if (this.currentPage >= this.pageCount) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
