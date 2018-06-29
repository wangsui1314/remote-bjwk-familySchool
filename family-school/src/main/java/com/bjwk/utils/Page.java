package com.bjwk.utils;


public class Page {
   private Integer numberPerPage;//每页显示数量
    private Integer currentPage;//当前页
    private Integer currentNumber;//从哪里开始

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Page() {
    }

    public Page(Integer numberPerPage, Integer currentPage, Integer totalNumber, Integer totalPage) {

        this.numberPerPage = numberPerPage;
        this.currentPage = currentPage;
    }

    public Integer getNumberPerPage() {

        return numberPerPage;
    }

    public void setNumberPerPage(Integer numberPerPage) {
        this.numberPerPage = numberPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        if (numberPerPage != 0){
            currentNumber = (currentPage-1)*numberPerPage;
        }
    }

	@Override
	public String toString() {
		return "Page [numberPerPage=" + numberPerPage + ", currentPage="
				+ currentPage + ", currentNumber=" + currentNumber + "]";
	}
    
}
