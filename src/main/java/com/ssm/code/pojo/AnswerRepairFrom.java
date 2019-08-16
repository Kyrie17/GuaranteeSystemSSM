package com.ssm.code.pojo;

import java.util.List;

public class AnswerRepairFrom {

    /**
     * 是否第一页
     */
    private boolean isFirstPage;
    /**
     * 是否最后一页
     */
    private boolean isLastPage;
    /**
     * 是否有下一页
     */
    private boolean isHasNextPage;
    /**
     * 是否有前一页
     */
    private boolean isHasPreviousPage;
    /**
     * 导航条
     */
    private int[] navigate;
    /**
     * 保修单集合
     */
    private List<RepairForm> repairForms;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总记录数
     */
    private long total;

    public AnswerRepairFrom() {
    }

    public AnswerRepairFrom(List<RepairForm> repairForms, int currentPage, int totalPage, long total) {
        this.repairForms = repairForms;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.total = total;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasNextPage() {
        return isHasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        isHasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return isHasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        isHasPreviousPage = hasPreviousPage;
    }

    public int[] getNavigate() {
        return navigate;
    }

    public void setNavigate(int[] navigate) {
        this.navigate = navigate;
    }

    public List<RepairForm> getRepairForms() {
        return repairForms;
    }

    public void setRepairForms(List<RepairForm> repairForms) {
        this.repairForms = repairForms;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
