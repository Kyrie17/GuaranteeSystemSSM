package com.ssm.code.pojo;

import java.util.List;

/**
 * AnswerNoticeInform class
 *
 * @author Flc
 * @date 2019/5/25
 */
public class AnswerNoticeInform {
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
     * 服务信息集合
     */
    private List<NoticeInform> noticeInforms;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 总页数
     */
    private int totalPage;

    public AnswerNoticeInform() {
    }

    public AnswerNoticeInform(List<NoticeInform> noticeInforms, int currentPage, int totalPage) {
        this.noticeInforms = noticeInforms;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
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

    public List<NoticeInform> getNoticeInforms() {
        return noticeInforms;
    }

    public void setNoticeInforms(List<NoticeInform> noticeInforms) {
        this.noticeInforms = noticeInforms;
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
}
