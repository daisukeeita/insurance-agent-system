package com.acolyptos.insurance.domain.inventory;

import java.util.List;

public class CocPageResponse<T> {

  private List<T> content;

  private int pageNumber;

  private int pageSize;

  private int totalPages;

  private long totalElements;

  protected CocPageResponse() {}

  public CocPageResponse(
      List<T> content, int pageNumber, int pageSize, int totalPages, long totalElements) {
    this.content = content;
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public List<T> getContents() {
    return content;
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }
}
